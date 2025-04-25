package magicbook.gtlitecore.api.utils.maps;

/*
 * Copyright (c) 2022 GTNH Team, glee8e, Code Chicken
 *
 * This file is originally part of NotEnoughItem by Code Chicken.
 * It is adapted to implement the standard Map interface by glee8e,
 * and I port this class to gtlitecore, so I should show license there.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 2.1 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

import com.google.common.base.Objects;
import com.google.common.collect.Iterators;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;

/**
 * A map class for ItemStack keys with wildcard damage/NBT. Optimised for lookup.
 *
 * @author CodeChiken, glee8e, mitchej123
 *
 * <p>
 *     This map does NOT support null values or null keys! null will be silently ignored!
 *     Originally created by CodeChicken for NotEnoughItems.
 *     Adapted to {@code Map<ItemStack, T>} interface by glee8e.
 *     Edited later to add the option to ignore NBT tags to match vanilla furnace recipe map behavior.
 * </p>
 *
 * @since 2.8.7-beta
 */
@SuppressWarnings("all")
public final class ItemStackMap<T> extends AbstractMap<ItemStack, T> {

    public static final NBTTagCompound WILDCARD_TAG;

    static {
        WILDCARD_TAG = new NBTTagCompound();
        WILDCARD_TAG.setBoolean("*", true);
    }

    private final HashMap<Item, DetailMap> itemMap = new HashMap<>();
    private int size;

    private final boolean NBTSensitive;

    static ItemStack newItemStack(Item item, int size, int damage, NBTTagCompound tag) {
        ItemStack stack = new ItemStack(item, size, damage);
        NBTTagCompound stackTagCompound = stack.getTagCompound();
        stackTagCompound = tag;
        return stack;
    }

    static int actualDamage(ItemStack stack) {
        return Items.DIAMOND.getDamage(stack);
    }

    static ItemStack wildcard(Item item, boolean nbtsensitive) {
        return newItemStack(item, 1, WILDCARD_VALUE, nbtsensitive ? WILDCARD_TAG : null);
    }

    static boolean isWildcard(int damage) {
        return damage == WILDCARD_VALUE;
    }

    static boolean isWildcard(NBTTagCompound tag) {
        return tag != null && tag.getBoolean("*");
    }

    @SuppressWarnings("unused")
    public ItemStackMap() {
        this.NBTSensitive = false;
    }

    @SuppressWarnings("unused")
    public ItemStackMap(boolean NBTSensitive) {
        this.NBTSensitive = NBTSensitive;
    }

    @Override
    public T get(Object key) {
        if (!(key instanceof ItemStack)) return null;
        ItemStack stack = ((ItemStack) key);
        if (stack.getItem() == null)
            return null;
        DetailMap map = itemMap.get(stack.getItem());
        return map == null ? null : map.get(stack);
    }

    @Override
    public T put(ItemStack key, T value) {
        if (key == null || key.getItem() == null || value == null)
            return null;
        return itemMap.computeIfAbsent(key.getItem(), k -> new DetailMap(this.NBTSensitive)).put(key, value);
    }

    @Override
    public T remove(Object key) {
        if (!(key instanceof ItemStack))
            return null;
        ItemStack stack = ((ItemStack) key);
        if (stack.getItem() == null)
            return null;
        DetailMap map = itemMap.get(stack.getItem());
        return map == null ? null : map.remove(stack);
    }

    @Override
    public boolean containsKey(Object key) {
        if (!(key instanceof ItemStack))
            return false;
        ItemStack stack = ((ItemStack) key);
        if (stack.getItem() == null)
            return false;
        DetailMap map = itemMap.get(stack.getItem());
        return map != null && map.get(stack) != null;
    }

    @Override
    public T merge(ItemStack key, @NotNull T value,
                   @NotNull BiFunction<? super T, ? super T, ? extends T> remappingFunction) {
        if (key == null || key.getItem() == null || value == null || remappingFunction == null)
            return null;
        DetailMap map = itemMap.get(key.getItem());
        return itemMap.computeIfAbsent(key.getItem(), k -> new DetailMap(this.NBTSensitive))
                .merge(key, value, remappingFunction);
    }

    @Override
    public T computeIfAbsent(ItemStack key,
                             @NotNull Function<? super ItemStack, ? extends T> mappingFunction) {
        if (key == null || key.getItem() == null || mappingFunction == null)
            return null;
        DetailMap map = itemMap.get(key.getItem());
        return itemMap.computeIfAbsent(key.getItem(), k -> new DetailMap(this.NBTSensitive))
                .computeIfAbsent(key, mappingFunction);
    }

