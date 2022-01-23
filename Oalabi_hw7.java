package finalproject;


import java.io.FileNotFoundException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Item {
	//implements Comparable<Item>; 
	
			//add data fields
		private LocalDateTime transactionTime;
		private String itemName;
		private String type;
		private String specialFeature;
		private double cost;
		
		//add constructors
		
		public Item() {// no arg constructor 
		}

		public Item(LocalDateTime transactionTime, String itemName, String type, double cost) {
			this.transactionTime = transactionTime;
			this.itemName = itemName;
			this.type = type;
			this.cost = cost;
			//this.specialFeature = specialFeature;
		}

		//add getters and setters

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public LocalDateTime getTransactionTime() {
			return transactionTime;
		}

		public void setTransactionTime(LocalDateTime transactionTime) {
			this.transactionTime = transactionTime;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		

		public String getSpecialFeature() {
			return specialFeature;
		}

		public void setSpecialFeature(String specialFeature) {
			this.specialFeature = specialFeature;
		}
		

		public double getCost() {
			return cost;
		}

		public void setCost(double cost) {
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Item transactionTime:" + transactionTime + ", itemName:" + itemName + ", type:" + type
					+ ", specialFeature:" + specialFeature + " ";
		}

}//end item


class NotEdibleItem extends Item {
	//add data fields
		private String country;

			
		//add constructors
		

		public NotEdibleItem(LocalDateTime transactionTime, String itemName, String type, double cost, String country) {
			super(transactionTime, itemName, type,cost); //Special feature = country
			this.country = country;
			setSpecialFeature(country);
			
		}

		//add getters and setters

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		@Override
		public String toString() {
			return "Item transactionTime:" + getTransactionTime() + ", itemName:" + getItemName() + ", type:" + getType()
					+ ", specialFeature:" + getSpecialFeature() + " ";

		
}
}// end not edible 
	
class EdibleItem extends Item {
	
	//add data fields
		private LocalDate bestBeforeDate;
			
		//add constructors
		
		public EdibleItem() {
			
		}
		public EdibleItem(LocalDateTime transactionTime, String itemName, String type, double cost, LocalDate bestBeforeDate ) {
			super(transactionTime, itemName, type, cost); //Special feature = best before date
			this.bestBeforeDate = bestBeforeDate;
			setSpecialFeature(bestBeforeDate.toString());
		}
		
		//add getters and setters
		public LocalDate getBestBeforeDate() {
			return bestBeforeDate;
		}
		public void setBestBeforeDate(LocalDate bestBeforeDate) {
			this.bestBeforeDate = bestBeforeDate;
		}

		//implement toString()
		@Override
		public String toString() {
			return "Item transactionTime:" + getTransactionTime() + ", itemName:" + getItemName() + ", type:" + getType()
					+ ", specialFeature:" + getSpecialFeature() + " ";


	}

}//end edible item

class EdibleFreshItem extends EdibleItem {
	
	//add data fields
		private Boolean expireingSoon;
		//add constructors
		
		public EdibleFreshItem(LocalDateTime transactionTime, String itemName, String type,  double cost, LocalDate localDate, Boolean expireingSoon) {
			super(transactionTime, itemName, type, cost, localDate);
			this.expireingSoon = expireingSoon;
			//this.itemName = itemName;
			setSpecialFeature(expireingSoon.toString());
		}



		//add getters and setters
		public boolean isExpireingSoon() {
			return expireingSoon;
		}

		public void setExpireingSoon(boolean expireingSoon) {
			this.expireingSoon = expireingSoon;
		}

		//implement toString()
		@Override
		public String toString() {
			return "Item transactionTime:" + getTransactionTime() + ", itemName:" + getItemName() + ", type:" + getType()
					+ ", specialFeature:" + getSpecialFeature() + " ";

		
	}
	
} //end edible fresh




/*	TASK 5. Implement part 1 of the report.  
 * You need to read data from input.txt file, store it into an array of items (called items) 
 * and write it in a formatted form into the output file. Code, Debug and Present in the best format possible. 
 * Hint: it might be helpful to format a String of toString() method using String.format() method (works like System.out.printf.)
 * Hint2: You might have to close and delete existing output .doc or .txt file from its folder */

public class Oalabi_hw7 {
	static Item [] items = new Item [200];//an array of all Items 
	static EdibleItem [] edibleItems;//an array of Edible Items 
	static int countEdibleitems = 0;//count edible items 
	static String pageHeader; //save the header
	static String header2;
	
	//main() method runs the program
	public static void main(String[] args) throws FileNotFoundException {
		double sum = 0, average = 0;
		//Optional - create the a new input file  
		//CreateInput.createInputFile();
		
	    java.io.File inputFile = new java.io.File("/Users/tmala/eclipse-workspace/oalabi_HW7/src/finalproject/input.txt");
	    // Create a Scanner for the file
	    Scanner scan = null;
		try {
			scan = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("let's see what StackTrace will show: ");
			e.printStackTrace();
			System.out.println("Valuable info!");
		}

		//call the method to read data from the input file and store in it in ArrayList 
		readInputFile();
		
		//find the money total and the average price for this day
		 
		for (int i = 0; i < items.length -1; i++) {
			sum = sum + items[i].getCost();
			//System.out.println(items[i].getItemName() + " " + items[i].getCost());
		}
		average = sum/200;
		//System.out.println("The sum is: " + sum + "\n the average is: " + sum/200);
		
		
		
		//create an output file
		java.io.File file = new java.io.File(LocalDate.now().toString()+"_Oalabi_hw7_report.doc");
		java.io.PrintWriter output = new java.io.PrintWriter(file);

		//Part 1: lists all items sold within the day (December 4, 2021) by the purchase time stamp 
		output.print("This report prepared on " +  LocalDate.now() + " by oalabi, CPS*2231*xx\n\n");
		

		//Print the header row of the data
		output.print(pageHeader + "\n\n");
		output.print(header2 + "\n\n");

		
		//Print all items into the file (.txt or .doc)
		for (int i = 0; i < items.length -1; i++) {
			//output.print(items[i].getItemName( ) + "\t " + items[i].getType()+ "\t" + items[i].getCost() + "\t" + items[i].getSpecialFeature() + "\n");
			//output.print(items[i].getTransactionTime());
			output.printf("%-20s %-10s %-15s %1.2f %-10s \n",items[i].getTransactionTime(),  items[i].getItemName(), items[i].getType(), items[i].getCost(), items[i].getSpecialFeature());
		}

			
		//Display money total and the average price at the end of Part 1

				output.print("The average price is: $");
				output.printf("%-1.2f", average);
				
				output.print("\nThe total money is: $");
				output.printf("%1.2f\n " , sum);
				
				
/*TASK 6. Implement part 2 of the report.  
 * You need to sort data by price and write it in a formatted form into the same output file, 
 * Code, Debug and Present in a best format possible.
 * Hint: You might have to close and delete existing output file from the folder */

		//call the method to sort the items by price
				sortByPrice(items);
		
		//next page starts
		output.printf((char)12 +pageHeader.trim() + " sorted by price\n\n");
		
		//Print the header row of the page and the data
		output.print(header2);
		

		//Print all items sorted by price into the same file
		for (int i = 0; i < items.length -1; i++) {
			//output.print(items[i].getItemName( ) + "\t " + items[i].getType()+ "\t" + items[i].getCost() + "\t" + items[i].getSpecialFeature() + "\n");
			//output.print(items[i].getTransactionTime());
			output.printf("%-20s %-10s %-15s %1.2f %-10s \n",items[i].getTransactionTime(),  items[i].getItemName(), items[i].getType(), items[i].getCost(), items[i].getSpecialFeature());
		}

		
/*TASK 7. Implement part 3 of the report.  
 * You need to extract Edible Items out of all Items and store them into an array of EdibleItems called edibleItems. 
 * Sort this data by expiration data and write it in a formatted form into the same output file. 
 * Code, Debug and Present in a best format possible. */

		
		//populates edibleItems array
		edibleItems = new EdibleItem[countEdibleitems];
		
		int countEdibleIndex = 0;
				for(int i = 0; i < items.length-1; i++) {
					if ((items[i].getType()).equals("edible") || items[i].getType().equals("edibleFresh") ){
						edibleItems[countEdibleIndex] = (EdibleItem) items[i];
						countEdibleIndex++;
					}
				}
		//create an array of EdibleItems

		EdibleItem[] EdibleItems = sortByDate(edibleItems);
		
		
		// from list of items, if items[i].gettype=edibleitem
		//ass to edible items array
		
		
		//sort Edible Items by Expiration Date
	
		//next page starts
		output.printf((char)12 +pageHeader.trim() + " sorted by expiration Date\n\n");

		
		//Print the header row of the page and the data
		output.print(header2);

		
		//Print all Edible Items sorted by their expiration date into the file
		for (int i = 0; i < EdibleItems.length -1; i++) {
			//output.print(items[i].getItemName( ) + "\t " + items[i].getType()+ "\t" + items[i].getCost() + "\t" + items[i].getSpecialFeature() + "\n");
			//output.print(items[i].getTransactionTime());
			output.printf("%-20s %-10s %-15s %1.2f %-10s \n",EdibleItems[i].getTransactionTime(),  EdibleItems[i].getItemName(), EdibleItems[i].getType(), EdibleItems[i].getCost(), EdibleItems[i].getSpecialFeature());
		}
		
		// Close the file
	    output.close();
	}
	
	//read() method reads the data from the input file - its code is provided to you
	public static void readInputFile() {
		
		// Declare file and Create a File instance
	    java.io.File file = new java.io.File("/Users/tmala/eclipse-workspace/oalabi_HW7/src/finalproject/input.txt");
	    
	    // Declare a Scanner variable 
	    Scanner input = null;
		try {
			input = new Scanner(file);//file is OK
		} 
		catch (FileNotFoundException e) {//Houston  we've got a problem
			e.printStackTrace();
		}

	    // Read data from a file
		pageHeader = input.nextLine() + "\n";//title
		
		 header2 = input.nextLine() + input.nextLine() + input.nextLine();//table header
	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss.Z", Locale.US);
		LocalDateTime localDateTime = null;
		
		//till there is something in the file
		int i = 0; //for row
		while (input.hasNextLine() && input.hasNext()) {
			
			//get data
			localDateTime = LocalDateTime.parse(input.next());
			String item = input.next();
			String type = input.next();
			double cost = Double.parseDouble(input.next());
	
			//populate the items array, some data depends on type
	    	if (type.equals("notEdible")) {//uncomment the line below once your classes are created
	    		items[i] = new NotEdibleItem(localDateTime, item, type, cost, input.next()); 
	    	}
    		else {//uncomment the line below once your classes are created
    			countEdibleitems++;
    			LocalDate localDate = LocalDate.parse(input.next());
    			
    			if (type.equals("edible")) {//uncomment the line below once your classes are created
    				items[i] = new EdibleItem(localDateTime, item, type, cost, localDate); 
    			}
   				else {
    				items[i] = new EdibleFreshItem(localDateTime, item, type, cost, localDate, Boolean.parseBoolean(input.next())); 

        		}
    		}
	    	i++;
	   	}

	    // Close the file
	    input.close();
	}
	
	//sortByPrice() method sorts the items by their price. You can use any Sorting algorithm you want; some useful links are there in Appendix.
	public static void sortByPrice(Item[] list ) { 
		
		
		for (int i = 1; i < list.length -1; i++) {
		      // insert list[i] into a sorted sublist list[0..i-1] so that
			//          list[0..i] is sorted. *

			Item currentElement = list[i];
			int k;
			for (k = i-1; k>=0 && list[k].getCost() > currentElement.getCost(); k--) {
				list[k +1] = list[k];
			}
		      // Insert the current element into list[k+1]

			list[k+1] = currentElement;
		}
			
		
	}



	//sortByDate() method sorts the edible items by their expiration date. You can use any Sorting algorithm you want; some useful links are there in Appendix.
	public static EdibleItem[] sortByDate(EdibleItem[] list ) {//selection sort
		//provide your own algorithm using any sorting algorithm of your choice
		
		for (int i=0; i<countEdibleitems; i++) {
			
			for(int j=0; j<(countEdibleitems -i-1); j++) {
				if (list[j].getBestBeforeDate().isAfter(list[j+1].getBestBeforeDate())) {
					EdibleItem temp = list[j];
					list[j] = list[j+1];
					list[j+1] = temp;
				}
			}
		}
		
		return list;
	}
}

