import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(8080); // Spark will run on port 8080

        get("/transfer", (req, res) -> {
                    String account1 = req.queryParams("account1");
                    String account2 = req.queryParams("account2");
                    String amount = req.queryParams("amount");
                    Account.transfer(account1, account2, Double.valueOf(amount));
                    return "Hello World";
                }
        );
    }
}