    @Override
    public void clear() {
        itemMap.clear();
        size = 0;
    }

    @NotNull
    @Override
    public Set<Entry<ItemStack, T>> entrySet() {
        return new SetView();
    }

    private static class StackMetaKey {

        public final int damage;
        public final NBTTagCompound tag;

        public StackMetaKey(int damage, NBTTagCompound tag) {
            this.damage = damage;
            this.tag = tag;
        }

        public StackMetaKey(ItemStack key) {
            this(actualDamage(key), key.getTagCompound());
        }

        public int hashCode() {
            return Objects.hashCode(damage, tag);
        }

        public boolean equals(Object o) {
            if (!(o instanceof StackMetaKey))
                return false;
            StackMetaKey t = (StackMetaKey) o;
            return damage == t.damage && Objects.equal(tag, t.tag);
        }
    }

    private static class DetailIter<T> implements Iterator<Entry<ItemStack, T>> {

        private final Item owner;
        private final ItemStackMap<T>.DetailMap backing;

        @Nullable
        private final Iterator<Entry<Integer, T>> damageIter;

        @Nullable
        private final Iterator<Entry<NBTTagCompound, T>> tagIter;

        @Nullable
        private final Iterator<Entry<StackMetaKey, T>> metaIter;

        private DetailIterState state = DetailIterState.NOT_STARTED;
        private boolean removed = false;

        private DetailIter(Entry<Item, ItemStackMap<T>.DetailMap> input) {
            this.owner = input.getKey();
            this.backing = input.getValue();
            damageIter = backing.damageMap != null ? backing.damageMap.entrySet().iterator() : null;
            tagIter = backing.tagMap != null ? backing.tagMap.entrySet().iterator() : null;
            metaIter = backing.metaMap != null ? backing.metaMap.entrySet().iterator() : null;
        }

        private DetailIterState nextState() {
            switch (state) {
                case NOT_STARTED:
                    if (backing.hasWildcard)
                        return DetailIterState.WILDCARD;
                case WILDCARD:
                case DAMAGE:
                    if (damageIter != null && damageIter.hasNext())
                        return DetailIterState.DAMAGE;
                case TAG:
                    if (tagIter != null && tagIter.hasNext())
                        return DetailIterState.TAG;
                case META:
                    if (metaIter != null && metaIter.hasNext())
                        return DetailIterState.META;
                case DONE:
                    return DetailIterState.DONE;
                default:
                    throw new IllegalStateException("Unexpected value: " + state);
            }
        }

        @Override
        public void remove() {
            if (removed)
                throw new IllegalStateException("remove() called twice");
            state.remove(this);
            removed = true;
        }

        @Override
        public boolean hasNext() {
            return nextState() != DetailIterState.DONE;
        }

        @Override
        public Entry<ItemStack, T> next() {
            DetailIterState nextState = nextState();
            if (nextState == DetailIterState.DONE)
                throw new NoSuchElementException();
            state = nextState;
            removed = false;
            return nextState.get(this);
        }

        private enum DetailIterState {

