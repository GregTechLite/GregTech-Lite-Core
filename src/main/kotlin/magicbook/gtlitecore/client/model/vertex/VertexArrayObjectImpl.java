package magicbook.gtlitecore.client.model.vertex;

import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GLContext;

public enum VertexArrayObjectImpl
{

    GL3,
    APPLE,
    ARB;

    public static VertexArrayObjectImpl querySupported()
    {
        final ContextCapabilities capabilities = GLContext.getCapabilities();
        if (capabilities.OpenGL30)
            return GL3;
        else if (capabilities.GL_APPLE_vertex_array_object)
            return APPLE;
        else if (capabilities.GL_ARB_vertex_array_object)
            return ARB;
        else
            throw new UnsupportedOperationException("VAO not supported by the current OpenGL implementation.");
    }

}
