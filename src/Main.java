import java.util.HashMap;
import java.util.Scanner;

public class Main{
    private Scanner scanner = new Scanner(System.in);
    private HashMap<String, Double> ratesToUSD = new HashMap<>();
    private HashMap<String, Double> ratesFromUSD = new HashMap<>();
    private String[] currencies = new String[]{"EUR", "GBP", "JPY", "INR", "CZK", "USD"};

    public static void main(String[] args) {
        Main main = new Main();

        main.createToUSDRates();
        main.createFromUSDRates();
        main.mainConsole();
    }

    public void mainConsole(){
        while(true){
            System.out.println("\nWelcome to Currency Converter!");

            boolean validInput = false;
            while(!validInput){
                System.out.print("Please enter a command (convert, exit): ");
                String command = stringInput();

                switch(command.toLowerCase()){
                    case "convert":
                        convert();
                        validInput = true;
                        break;
                    case "exit":
                        System.exit(0);
                    default:
                        System.out.println(command + " is not a valid command.");
                }
            }
        }
    }

    public void convert(){
        String fromCurrency = getFromCurrency();
        String toCurrency = getToCurrency();
        double amount = getAmount();

        System.out.print(amount + " " + fromCurrency + " converts to about ");
        System.out.printf("%,.3f",convertCurrency(fromCurrency, toCurrency, amount));
        System.out.println(" " + toCurrency);
    }

    public void printCurrencies(){
        System.out.println("\nSupported currencies:");
        for(int x = 0; x < currencies.length; x++){
            if(x % 10 == 0){
                System.out.println();
            }

            System.out.print((x % 10 == 0 || x == 0) ? currencies[x] : ", " + currencies[x]);
        }
    }

    public String getFromCurrency(){
        while(true){
            printCurrencies();
            System.out.print("\n\nPlease enter the from currency: ");
            String fromCurrency = stringInput().toUpperCase();
            if(ratesFromUSD.containsKey(fromCurrency) || fromCurrency.equals("USD")){
                return fromCurrency;
            } else {
                System.out.println("\nThis is not a supported currency.");

            }
        }
    }

    public String getToCurrency(){
        while(true){
            printCurrencies();
            System.out.print("\n\nPlease enter the to currency: ");
            String toCurrency = stringInput().toUpperCase();
            if(ratesToUSD.containsKey(toCurrency) || toCurrency.equals("USD")){
                return toCurrency;
            } else {
                System.out.println("\nThis is not a supported currency.");
            }
        }
    }

    public int getAmount(){
        while(true){
            System.out.print("\nPlease enter the amount of money you want to convert: ");
            int amount = intInput();
            if(amount > 0){
                return amount;
            } else {
                System.out.println("The amount must be greater than 0.");
            }
        }
    }

    public double convertCurrency(String fromCurrency, String toCurrency, double amount){
        if(fromCurrency.equalsIgnoreCase("USD")){
            amount = ratesFromUSD.get(toCurrency);
        } else if(toCurrency.equalsIgnoreCase("USD")){
            amount = ratesToUSD.get(fromCurrency);
        } else {
            amount *= ratesToUSD.get(fromCurrency);
            amount *= ratesFromUSD.get(toCurrency);
        }



        return amount;
    }

    public int intInput(){
        if(scanner.hasNextInt()){
            int number = scanner.nextInt();
            scanner.nextLine();
            return number;
        } else {
            scanner.nextLine();
            return -1;
        }
    }

    public String stringInput(){
        return scanner.nextLine();
    }

    private void createToUSDRates(){
        ratesToUSD.put("EUR", 1.0852);
        ratesToUSD.put("GBP", 1.2327);
        ratesToUSD.put("JPY", 0.0075235);
        ratesToUSD.put("INR", 0.012171);
        ratesToUSD.put("CZK", 0.012171);
    }

    private void createFromUSDRates(){
        ratesFromUSD.put("EUR", 0.92149);
        ratesFromUSD.put("GBP", 0.81110);
        ratesFromUSD.put("JPY", 132.94);
        ratesFromUSD.put("INR", 82.167);
        ratesFromUSD.put("CZK", 21.624);
    }
}