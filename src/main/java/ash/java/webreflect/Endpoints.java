package ash.java.webreflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

class Endpoints {
    private static Logger logger = LoggerFactory.getLogger(Endpoints.class);

    private Endpoints(){}

    static void registerRoutes()
    {
        get("/", (req, res) -> "GET endpoint");

        post("/list", Routes.ListRoute);
    }
}
