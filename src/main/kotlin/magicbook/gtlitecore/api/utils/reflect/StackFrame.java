package magicbook.gtlitecore.api.utils.reflect;

public interface StackFrame
{

    String getClassName();

    String getMethodName();

    Class<?> getDeclaringClass();

    int getLineNumber();

    boolean isNativeMethod();

}
