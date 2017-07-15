package ash.java.webreflect;

import static spark.Spark.*;

class Routes {

    private Routes(){}

    static void registerRoutes()
    {
        get("/", (req, res) -> "GET endpoint");

        post("/iterable", (req, res) -> {
            byte[] jarBytes = req.bodyAsBytes();
            return String.format("Size of jar in bytes: %d", jarBytes.length);
        });
    }
}
