import java.util.*;
public class Restaurant {
	private static Scanner scan = new Scanner(System.in);
	private static Table[] tables;
	private static Dish[] dishes;
	private static Waiter[] waiters;
	private static double revenue;
	public static void main(String[] args){
		System.out.println("Thank you for installing Restaurant Managing Helpful Software (RMHS).");
		System.out.println("Please proceed by entering the name of your restaurant.");
		String name = scan.nextLine();
		revenue = 0;
		System.out.println("Perfect! Let's proceed by entering the number of tables.");
		int numTables = scan.nextInt();
		while(numTables < 1){
			System.out.println("Please enter a number greater than 0.");
			System.out.println("Please enter number of tables.");
			numTables = scan.nextInt();
		}
		tables = new Table[numTables];

		System.out.println("Great! Next, we will need to add seats to our tables. How many seats does each table have?");

		for(int i = 1; i <= numTables; i ++){
			System.out.println("Please enter number of seats in table " + i + ".");
			int input = scan.nextInt();
			while(input < 1){
				System.out.println("Please enter a number greater than 0.");
				System.out.println("Please enter number of seats in table " + i + ".");
				input = scan.nextInt();
			}
			tables[i - 1] = new Table(i, input);
		}
		
		//Sorting tables by number of seats
		int holdIndex = 0;
		int index = 0;
		int pointer = 0;
		int c = tables.length;
		Table hold = null;
		Table min = null;
		while (index < c){
			pointer = index;
			min = tables[pointer];
			holdIndex = pointer;
			while (pointer < c){
				if (tables[pointer].getSeats() < min.getSeats()){
					min = tables[pointer];
					holdIndex = pointer;
				}
				pointer = pointer + 1;
			}
			hold = tables[index]; // Swap elements after selecting lowest element
			tables[index] = min;
			tables[holdIndex] = hold;
			index = index + 1;
		}

		System.out.println("Next, let's take care of our Waiters.");
		System.out.println("How many servers are employed?");
		int numWaiters = scan.nextInt();
		waiters = new Waiter[numWaiters];
		String clear = scan.nextLine();
		for(int i = 1; i <= numWaiters; i ++){
			System.out.println("Please enter waiter " + i + "'s name.");
			String input = scan.nextLine();
			waiters[i - 1] = new Waiter(input);
		}
		
		System.out.println("Lastly, let's set up the dishes.");
		System.out.println("How many items are on the menu?");
		int numDishes = scan.nextInt();
		dishes = new Dish[numDishes];
		for(int i = 1; i <= numDishes; i ++){
			System.out.println("Please enter dish " + i + "'s name.");
			String input = scan.nextLine();
			input = scan.nextLine();
			System.out.println("Please enter dish " + i + "'s price.");
			double p = scan.nextDouble();
			dishes[i - 1] = new Dish(input, p);
		}

		int entry = -1;
		System.out.println("Great! The system is now live.");
		while(entry != 0){
			System.out.println("0) Exit system.");
			System.out.println("1) Seat a customer.");
			System.out.println("2) View waiter assignments.");
			System.out.println("3) Enter an order.");
			System.out.println("4) View orders.");
			System.out.println("5) Calculate a bill.");
			System.out.println("6) Enter payment.");
			System.out.println("7) Reset a table for the next guest.");
			System.out.println("8) Show restaurant revenue.");
			try{
				entry = scan.nextInt();
			}
			catch(InputMismatchException e){
				System.out.println("Please enter an integer.");
				scan.nextLine();
				entry = -1;
			}
			if(entry == 0){
				System.out.println("Thank you for using RMHS! Good bye!");
				System.exit(0);
			}
			else if(entry == 1){
				System.out.println("Please enter party size.");
				int size = scan.nextInt();
				boolean assigned = false;
				for(int i = 0; i < tables.length; i ++){
					if(size <= tables[i].getSeats() && tables[i].getOccupied() == false && assigned == false){
						tables[i].occupyTable();
						System.out.println("Party seated at table " + tables[i].getTableNum() + ".");
						assigned = true;
						int waiterIndex = findNextWaiter();
						waiters[waiterIndex].assignTable(tables[i]);
						tables[i].setWaiter(waiters[waiterIndex]);
						System.out.println("Waiter " + waiters[waiterIndex].getName() + " has been assigned to this table.");
					}
				}
				if(assigned == false)
					System.out.println("Sorry, there are no more tables for a party of this size.");
			}
			else if(entry == 2){
				System.out.println("Here are the waiter's assignments.");
				for(int i = 0; i < waiters.length; i ++){
					if (waiters[i] != null){
						System.out.println(waiters[i].getName() + ":");
						for(int j = 0; j < waiters[i].getAssigned().length; j ++){
							if(waiters[i].getAssigned()[j] != null){
								System.out.println("Table " + waiters[i].getAssigned()[j].getTableNum());
							}
						}
						System.out.println();
					}
				}
			}
			else if(entry == 3){
				int table = findTable();
				if(table != -1 && tables[table].getOccupied() == true){
					System.out.println("Enter customer order using the automated system.");
					int key = -1;
					while(key != 0){
						System.out.println("0) Done entering orders");
						for(int i = 1; i <= dishes.length; i++){
							System.out.println(i + ") " + dishes[i - 1].getName());
						}
						key = scan.nextInt();
						if(key >= 1 && key <= dishes.length){
							tables[table].addDish(dishes[key - 1]);
						}
						else if(key == 0)
							System.out.println("Exiting order menu.");
						else
							System.out.println("Invalid key entry. Please try again.");
					}
				}
				else if(table == -1)
					System.out.println("Table not found. Please try again.");
				else if(tables[table].getOccupied() == false)
					System.out.println("Table is not occupied and cannot make orders.");
				else
					System.out.println("A system error has occured. Please try again.");
			}
			else if(entry == 4){
				int table = findTable();
				if(table != -1 && tables[table].getOccupied() == true){
					System.out.println("Table " + tables[table].getTableNum() + " has placed the following orders:");
					for(int i = 0; i < tables[table].getDish().length; i++){
						if(tables[table].getDish()[i] != null){
							System.out.println(tables[table].getDish()[i].getName());
						}
					}

				}
				else if(table == -1)
					System.out.println("Table not found. Please try again.");
				else if(tables[table].getOccupied() == false)
					System.out.println("Table is not occupied and cannot make orders.");
				else
					System.out.println("A system error has occured. Please try again.");
			}
			else if(entry == 5){
				int table = findTable();
				if(table != -1){
					System.out.println("Table " + tables[table].getTableNum() + " has a total of $" + tables[table].getBill() + ".");
				}
				else
					System.out.println("Table not found. Please try again.");

			}
			else if(entry == 6){
				int table = findTable();
				if(table != -1){
					System.out.println("Table " + tables[table].getTableNum() + " has a total of $" + tables[table].getBill() + ".");
					System.out.println("Please select a payment method:");
					System.out.println("0) Exit");
					System.out.println("1) Credit card");
					System.out.println("2) Cash");
					int key = scan.nextInt();
					if(key == 0){
						System.out.println("Exiting payment menu.");
					}
					else if(key == 1){
						System.out.println("Total: $" + tables[table].getBill());
						System.out.println("Please enter amount for tips.");
						double amount = scan.nextDouble(); //Tip amount
						tables[table].getWaiter().addTips(amount);
						amount += tables[table].getBill(); //New total with tips
						System.out.println("Grand total: $" + amount);
						System.out.println("Please swipe card.");
						System.out.println("Payment accepted. Thank you!");
						revenue += tables[table].getBill();
						tables[table].paid(); //Clears bill and orders
					}
					else if(key == 2){
						System.out.println("Total: $" + tables[table].getBill());
						System.out.println("Please enter amount for tips.");
						double amount = scan.nextDouble(); //Tip amount
						tables[table].getWaiter().addTips(amount);
						amount += tables[table].getBill(); //New total with tips
						System.out.println("Grand total: $" + amount);
						System.out.println("Please enter cash given:");
						double cash = scan.nextDouble(); //Cash given
						while(cash < amount){
							System.out.println("Insufficient Cash");
							System.out.println("Please enter cash given:");
							cash = scan.nextDouble();
						}
						System.out.println("Payment accepted. Thank you!");
						revenue += tables[table].getBill();
						cash -= amount; //Change returned
						System.out.println("Change: $" + cash);
						tables[table].paid(); //Clears bill and orders
					}
					else
						System.out.println("Invalid menu entry. Please try again.");
				}
				else
					System.out.println("Table not found. Please try again.");
			}
			else if(entry == 7){
				int table = findTable();
				if(table != -1){
					tables[table].getWaiter().removeTable(tables[table]);
					tables[table].clearTable();
					System.out.println("Table " + tables[table].getTableNum() + " is cleared for the next guest.");
				}
				else
					System.out.println("Table not found. Please try again.");
			}
			else if(entry == 8){
				System.out.println("Total revenue is $" + revenue);
			}
			else
				System.out.println("Invalid menu entry. Please try again.");
		}

	}

	private static int findTable(){
		System.out.println("Please select a table number.");
		int input = scan.nextInt();
		for(int i = 0; i < tables.length; i ++){
			if (tables[i] != null && tables[i].getTableNum() == input)
				return i;
		}
		return -1;
	}

	private static int findNextWaiter(){
		int min = waiters[0].getNumTables();
		int index = 0;
		for(int i = 0; i < waiters.length; i ++){
			if (waiters[i] != null && waiters[i].getNumTables() < min){
				min = waiters[i].getNumTables();
				index = i;
			}
		}
		return index;
	}
}