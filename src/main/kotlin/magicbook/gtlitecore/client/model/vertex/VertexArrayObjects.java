package magicbook.gtlitecore.client.model.vertex;

import magicbook.gtlitecore.client.model.vertex.impl.VertexArrayObjectApple;
import magicbook.gtlitecore.client.model.vertex.impl.VertexArrayObjectArb;
import magicbook.gtlitecore.client.model.vertex.impl.VertexArrayObjectGL3;
import org.lwjgl.opengl.GL11;

import java.nio.IntBuffer;

/**
 * Universal methods for handling <tt>Vertex Array Objects</tt> (VAO) in the OpenGL versions
 * supported by minecraft. Uses the GL3.0 VAO methods if available and fallbacks to {@code
 * GL_APPLE_vertex_array_object} or {@code ARB_vertex_array_object} if needed. This should
 * cover pretty much any GL 2.1-capable hardware.
 *
 * @author eigenraven
 */
public final class VertexArrayObjects
{

    private static final ThreadLocal<VertexArrayObject> VAO = ThreadLocal.withInitial(() -> {
        final VertexArrayObjectImpl vaoImpl = VertexArrayObjectImpl.querySupported();
        if (vaoImpl == VertexArrayObjectImpl.GL3)
            return new VertexArrayObjectGL3();
        else if (vaoImpl == VertexArrayObjectImpl.APPLE)
            return new VertexArrayObjectApple();
        else if (vaoImpl == VertexArrayObjectImpl.ARB)
            return new VertexArrayObjectArb();
        else
            throw new UnsupportedOperationException();
    });

    /**
     * Resets the cached VAO implementation for this thread, any further method calls will
     * query the {@code GLContext} capabilities again on first call.
     */
    public static void reinitializeGlContext()
    {
        VAO.remove();
    }

    /**
     * Equivalent to {@code glGetInteger(GL_VERTEX_ARRAY_BINDING)} for the currently
     * used VAO extension.
     *
     * @return The identifier of the currently bound VAO, or 0 if none is bound.
     */
    public static int getVertexArrayBinding()
    {
        return VAO.get().getCurrentBinding();
    }

    /**
     * Generates a single vertex array object name.
     *
     * @see #genVertexArrays(IntBuffer)
     */
    public static int genVertexArrays() {
        return VAO.get().glGenVertexArrays();
    }

    /**
     * {@code genVertexArrays(IntBuffer)} returns n vertex array object names in arrays.
     * <p>
     * There is no guarantee that the names form a contiguous set of integers; however, it
     * is guaranteed that none of the returned names was in use immediately before the call
     * to {@code genVertexArrays(IntBuffer)}.
     * <p>
     * Vertex array object names returned by a call to {@code genVertexArrays(IntBuffer)} are
     * not returned by subsequent calls, unless they are first deleted with {@link #deleteVertexArrays(IntBuffer)}.
     * <p>
     * The names returned in arrays are marked as used, for the purposes of {@code genVertexArrays(IntBuffer)}
     * only, but they acquire state and type only when they are first bound. <h1>Errors</h1>
     * {@link GL11#GL_INVALID_VALUE} is generated if n is negative.
     */
    public static void genVertexArrays(IntBuffer output)
    {
        VAO.get().glGenVertexArrays(output);
    }

    /**
     * Frees a single vertex array object name.
     *
     * @param id The name to free
     * @see      #deleteVertexArrays(IntBuffer)
     */
    public static void deleteVertexArrays(int id)
    {
        VAO.get().glDeleteVertexArrays(id);
    }

    /**
     * {@code deleteVertexArrays(IntBuffer)} deletes n vertex array objects whose names are
     * stored in the array addressed by arrays. Once a vertex array object is deleted it has
     * no contents and its name is again unused. If a vertex array object that is currently
     * bound is deleted, the binding for that object reverts to zero and the default vertex
     * array becomes current. Unused names in arrays are silently ignored, as is the value
     * zero. <h1>Errors</h1> {@link GL11#GL_INVALID_VALUE} is generated if n is negative.
     */
    public static void deleteVertexArrays(IntBuffer ids)
    {
        VAO.get().glDeleteVertexArrays(ids);
    }

    /**
     * {@code isVertexArray} returns {@code true} if array is currently the name of a vertex
     * array object. If the array is zero, or if array is not the name of a vertex array object,
     * or if an error occurs, {@code isVertexArray} returns {@code false}. If array is a name
     * returned by {@link #genVertexArrays(IntBuffer)}, by that has not yet been bound through
     * a call to {@link #bindVertexArray}, then the name is not a vertex array object and
     * {@code glIsVertexArray} returns {@code false}.
     */
    public static boolean isVertexArray(int array)
    {
        return VAO.get().glIsVertexArray(array);
    }

    /**
     * {@code bindVertexArray(int)} binds the vertex array object with name array. array is the
     * name of a vertex array object previously returned from a call to {@link #genVertexArrays(IntBuffer)},
     * or zero to break the existing vertex array object binding.
     * <p>
     * If no vertex array object with name array exists, one is created when array is first
     * bound. If the bind is successful no change is made to the state of the vertex array object,
     * and any previous vertex array object binding is broken. <h1>Errors</h1> {@link GL11#GL_INVALID_OPERATION}
     * is generated if array is not zero or the name of a vertex array object previously returned
     * from a call to {@link #genVertexArrays(IntBuffer)}.
     */
    public static void bindVertexArray(int id)
    {
        VAO.get().glBindVertexArray(id);
    }

}
