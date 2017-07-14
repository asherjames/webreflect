package ash.java.webreflect;

import static spark.Spark.*;

class Routes {

    private Routes(){}

    static void registerRoutes()
    {
        get("/", (req, res) -> "GET endpoint");
    }
}
