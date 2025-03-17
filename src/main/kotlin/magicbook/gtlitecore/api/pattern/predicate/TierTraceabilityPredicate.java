package magicbook.gtlitecore.api.pattern.predicate;

import gregtech.api.block.VariantActiveBlock;
import gregtech.api.pattern.BlockWorldState;
import gregtech.api.pattern.PatternStringError;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.BlockInfo;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import magicbook.gtlitecore.api.block.IBlockTier;
import net.minecraft.block.state.IBlockState;
import one.util.streamex.StreamEx;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Tier Traceability Predicate
 *
 * @author Gate Guardian
 *
 * <p>
 *     This class create a new {@code TraceabilityPredicate} about {@code IBlockTier}.
 *     Used to predicates casing in {@code MetaTileEntity} via given tiers in Internal API.
 *     Here is the usage of this class:
 *     <ul>
 *         <li>
 *             <b>Register Block Tier Hash Map via Internal API:</b>
 *             At first, you should register a new {@code Object2ObjectOpenHashMap} in Internal API,
 *             this is the Hash Map which used to store your blocks with tier info.
 *         </li>
 *         <li>
 *             <b>Add terms to correspondenced Hash Map via {@code register()}:</b>
 *             Then, you should add terms like {@code (IBlockState, IBlockTier)} to your Hash Map.
 *             At this step, used enhanced for or any other workable methods are allowed too.
 *             For example, just like below:
 *             <pre>{@code
 *                 public static final Object2ObjectOpenHashMap<IBlockState, IBlockTier>
 *                     MAPS = new Object2ObjectOpenHashMap();
 *             }</pre>
 *             and terms registry in {@code register()} will be:
 *             <pre>{@code
 *                 for (BlockCasing.CasingTier tier : BlockCasing.CasingTier.values()) {
 *                     MAPS.put(MetaBlocks.CASING.getState(tier),
 *                              new WrappedIntTier(tier, tier.ordinal() + 1));
 *                 }
 *             }</pre>
 *             where {@code IBlockState} term can be any other block class which impl {@code Block}.
 *         </li>
 *         <li>
 *             <b>Register Supplier via {@code TraceabilityPredicate} class:</b>
 *             In the end, you should register a method which used to included this predicate
 *             needed in your {@code MetaTileEntity}. Pay attention, you should given a special
 *             string to declare it in {@code MetaTileEntity} data. Supplier just like:
 *             <pre>{@code
 *                 public static Supplier<TierTraceabilityPredicate> CASING =
 *                     () -> new TierTraceabilityPredicate(MAPS, Comparator.comparing(
 *                         (s) -> ((WrappedIntTier) MAPS.get(s)).getIntTier()),
 *                     "Casing", null);
 *             }</pre>
 *         </li>
 *     </ul>
 *     How to used this {@code TraceabilityPredicate} in your {@code MetaTileEntity} class?
 *     At first, you should check it in {@code formStructure()} method:
 *     <pre>{@code
 *         Object type = context.get("CasingTieredStats"); // PatternMatchContext context
 *         this.casingTier = StructureUtility.getOrDefault(
 *             () -> type instanceof WrappedIntTier,
 *             () -> ((WrappedIntTier) type).getIntTier, 0);
 *         )
 *     }</pre>
 *     where {@code StructureUtility} is a utility class, and {@code casingTier} is a private
 *     param, and then, {@code invalidateStructure()} method used to register default value of
 *     tier, you can used like below:
 *     <pre>{@code
 *         this.casingTier = 0;
 *     }</pre>
 *     If you want to check tier with {@code RecipeMap} infos, then you can compare it with
 *     property of {@code RecipeMap} in {@code checkRecipe()} method:
 *     <pre>{@code
 *         return super.checkRecipe(recipe, consumeIfSuccess) // Recipe recipe, boolean consumeIfSuccess
 *             && recipe.getProperty(CasingTierProperty.getInstance(), 0) <= this.casingTier;
 *     }</pre>
 *     Another situation is other tier system, {@code WrappedIntTier} is example above, but this
 *     system allowed to used any tier which impl {@code WrappedTier} class.
 * </p>
 *
 * @see magicbook.gtlitecore.api.block.IBlockTier
 * @see magicbook.gtlitecore.api.block.impl.WrappedTier
 * @see magicbook.gtlitecore.api.block.impl.WrappedIntTier
 * @see magicbook.gtlitecore.api.GTLiteAPI
 * @see magicbook.gtlitecore.api.pattern.GTLiteTraceabilityPredicate
 */
