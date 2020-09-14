
public class Waiter extends Employee{
	private Table[] assigned;
	private int numTables;
	private final int maxTable = 10;
	private double tips;

	public Waiter(){
		super();
		assigned = new Table[maxTable];
		tips = 0;
		numTables = 0;
	}

	public Waiter(String name){
		super(name);
		assigned = new Table[maxTable];
		tips = 0;
		numTables = 0;
	}

	public void assignTable(Table newTable){
		boolean done = false;
		for(int i = 0; i < this.maxTable; i ++){
			if(assigned[i] == null && done == false){
				assigned[i] = newTable;
				done = true;
				numTables ++;
				System.out.println("Addition of assignment successful.");
			}
		}
		if(done == false)
			System.out.println("Waiter has too many assignments. Cannot assign more tables.");
	}

	public void removeTable(Table oldTable){
		boolean done = false;
		for(int i = 0; i < this.maxTable; i ++){
			if(assigned[i] == oldTable && done == false){
				assigned[i] = null;
				done = true;
				numTables --;
				System.out.println("Remove of assignment successful.");
			}
		}
		if(done == false)
			System.out.println("Table not found in Waiter's assignments.");
	}

	public void setName(String n){
		super.setName(n);
	}
	
	public void addTips(double t){
		this.tips += t;
	}

	public String getName(){
		return super.getName();
	}
	
	public double getTips(){
		return tips;
	}
	
	public Table[] getAssigned(){
		return assigned;
	}
	
	public int getNumTables(){
		return numTables;
	}
}
