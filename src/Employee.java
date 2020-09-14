
public class Employee {
	private String name;
	
	public Employee(){
		name = "default";
	}

	public Employee(String name){
		this.name = name;
	}
	
	public void setName(String n){
		this.name = n;
	}
	
	public String getName(){
		return this.name;
	}
	
}
