package gregtechlite.gtlitecore.api.extension

import gregtech.api.items.toolitem.IGTTool
import gregtech.api.items.toolitem.ToolBuilder
import gregtech.api.items.toolitem.ToolDefinitionBuilder

/**
 * name toolDefinition to avoid conflict with toolStats(UnaryOperator)
 */
fun <T : IGTTool> ToolBuilder<T>.toolDefinition(builder: ToolDefinitionBuilder.() -> Unit): ToolBuilder<T>
    = toolStats((ToolDefinitionBuilder().apply(builder)).build())