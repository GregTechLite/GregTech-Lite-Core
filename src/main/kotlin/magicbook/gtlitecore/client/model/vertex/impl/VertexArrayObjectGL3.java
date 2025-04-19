package magicbook.gtlitecore.client.model.vertex.impl;

import magicbook.gtlitecore.client.model.vertex.VertexArrayObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import java.nio.IntBuffer;

public final class VertexArrayObjectGL3 implements VertexArrayObject
{

    @Override
    public int getCurrentBinding()
    {
        return GL11.glGetInteger(GL30.GL_VERTEX_ARRAY_BINDING);
    }

    @Override
    public int glGenVertexArrays()
    {
        return GL30.glGenVertexArrays();
    }

    @Override
    public void glGenVertexArrays(IntBuffer output)
    {
        GL30.glGenVertexArrays(output);
    }

    @Override
    public void glDeleteVertexArrays(int id)
    {
        GL30.glDeleteVertexArrays(id);
    }

    @Override
    public void glDeleteVertexArrays(IntBuffer ids)
    {
        GL30.glDeleteVertexArrays(ids);
    }

    @Override
    public boolean glIsVertexArray(int id)
    {
        return GL30.glIsVertexArray(id);
    }

    @Override
    public void glBindVertexArray(int id)
    {
        GL30.glBindVertexArray(id);
    }

}
