package ash.java.webreflect;

import java.util.List;
import java.util.stream.Collectors;

import static spark.Spark.*;

class Routes {

    private Routes(){}

    @SuppressWarnings("unchecked")
    static void registerRoutes()
    {
        get("/", (req, res) -> "GET endpoint");

        post("/list", (req, res) -> {
            byte[] classBytes = req.bodyAsBytes();
            WebreflectClassloader classloader = new WebreflectClassloader(classBytes);
            Class clazz = classloader.findClass(req.queryParams("classname"));
            List list = (List) clazz.newInstance();
            return list.stream()
                    .map(i -> String.format("element: %s%n", i))
                    .collect(Collectors.joining());
        });
    }
}
