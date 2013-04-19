import java.io.*;
import java.util.*;

public class Stock {
	
	public static void main(String[] args) throws FileNotFoundException {
		Stock MSFT;
		MSFT = new Stock("MSFT", prices);
		for(int i=101; i<2000; i++){
			decision(MSFT, i);
			System.out.println(accountValue(i, MSFT));
		}
		System.out.println(prices.size());
	}
	
	
	static ArrayList<Double> prices = new ArrayList<Double>();
	

	String name = null;
	static boolean investedIn=false;
	static double accountCash = 100000;
	static double shares=0;
	
	public Stock(String name, ArrayList<Double> prices) throws FileNotFoundException{
		this.name=name;
		this.prices=listCreator(name);
	}
	
	public static void decision(Stock stock, int date){
		if(SMAPoint(prices, date)<=prices.get(date)){
			if(!investedIn){
				buy(date, stock);
			}
		}
		else{
			if(investedIn){
			sell(date, stock);
			}
		}
	}

	public static void buy(int date, Stock stock) {
		investedIn=true;
		shares=accountCash/stock.prices.get(date);
		accountCash=0;
		System.out.println("Bought");
	}
	public static void sell(int date, Stock stock){
		investedIn=false;
		accountCash=shares*stock.prices.get(date);
		shares=0;
		System.out.println("Sold");
	}
	public static double accountValue(int date, Stock stock){
		return accountCash+shares*stock.prices.get(date);
	}
	public static double currentValue(Stock stock, int date){
		return stock.prices.get(date);
	}
	//TODO: Aggregate many files
	public static ArrayList<Double> listCreator(String stock) throws FileNotFoundException{
		int i=0;
		Scanner sc = new Scanner(new File(stock+"Quotes.csv"));
		sc.useDelimiter("\",\"");
		sc.nextLine();
		while(sc.hasNextLine()){
		i++;
		sc.next();
		prices.add(0, Double.parseDouble(sc.next()));
		//System.out.println(prices.get(i));
		sc.nextLine();
		}
		return prices;
	}
	public static double SMAPoint(ArrayList<Double> prices, int date){
		double finalPrice=0;
		for (int i = 0; i<20; i++){
			finalPrice += prices.get(date-(i*5));
			//System.out.println(finalPrice);
		}
		return finalPrice/20;
	}
}
