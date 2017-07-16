package ash.java.webreflect;

import jdk.internal.org.objectweb.asm.ClassReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;

import java.util.List;
import java.util.stream.Collectors;

class Routes {
    private static final Logger logger = LoggerFactory.getLogger(Routes.class);

    private Routes(){}

    @SuppressWarnings("unchecked")
    static final Route ListRoute = (req, res) -> {
        byte[] classBytes = req.bodyAsBytes();
        WebreflectClassloader classloader = new WebreflectClassloader(classBytes);
        String classname = req.queryParams("classname");
        if(classname == null || classname.isEmpty())
        {
            res.status(400);
            return "Request must contain 'classname' query param";
        }

        Class clazz;
        try
        {
            clazz = classloader.findClass(req.queryParams("classname"));
        }
        catch (ClassNotFoundException e)
        {
            logger.error("Class not found", e);
            return "Class could not be loaded";
        }
        List list = (List) clazz.newInstance();
        return list.stream()
                .map(i -> String.format("element: %s%n", i))
                .collect(Collectors.joining());
    };

    static final Route InvestigateRoute = (req, res) -> {
        byte[] classBytes = req.bodyAsBytes();
        String classname = new ClassReader(classBytes).getClassName().replace("/", ".");
        logger.info("classname: " + classname);
        WebreflectClassloader classloader = new WebreflectClassloader(classBytes);
        Class clazz = classloader.findClass(classname);
        return clazz.getName();
    };
}
