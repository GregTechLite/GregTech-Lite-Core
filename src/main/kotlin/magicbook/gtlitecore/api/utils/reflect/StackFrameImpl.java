package magicbook.gtlitecore.api.utils.reflect;

public class StackFrameImpl implements StackFrame
{

    private final StackTraceElement element;
    private final StackWalker walker;

    private Class<?> declaringClass;

    /* package */ StackFrameImpl(StackTraceElement element, StackWalker walker)
    {
        this.element = element;
        this.walker = walker;
    }

    @Override
    public String getClassName()
    {
        return element.getClassName();
    }

    @Override
    public String getMethodName()
    {
        return element.getMethodName();
    }

    @Override
    public Class<?> getDeclaringClass()
    {
        if (walker.retainClassReference)
        {
            if (declaringClass == null)
            {
                try
                {
                    declaringClass = Class.forName(getClassName());
                }
                catch (ClassNotFoundException exception)
                {
                    throw new RuntimeException(exception);
                }
            }
            return declaringClass;
        }
        throw new UnsupportedOperationException("RETAIN_CLASS_REFERENCE option not enabled");
    }

    @Override
    public int getLineNumber()
    {
        return element.getLineNumber();
    }

    @Override
    public boolean isNativeMethod()
    {
        return element.isNativeMethod();
    }

}
