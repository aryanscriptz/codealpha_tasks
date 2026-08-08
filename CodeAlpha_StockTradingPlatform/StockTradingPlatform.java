import java.util.*;

class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}

class Transaction {
    private String type;
    private String symbol;
    private int quantity;
    private double amount;

    public Transaction(String type, String symbol, int quantity, double amount) {
        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.amount = amount;
    }

    public void display() {
        System.out.println(type + " | " + symbol + " | Qty: " + quantity + " | Amount: $" + amount);
    }
}

class User {
    private double balance;
    private HashMap<String, Integer> portfolio;
    private ArrayList<Transaction> history;

    public User(double balance) {
        this.balance = balance;
        portfolio = new HashMap<>();
        history = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void buyStock(Stock stock, int qty) {
        double cost = stock.getPrice() * qty;

        if (cost > balance) {
            System.out.println("Insufficient Balance!");
            return;
        }

        balance -= cost;
        portfolio.put(stock.getSymbol(),
                portfolio.getOrDefault(stock.getSymbol(), 0) + qty);

        history.add(new Transaction("BUY", stock.getSymbol(), qty, cost));

        System.out.println("Stock Purchased Successfully.");
    }

    public void sellStock(Stock stock, int qty) {

        if (!portfolio.containsKey(stock.getSymbol()) ||
                portfolio.get(stock.getSymbol()) < qty) {

            System.out.println("Not enough shares.");
            return;
        }

        double amount = stock.getPrice() * qty;

        balance += amount;

        portfolio.put(stock.getSymbol(),
                portfolio.get(stock.getSymbol()) - qty);

        if (portfolio.get(stock.getSymbol()) == 0)
            portfolio.remove(stock.getSymbol());

        history.add(new Transaction("SELL", stock.getSymbol(), qty, amount));

        System.out.println("Stock Sold Successfully.");
    }

    public void showPortfolio(HashMap<String, Stock> market) {

        System.out.println("\n------ Portfolio ------");

        double total = balance;

        if (portfolio.isEmpty()) {
            System.out.println("No Stocks Owned");
        }

        for (String symbol : portfolio.keySet()) {

            int qty = portfolio.get(symbol);

            double value = qty * market.get(symbol).getPrice();

            total += value;

            System.out.println(symbol + "  Qty: " + qty + "  Value: $" + value);
        }

        System.out.println("------------------------");
        System.out.println("Cash Balance : $" + balance);
        System.out.println("Total Assets : $" + total);
    }

    public void showHistory() {

        System.out.println("\n----- Transactions -----");

        if (history.isEmpty()) {
            System.out.println("No Transactions.");
            return;
        }

        for (Transaction t : history)
            t.display();
    }
}

public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        HashMap<String, Stock> market = new HashMap<>();

        market.put("AAPL", new Stock("AAPL", 180));
        market.put("TSLA", new Stock("TSLA", 250));
        market.put("GOOG", new Stock("GOOG", 140));
        market.put("AMZN", new Stock("AMZN", 145));
        market.put("MSFT", new Stock("MSFT", 410));

        User user = new User(10000);

        while (true) {

            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.println("\nAvailable Stocks");

                    for (Stock s : market.values()) {
                        System.out.println(s.getSymbol() + " : $" + s.getPrice());
                    }
                    break;

                case 2:

                    System.out.print("Enter Stock Symbol: ");
                    String buy = sc.next().toUpperCase();

                    if (!market.containsKey(buy)) {
                        System.out.println("Invalid Stock.");
                        break;
                    }

                    System.out.print("Enter Quantity: ");
                    int bqty = sc.nextInt();

                    user.buyStock(market.get(buy), bqty);

                    break;

                case 3:

                    System.out.print("Enter Stock Symbol: ");
                    String sell = sc.next().toUpperCase();

                    if (!market.containsKey(sell)) {
                        System.out.println("Invalid Stock.");
                        break;
                    }

                    System.out.print("Enter Quantity: ");
                    int sqty = sc.nextInt();

                    user.sellStock(market.get(sell), sqty);

                    break;

                case 4:
                    user.showPortfolio(market);
                    break;

                case 5:
                    user.showHistory();
                    break;

                case 6:
                    System.out.println("Thank You!");
                    return;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}