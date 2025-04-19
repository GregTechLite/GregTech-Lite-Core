package magicbook.gtlitecore.client.model.vertex.impl;

import magicbook.gtlitecore.client.model.vertex.VertexArrayObject;
import org.lwjgl.opengl.ARBVertexArrayObject;
import org.lwjgl.opengl.GL11;

import java.nio.IntBuffer;

public final class VertexArrayObjectArb implements VertexArrayObject
{

    @Override
    public int getCurrentBinding()
    {
        return GL11.glGetInteger(ARBVertexArrayObject.GL_VERTEX_ARRAY_BINDING);
    }

    @Override
    public int glGenVertexArrays()
    {
        return ARBVertexArrayObject.glGenVertexArrays();
    }

    @Override
    public void glGenVertexArrays(IntBuffer output)
    {
        ARBVertexArrayObject.glGenVertexArrays(output);
    }

    @Override
    public void glDeleteVertexArrays(int id)
    {
        ARBVertexArrayObject.glDeleteVertexArrays(id);
    }

    @Override
    public void glDeleteVertexArrays(IntBuffer ids)
    {
        ARBVertexArrayObject.glDeleteVertexArrays(ids);
    }

    @Override
    public boolean glIsVertexArray(int id)
    {
        return ARBVertexArrayObject.glIsVertexArray(id);
    }

    @Override
    public void glBindVertexArray(int id)
    {
        ARBVertexArrayObject.glBindVertexArray(id);
    }

}
