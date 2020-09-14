
public class Chef extends Employee{
	private Dish[] assigned;
	private final int maxDish = 3;
	
	public Chef(){
		super();
		assigned = new Dish[maxDish];
	}
	
	public Chef(String n){
		super(n);
		assigned = new Dish[maxDish];
	}
	
	public void assignDish(Dish newDish){
		boolean done = false;
		for(int i = 0; i < this.maxDish; i ++){
			if(assigned[i] == null && done == false){
				assigned[i] = newDish;
				done = true;
				System.out.println("Addition of assignment successful.");
			}
		}
		if(done == false)
			System.out.println("Chef has too many assignments. Cannot assign more dishes.");
	}
	
	public void removeDish(Dish oldDish){
		boolean done = false;
		for(int i = 0; i < this.maxDish; i ++){
			if(assigned[i] == oldDish && done == false){
				assigned[i] = null;
				done = true;
				System.out.println("Remove of assignment successful.");
			}
		}
		if(done == false)
			System.out.println("Dish not found in Chef's assignments.");
	}

	public void setName(String n){
		super.setName(n);
	}

	public String getName(){
		return super.getName();
	}
	

}
