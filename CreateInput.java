package finalproject;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CreateInput {
	
	/* main method */  
	public static void main(String[] args) {
		//create the unique input file for this assignment 
		createInputFile();
	}
		
	/* createInputFile() method generates random transaction data */
	static void createInputFile(){
		java.io.File file = new java.io.File("/Users/tmala/eclipse-workspace/oalabi_HW7/src/finalproject/input.txt");
		java.io.PrintWriter output = null;
		try {
			output = new java.io.PrintWriter(file);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			
		//list of timestamps for the purchases
		String [] timestamps = new String [200];
		Random random = new Random();
			
		//generate 200 random purchase timestamps
		for (int i=0; i < 200; i++) {
			LocalDateTime time = LocalDateTime.of(LocalDate.of(2021,12,4), 
					LocalTime.of(random.nextInt(24), random.nextInt(60), random.nextInt(60), random.nextInt(999999999 + 1)));   
			timestamps[i] =time.toString();
		}
			
		/* sort timestamps by time */
		Arrays.sort(timestamps);
			
		//array of items and their types
		String [][] items = {
								{ "Chicken", "edibleFresh"}, {"Coffee", "edible"}, {"Ham","edibleFresh"}, {"Cleaner", "notEdible"}, {"Diapers", "notEdible"}, 
								{"Paper", "notEdible"}, {"Meat","edibleFresh"}, {"Muffin","edibleFresh"}, {"Banana","edibleFresh"}, {"Apple","edibleFresh"}, 
								{"Waffles","edible"}, {"Mac&cheese","edibleFresh"}, {"Candy","edible"}, {"Oreo","edible"}, {"Chips","edible"}, 
								{"Soup", "edible"}, {"Noodles", "edible"}, {"Pasta","edible"}, {"Yogurt","edibleFresh"}, {"Cream","edible"}, 
								{"Quaker","edible"}, {"Cheerios","edible"}, {"Jam","edible"}, {"Lemon","edibleFresh"}, {"Potato", "edibleFresh"}, 
								{"Tomato", "edibleFresh"}, {"Cucumber","edibleFresh"}, {"Juice","edibleFresh"}, {"Watermelon","edibleFresh"}, {"pierogi", "edibleFresh"},
								{"Sushi", "edibleFresh"}, {"Mango","edibleFresh"}, {"Water","edibleFresh"}, {"Sneakers","edible"}, {"Twix", "edible"}, 
								{"Cheetos", "edible"}, {"Sandwich","edible"}, {"Butter","edible"}, {"Toy", "notEdible"}, {"Jar", "notEdible"}, 
								{"Glass", "notEdible"},{"Gloves", "notEdible"}, {"Hat", "notEdible"}, {"Newspaper", "notEdible"} , {"doritos chips", "edible"}, {"Cherios", "edible"} , {"ChoclateMilk", "edibleFresh"}							};
			
		//array of countries
		String [] contries = {"USA", "Canada", "UK", "China", "Japan", "Colombia", "Peru", "Brasil", "Nigeria", "Germany"};
			
		//Print the header
		output.printf("%25s%20s\n\n","","The Grocery Store Transactions Report for December 4, 2021, generated on " + LocalDate.now());
		output.printf("%-30s%-20s%-20s%-18s%-20s\n\n","Transaction Time", "Item Purchased", "Item Type", "Price", "Special Feature(s)");
			
		//Print the transactions based on item type
		for (int i = 0; i < timestamps.length; i++) {
			int index1 = (int) (Math.random()*items.length);
			int index2 = (int) (Math.random()*contries.length);
			output.printf("%20s%15s%15s",timestamps[i], "\"" + items[index1][0] + "\"", items[index1][1]);
			
			//print price and special features based on type		
			if (items[index1][1] == "notEdible") 
					output.printf("%15.2f%20s\n", Math.random()*100, contries[index2]);
					
			else  if (items[index1][1] == "edible") 
				    output.printf("%15.2f%20s\n",Math.random()*30, getRandomDate(LocalDate.of(2021, 12, 5), LocalDate.of(2022, 12, 5))); 
			
			else {//edible while fresh
					LocalDate now = LocalDate.of(2021, 12, 4); //we are generating the report for December 4
					LocalDate randomDate = getRandomDate(LocalDate.of(2021, 12, 5), LocalDate.of(2021, 12, 12));
						 
					//check if the item is about to expire within 3 days
					if (Period.between(now, randomDate).getDays() <= 3)
						output.printf("%15.2f%20s%10s\n", Math.random()*30, randomDate.toString(), true); //  expiring soon 
					else
						output.printf("%15.2f%20s%10s\n", Math.random()*30, randomDate.toString(), false); // not expiring soon 
			}
		}
		// Close the file
		    output.close();
	}
		
	/* auxiliary method getRandomDate() generates a random date */
	static LocalDate getRandomDate(LocalDate startDate,LocalDate endDate) {
		long start, end, randomEpochDay;//declare
		
        start = startDate.toEpochDay();
        end = endDate.toEpochDay();
        randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();  
        
        return LocalDate.ofEpochDay(randomEpochDay);
	}
}
