
public class Table {
	private int number;
	private int numSeats;
	private double bill;
	private boolean occupied;
	private Dish[] orders;
	private Waiter server;
	private final int maxDish = 15;

	public Table(){
		number = -1;
		numSeats = 4;
		occupied = false;
		bill = 0;
		this.orders = new Dish[maxDish];
	}

	public Table(int tableNum, int numSeats){
		number = tableNum;
		this.numSeats = numSeats;
		occupied = false;
		bill = 0;
		this.orders = new Dish[maxDish];
	}

	public void setTable(int tableNum, int numSeats){
		number = tableNum;
		this.numSeats = numSeats;
	}

	public void setTable(int tableNum, int numSeats, Waiter server){
		number = tableNum;
		this.numSeats = numSeats;
		this.orders = new Dish[maxDish];
		this.server = server;	
	}

	public void occupyTable(){
		if (occupied == false)
			occupied = true;
		else
			System.out.println("Table already occupied! System error!");
	}

	public void setWaiter(Waiter w){
		server = w;
	}
	public void clearTable(){
		occupied = false;
		bill = 0;
		for(int i = 0; i < maxDish; i ++){
			orders[i] = null;
		}
		server = null;
	}
	
	public void addDish(Dish newDish){
		boolean done = false;
		for(int i = 0; i < this.maxDish; i ++){
			if(orders[i] == null && done == false){
				orders[i] = newDish;
				done = true;
				System.out.println("Order of dish " + newDish.getName() + " added to queue.");
			}
		}
		if(done == false)
			System.out.println("Table has too many dishes. Cannot order more dishes.");
	}
	
	public void paid(){
		bill = 0;
		for(int i = 0; i < maxDish; i ++){
			orders[i] = null;
		}
	}
	
	public double getBill(){
		double sum = 0;
		for(int i = 0; i < orders.length; i ++){
			if(orders[i] != null)
				sum += orders[i].getPrice();
		}
		return sum;
	}

	public int getTableNum(){
		return this.number;
	}

	public int getSeats(){
		return this.numSeats;
	}
	
	public boolean getOccupied(){
		return this.occupied;
	}

	public Dish[] getDish(){
		return orders;
	}
	
	public Waiter getWaiter(){
		return server;
	}
	
	public static Table getTable(int tableNum, Table[] tables){
		for(int i = 0; i < tables.length; i ++){
			if(tables[i].getTableNum() == tableNum);
			return tables[i];
		}
		return null;
	}

}
