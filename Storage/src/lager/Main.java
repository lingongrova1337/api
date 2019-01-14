package lager;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	protected static Scanner s = new Scanner(System.in);
	public static Storage storage = new Storage();
	
	public static void main(String[] args) {
		int menuChoice = 0;
		
		fill();
		
		storage.printAllItems();
		
		/* 
		 * Add items
		 * Remove items
		 * Search items
		 * Print items
		 * Update items
		 * Set discount on items
		 * 
		 * */
		
		boolean running = true;
		
		do {
			System.out.println("Enter Menu Choice");
			System.out.println("1. Add item");
			System.out.println("2. Remove item");
			System.out.println("3. Search item");
			System.out.println("4. Print item");
			System.out.println("5. Update item");
			System.out.println("6. Set discount on item");
			System.out.println("7. Exit");
			System.out.print("Your choice: ");
			
			menuChoice = s.nextInt();
			
			switch(menuChoice) {
				case 1:
					int addMenuChoice = -1;
					// Add items to List<Item>
					do {
					System.out.println("Enter Menu Choice");
					System.out.println("1) Add TV");
					System.out.println("2) Add COmputer Screen");
					System.out.println("3) Exit");
					addMenuChoice = s.nextInt();
					switch(addMenuChoice) {
					
						case 1: //Add TV
							
							addTV();
							break;
						case 2: //Add Computer screen
							System.out.println();
							addCS();
							break;
						default:
							System.out.println("Please enter a valid number between 1 and 2");
					}
					}while(addMenuChoice !=3);
					break;
				case 2://remove item	
					removeItem();
					
					break;
				case 4:	
					storage.printAllItems();
					break;
				case 7:
					running = false;
					break;
				default:
					System.out.println("Please enter a valid number between 1 and 7");
			}
		} while(running);
		
	}
	
	private static void addCS() {
		System.out.println("Enter type of computer screen (LCD, LED etc)");
		String type = s.nextLine();
		System.out.println("Enter make of the computer screen (Philips, Sony etc)");
		String maker = s.nextLine();
		System.out.println("Please enter the sell value of the computer screen");
		double outPrice = s.nextDouble();
		System.out.println("Please enter the purchase price of the computer screen");
		double inPrice = s.nextDouble();
		System.out.println("Please enter the number of computer screens you have in stock");
		int quantity = s.nextInt();
		System.out.println("Please enter the dimension of the computer screen");
		double size = s.nextDouble();
		System.out.println("Please enter how long warranty the computer screen has");
		int warranty = s.nextInt();
		System.out.println("Please enter a description of the computer screen");
		String desc = s.nextLine();
		System.out.println("Please enter which connection types the computer screen has (HDMI, VGA, DVI etc)");
		String[] connect = s.nextLine().split(",");
		
		storage.addItem(new ComputerScreen(type, maker, outPrice, inPrice, quantity, size, warranty, desc, Arrays.asList(connect)));
		
	}

	public static void fill() {
		ComputerScreen cs1 = new  ComputerScreen("LED", "Sony", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI"));
		ComputerScreen cs2 = new  ComputerScreen("LCD", "BenQ", 16.0, 8.0, 17, 23.5, 12 , "Standard screen, nothing fancy", Arrays.asList("HDMI", "VGA", "DVI"));
		Tv tv1 = new Tv("LED", "Philips", 10.0, 5.0, 10, 10.0, 12000, "En billig tv");
		Tv tv2 = new Tv("QLED", "LG", 15.0, 5.0, 23, 43.0, 12000, "En dyr tv");
		storage.addItem(tv1);
		storage.addItem(tv2);
		storage.addItem(cs1);
		storage.addItem(cs2);
	}
	
	public static void addTV() {
		s.nextLine();
		System.out.println("Enter type of TV (LCD, LED etc)");
		String type = s.nextLine();
		System.out.println("Enter make of the TV (Philips, Sony etc)");
		String maker = s.nextLine();
		System.out.println("Please enter the sell value of the TV");
		double outPrice = s.nextDouble();
		System.out.println("Please enter the purchase price of the TV");
		double inPrice = s.nextDouble();
		System.out.println("Please enter the number of TVs you have in stock");
		int quantity = s.nextInt();
		System.out.println("Please enter the dimension of the TV");
		double size = s.nextDouble();
		System.out.println("Please enter how long warranty the TV has");
		int warranty = s.nextInt();
		System.out.println("Please enter a description of the TV");
		String desc = s.nextLine();
		s.nextLine();
		storage.addItem(new Tv(type, maker, outPrice, inPrice, quantity, size, warranty, desc));
	}
	
	public static void removeItem() {
		int deleteWhat;
		System.out.println("1) Remove Tv");
		System.out.println("2) Remove Computer Screen");
		System.out.println("3) Exit");
		
		
		Scanner fs = new Scanner(System.in);
		deleteWhat = fs.nextInt();
		System.out.println("Enter the type: ");
		fs.nextLine();
		String type = fs.nextLine();
		System.out.println("Enter the maker: ");
		String maker = fs.nextLine();
		System.out.println("Enter the outprice: ");
		double outprice = fs.nextDouble();
		System.out.println("Enter the inprice: ");
		double inprice = fs.nextDouble();
		System.out.println("Enter the quantity: ");
		int qty = fs.nextInt();
		
		switch(deleteWhat) {
		case 1: //delete a tv			
			System.out.println("Enter the size: ");
			double size = fs.nextDouble();
			System.out.println("Enter the warranty: ");
			int warranty = fs.nextInt();
			System.out.println("Enter the desc: ");
			fs.nextLine();
			String desc = fs.nextLine();
			storage.deleteItem(new Tv(type, maker, outprice, inprice, qty, size, warranty, desc));
			
			break;
		
		
		case 2://delete a computer screen
			break;
		}

		fs.close();
	}
}
