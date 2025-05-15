package magicbook.gtlitecore.client.shader;

import gregtech.client.renderer.IRenderSetup;


public interface IBloomRenderFast extends IRenderSetup
{

    /**
     * Custom bloom style.
     *
     * @return Returns 0 means "Simple Gaussian Blur Bloom", 1 for "Unity Bloom", 2 for "Unreal Bloom".
     */
    int customBloomStyle();

}
