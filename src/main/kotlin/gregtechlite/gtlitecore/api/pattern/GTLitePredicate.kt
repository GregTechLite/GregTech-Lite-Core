package gregtechlite.gtlitecore.api.pattern

import gregtech.api.pattern.BlockWorldState
import gregtech.api.pattern.TraceabilityPredicate
import gregtech.api.util.BlockInfo
import gregtechlite.gtlitecore.api.block.attribute.BlockAttributeRegistry

class GTLitePredicate(predicate: (BlockWorldState) -> Boolean,
                      candidates: () -> Array<BlockInfo>,
                      val previewCandidates: Boolean = false,
                      val registry: BlockAttributeRegistry<*>? = null,
                      val optionalGroup: String? = null) : TraceabilityPredicate.SimplePredicate(predicate, candidates)