public class TierTraceabilityPredicate extends TraceabilityPredicate
{

    // Correspondence Hash Map (IBlockState, IBlockTier) via Internal API content.
    private final Object2ObjectOpenHashMap<IBlockState, IBlockTier> map;
    // Name of TraceabilityPredicate.
    private final String name;
    // Error info key of TraceabilityPredicate.
    private final String errorKey;
    // Candidates Cache Supplier.
    private Supplier<BlockInfo[]> candidatesCache;
    // Block State comparator.
    private final Comparator<IBlockState> comparator;
    // Block State predicate.
    private final Predicate<IBlockState> predicate;

    public TierTraceabilityPredicate(Object2ObjectOpenHashMap<IBlockState, IBlockTier> map,
                                     String name,
                                     @Nullable String errorKey)
    {
        this(map, Comparator.comparing((s) -> map.get(s).getName()), BlockState -> true, name, errorKey);
    }

    public TierTraceabilityPredicate(Object2ObjectOpenHashMap<IBlockState, IBlockTier> map,
                                     Predicate<IBlockState> predicate,
                                     String name,
                                     @Nullable String errorKey)
    {
        this(map, Comparator.comparing((s) -> map.get(s).getName()), predicate, name, errorKey);
    }

    public TierTraceabilityPredicate(Object2ObjectOpenHashMap<IBlockState, IBlockTier> map,
                                     Comparator<IBlockState> comparator,
                                     String name,
                                     @Nullable String errorKey)
    {
        this(map, comparator, BlockState -> true, name, errorKey);
    }

    public TierTraceabilityPredicate(Object2ObjectOpenHashMap<IBlockState, IBlockTier> map,
                                     Comparator<IBlockState> comparator,
                                     Predicate<IBlockState> predicate,
                                     String name,
                                     @Nullable String errorKey)
    {
        super();
        this.map = map;
        this.name = name;
        if (errorKey == null)
        {
            this.errorKey = "gregtech.multiblock.pattern.error.casing";
        }
        else
        {
            this.errorKey = errorKey;
            this.addTooltips(this.errorKey);
        }
        this.common.add(new SimplePredicate(predicate(), candidates()));
        this.comparator = comparator;
        this.predicate = predicate;
    }

    private Predicate<BlockWorldState> predicate()
    {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (map.containsKey(blockState))
            {
                IBlockTier stats = map.get(blockState);
                Object tier = stats.getTier();
                Object currentTier = blockWorldState.getMatchContext().getOrPut(name, tier);
                if (!currentTier.equals(tier))
                {
                    blockWorldState.setError(new PatternStringError(errorKey));
                    return false;
                }
                else
                {
                    blockWorldState.getMatchContext().getOrPut(name + "TieredStats", stats);
                    if (blockState.getBlock() instanceof VariantActiveBlock)
                    {
                        (blockWorldState.getMatchContext()
                                .getOrPut("VABlock", new LinkedList<>()))
                                        .add(blockWorldState.getPos());
                    }
                    return true;
                }
            }
            else
            {
                return false;
            }
        };
    }

    private Supplier<BlockInfo[]> candidates()
    {
        if (candidatesCache == null)
        {
            candidatesCache = () -> StreamEx.of(map.keySet())
                    .filter(predicate)
                    .sorted(comparator)
                    .map(type -> new BlockInfo(type, null,
                            map.get(type).getInfo()))
                    .toArray(BlockInfo[]::new);
        }
        return candidatesCache;
    }

}
