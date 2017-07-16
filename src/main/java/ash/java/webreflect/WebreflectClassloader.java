package ash.java.webreflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebreflectClassloader extends ClassLoader {
    private ClassLoader parent;
    private byte[] classBytes;
    private Logger logger = LoggerFactory.getLogger(WebreflectClassloader.class);

    WebreflectClassloader(byte[] classBytes)
    {
        this.parent = ClassLoader.getSystemClassLoader();
        this.classBytes = classBytes;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException
    {
        Class clazz = null;
        try
        {
            clazz = parent.loadClass(name);
        }
        catch (ClassNotFoundException e)
        {
            try
            {
                return defineClass(name, classBytes, 0, classBytes.length);
            } catch (ClassFormatError er)
            {
                logger.error("Class format error with class name %s\n%s", name, er.getStackTrace());
            }
        }
        return clazz;
    }
}
