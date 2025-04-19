package magicbook.gtlitecore.client.model.vertex;

import java.nio.IntBuffer;

public interface VertexArrayObject
{

    int getCurrentBinding();

    int glGenVertexArrays();

    void glGenVertexArrays(IntBuffer buf);

    void glDeleteVertexArrays(int id);

    void glDeleteVertexArrays(IntBuffer buf);

    boolean glIsVertexArray(int id);

    void glBindVertexArray(int id);

}