            NOT_STARTED {
                @Override
                <T> Entry<ItemStack, T> get(DetailIter<T> iter) {
                    throw new AssertionError("Should not call get on NOT_STARTED");
                }

                @Override
                <T> void remove(DetailIter<T> iter) {
                    throw new IllegalStateException("next() never called");
                }
            },
            WILDCARD {
                @Override
                <T> Entry<ItemStack, T> get(DetailIter<T> iter) {
                    return new Entry<ItemStack, T>() {

                        private final ItemStack key = wildcard(iter.owner, iter.backing.NBTSensitive);

                        @Override
                        public ItemStack getKey() {
                            return key;
                        }

                        @Override
                        public T getValue() {
                            return iter.backing.wildcard;
                        }

                        @Override
                        public T setValue(T value) {
                            return iter.backing.wildcard = value;
                        }
                    };
                }

                @Override
                <T> void remove(DetailIter<T> iter) {
                    assert iter.backing.hasWildcard;
                    iter.backing.wildcard = null;
                    iter.backing.hasWildcard = false;
                }
            },
            DAMAGE {
                @Override
                <T> Entry<ItemStack, T> get(DetailIter<T> iter) {
                    assert iter.damageIter != null;
                    Entry<Integer, T> entry = iter.damageIter.next();
                    return new ItemStackEntry<>(
                            newItemStack(
                                    iter.owner,
                                    1,
                                    entry.getKey(),
                                    iter.backing.NBTSensitive ? WILDCARD_TAG : null),
                            entry);
                }

                @Override
                <T> void remove(DetailIter<T> iter) {
                    assert iter.damageIter != null;
                    iter.damageIter.remove();
                }
            },
            TAG {
                @Override
                <T> Entry<ItemStack, T> get(DetailIter<T> iter) {
                    assert iter.tagIter != null;
                    Entry<NBTTagCompound, T> entry = iter.tagIter.next();
                    return new ItemStackEntry<>(newItemStack(iter.owner, 1, WILDCARD_VALUE, entry.getKey()), entry);
                }

                @Override
                <T> void remove(DetailIter<T> iter) {
                    assert iter.tagIter != null;
                    iter.tagIter.remove();
                }
            },
            META {
                @Override
                <T> Entry<ItemStack, T> get(DetailIter<T> iter) {
                    assert iter.metaIter != null;
                    Entry<StackMetaKey, T> entry = iter.metaIter.next();
                    return new ItemStackEntry<>(
                            newItemStack(iter.owner, 1, entry.getKey().damage, entry.getKey().tag),
                            entry);
                }

                @Override
                <T> void remove(DetailIter<T> iter) {
                    assert iter.metaIter != null;
                    iter.metaIter.remove();
                }
            },
            DONE {
                @Override
                <T> Entry<ItemStack, T> get(DetailIter<T> iter) {
                    throw new AssertionError("Should not call get on DONE");
                }

                @Override
                <T> void remove(DetailIter<T> iter) {
                    throw new AssertionError("Should not call remove on DONE");
                }
            };

            abstract <T> Entry<ItemStack, T> get(DetailIter<T> iter);

            abstract <T> void remove(DetailIter<T> iter);

            private static class ItemStackEntry<T> implements Entry<ItemStack, T> {

                private final ItemStack key;
                private final Entry<?, T> entry;

                public ItemStackEntry(ItemStack key, Entry<?, T> entry) {
                    this.key = key;
                    this.entry = entry;
                }

                @Override
                public ItemStack getKey() {
                    return key;
                }

                @Override
                public T getValue() {
                    return entry.getValue();
                }

                @Override
                public T setValue(T value) {
                    return entry.setValue(value);
                }
            }
        }
    }

    private class DetailMap {

        private boolean hasWildcard;
        private T wildcard;
        private HashMap<Integer, T> damageMap;
        private HashMap<NBTTagCompound, T> tagMap;
        private HashMap<StackMetaKey, T> metaMap;
        private int size;

        private final boolean NBTSensitive;

        public DetailMap(boolean NBTSensitive) {
            this.NBTSensitive = NBTSensitive;
        }

        private KeyType getKeyType(int damage, NBTTagCompound tag) {
            KeyType i = isWildcard(damage) ? KeyType.WildcardMeta : KeyType.NotWildcard;
            if (!NBTSensitive || isWildcard(tag))
                i = i.withWildcardTag();
            return i;
        }

        public T get(ItemStack key) {
            if (wildcard != null)
                return wildcard;

            if (damageMap != null) {
                final T ret = damageMap.get(actualDamage(key));
                if (ret != null)
                    return ret;
            }
            if (tagMap != null) {
                final T ret = tagMap.get(key.getTagCompound());
                if (ret != null)
                    return ret;
            }
            if (metaMap != null)
                return metaMap.get(new StackMetaKey(key));

            return null;
        }

        public T put(ItemStack key, T value) {
            try {
                switch (getKeyType(actualDamage(key), key.getTagCompound())) {
                    case NotWildcard:
                        if (metaMap == null)
                            metaMap = new HashMap<>();
                        return metaMap.put(new StackMetaKey(key), value);
                    case WildcardMeta:
                        if (tagMap == null)
                            tagMap = new HashMap<>();
                        return tagMap.put(key.getTagCompound(), value);
                    case WildcardTag:
                        if (damageMap == null)
                            damageMap = new HashMap<>();
                        return damageMap.put(actualDamage(key), value);
                    case WildcardAll:
                        T ret = wildcard;
                        wildcard = value;
                        hasWildcard = true;
                        return ret;
                }
            } finally {
                updateSize();
            }
            return null;
        }

