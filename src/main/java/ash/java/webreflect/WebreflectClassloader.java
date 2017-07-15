package ash.java.webreflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebreflectClassloader extends ClassLoader {
    private ClassLoader parent;
    private byte[] jarBytes;
    private Logger logger = LoggerFactory.getLogger(WebreflectClassloader.class);

    WebreflectClassloader(ClassLoader parent, byte[] jarBytes)
    {
        this.parent = parent;
        this.jarBytes = jarBytes;
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
                return defineClass(name, jarBytes, 0, jarBytes.length);
            } catch (ClassFormatError er)
            {
                logger.error("Class format error with class name %s\n%s", name, er.getStackTrace());
            }
        }
        return clazz;
    }
}
