import java.io.*;
import java.util.*;

public class Stock {
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println((SMAPoint(listCreator("MSFTQuotes"),0)));
	}
	
	static ArrayList<Double> prices = new ArrayList<Double>();
	
	String name = null;
	boolean investedIn=false;
	
	public Stock(String name){
		this.name=name;
		
	}
	
	public void decision(int date, Stock stock){
		if(SMAPoint(prices, date)<=prices.get(date)){
			if(stock.investedIn=false){
				buy(date);
			}
		}
		else{
			sell(date);
		}
	}

	public void buy(int date) {
		investedIn=true;
	}
	public void sell(int date){
		investedIn=false;
	}
	public double currentValue(Stock stock, int date){
		
	}
	//TODO: Aggregate many files
	
	public static ArrayList<Double> listCreator(String stock) throws FileNotFoundException{
		int i=0;
		Scanner sc = new Scanner(new File(stock+".csv"));
		sc.useDelimiter("\",\"");
		sc.nextLine();
		while(sc.hasNextLine()){
		sc.next();
		prices.add(Double.parseDouble(sc.next()));
		//System.out.println(prices.get(i));
		i++;
		sc.nextLine();
		}
		return prices;
	}
	public static double SMAPoint(ArrayList<Double> prices, int date){
		double finalPrice=0;
		for (int i = 0; i<20; i++){
			finalPrice += prices.get(date+(i*5));
			System.out.println(finalPrice);
		}
		return finalPrice/20;
	}
}
