package gregtechlite.gtlitecore.api.pattern

import gregtech.api.pattern.BlockWorldState
import gregtech.api.pattern.TraceabilityPredicate
import gregtech.api.util.BlockInfo

class GTLitePredicate(predicate: (BlockWorldState) -> Boolean,
                      candidates: () -> Array<BlockInfo>,
                      val previewCandidates: Boolean = false) : TraceabilityPredicate.SimplePredicate(predicate, candidates)