        public T remove(ItemStack key) {
            try {
                switch (getKeyType(actualDamage(key), key.getTagCompound())) {
                    case NotWildcard:
                        return metaMap != null ? metaMap.remove(new StackMetaKey(key)) : null;
                    case WildcardMeta:
                        return tagMap != null ? tagMap.remove(key.getTagCompound()) : null;
                    case WildcardTag:
                        return damageMap != null ? damageMap.remove(actualDamage(key)) : null;
                    case WildcardAll:
                        T ret = wildcard;
                        wildcard = null;
                        hasWildcard = false;
                        return ret;
                }
            } finally {
                updateSize();
            }
            return null;
        }

        private void updateSize() {
            int newSize = (hasWildcard ? 1 : 0) + (metaMap != null ? metaMap.size() : 0)
                    + (tagMap != null ? tagMap.size() : 0)
                    + (damageMap != null ? damageMap.size() : 0);

            if (newSize != size) {
                ItemStackMap.this.size += newSize - size;
                size = newSize;
            }
        }

        public T merge(ItemStack key, T value, BiFunction<? super T, ? super T, ? extends T> remappingFunction) {
            try {
                switch (getKeyType(actualDamage(key), key.getTagCompound())) {
                    case NotWildcard:
                        if (metaMap == null)
                            metaMap = new HashMap<>();
                        return metaMap.merge(new StackMetaKey(key), value, remappingFunction);
                    case WildcardMeta:
                        if (tagMap == null)
                            tagMap = new HashMap<>();
                        return tagMap.merge(key.getTagCompound(), value, remappingFunction);
                    case WildcardTag:
                        if (damageMap == null)
                            damageMap = new HashMap<>();
                        return damageMap.merge(actualDamage(key), value, remappingFunction);
                    case WildcardAll:
                        T newValue = wildcard == null ? value : remappingFunction.apply(wildcard, value);
                        wildcard = newValue;
                        hasWildcard = newValue != null;
                        return newValue;
                }
            } finally {
                updateSize();
            }
            return null;
        }

        public T computeIfAbsent(ItemStack key, Function<? super ItemStack, ? extends T> mappingFunction) {
            try {
                switch (getKeyType(actualDamage(key), key.getTagCompound())) {
                    case NotWildcard:
                        if (metaMap == null)
                            metaMap = new HashMap<>();
                        return metaMap.computeIfAbsent(new StackMetaKey(key), x -> mappingFunction.apply(key));
                    case WildcardMeta:
                        if (tagMap == null)
                            tagMap = new HashMap<>();
                        return tagMap.computeIfAbsent(key.getTagCompound(), x -> mappingFunction.apply(key));
                    case WildcardTag:
                        if (damageMap == null)
                            damageMap = new HashMap<>();
                        return damageMap.computeIfAbsent(actualDamage(key), x -> mappingFunction.apply(key));
                    case WildcardAll:
                        T newValue = wildcard == null ? mappingFunction.apply(key) : wildcard;
                        wildcard = newValue;
                        hasWildcard = newValue != null;
                        return newValue;
                }
            } finally {
                updateSize();
            }
            return null;
        }
    }

    private class SetView extends AbstractSet<Entry<ItemStack, T>> {

        @Override
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return java.util.Objects.equals(get(entry.getKey()), entry.getValue());
        }

        @Override
        public boolean add(Entry<ItemStack, T> itemStackTEntry) {
            return itemStackTEntry.getKey() != null && itemStackTEntry.getValue() != null
                    && put(itemStackTEntry.getKey(), itemStackTEntry.getValue()) == null;
        }

        @Override
        public boolean remove(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return ItemStackMap.this.remove(entry.getKey(), entry.getValue());
        }

        @NotNull
        @Override
        public Iterator<Entry<ItemStack, T>> iterator() {
            return Iterators.concat(Iterators.transform(itemMap.entrySet().iterator(), DetailIter::new));
        }

        @Override
        public int size() {
            return size;
        }
    }

    private enum KeyType {

        NotWildcard, // MetaMap
        WildcardMeta, // TagMap
        WildcardTag, // DamageMap
        WildcardAll, // WildCard
        ;

        private static final KeyType[] VALUES = values();

        public KeyType withWildcardMeta() {
            return VALUES[ordinal() | 1];
        }

        public KeyType withWildcardTag() {
            return VALUES[ordinal() | 2];
        }
    }

}
