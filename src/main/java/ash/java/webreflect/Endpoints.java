package ash.java.webreflect;

import static spark.Spark.*;

class Endpoints {
    private Endpoints(){}

    static void registerRoutes()
    {
        get("/", (req, res) -> "GET endpoint");

        post("/list", Routes.ListRoute);

        post("/investigate", Routes.InvestigateRoute);
    }
}
