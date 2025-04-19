package magicbook.gtlitecore.client.model.vertex.impl;

import magicbook.gtlitecore.client.model.vertex.VertexArrayObject;
import org.lwjgl.opengl.APPLEVertexArrayObject;
import org.lwjgl.opengl.GL11;

import java.nio.IntBuffer;

public final class VertexArrayObjectApple implements VertexArrayObject
{

    @Override
    public int getCurrentBinding()
    {
        return GL11.glGetInteger(APPLEVertexArrayObject.GL_VERTEX_ARRAY_BINDING_APPLE);
    }

    @Override
    public int glGenVertexArrays()
    {
        return APPLEVertexArrayObject.glGenVertexArraysAPPLE();
    }

    @Override
    public void glGenVertexArrays(IntBuffer output)
    {
        APPLEVertexArrayObject.glGenVertexArraysAPPLE(output);
    }

    @Override
    public void glDeleteVertexArrays(int id)
    {
        APPLEVertexArrayObject.glDeleteVertexArraysAPPLE(id);
    }

    @Override
    public void glDeleteVertexArrays(IntBuffer ids)
    {
        APPLEVertexArrayObject.glDeleteVertexArraysAPPLE(ids);
    }

    @Override
    public boolean glIsVertexArray(int id)
    {
        return APPLEVertexArrayObject.glIsVertexArrayAPPLE(id);
    }

    @Override
    public void glBindVertexArray(int id)
    {
        APPLEVertexArrayObject.glBindVertexArrayAPPLE(id);
    }

}
