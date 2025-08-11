package gregtechlite.gtlitecore.api.module;

public enum ModuleStage
{
    
    C_SETUP, // Initializing Module Containers, "C" means "Container".
    M_SETUP, // Initializing Modules, "M" means "Module".
    CONSTRUCTION, // FML Construction Event stage.
    PRE_INIT, // FML Pre-Initialization Event stage.
    INIT, // FML Initialization Event stage.
    POST_INIT, // FML Post-Initialization Event stage.
    LOAD_COMPLETE, // FML Load Complete Event stage.
    SERVER_ABOUT_TO_START, // FML Server About To Start Event stage.
    SERVER_STARTING, // FML Server Starting Event stage.
    SERVER_STARTED; // FML Server Started Event stage.

}
