
public class Dish {
	private String name;
	private double price;

	public Dish(){
		name = "default";
		price = -1;
	}

	public Dish(String n, String[] ing, double p){
		name = n;
		price = p;
	}

	public Dish(String n, double p){
		name = n;
		price = p;
	}

	public void setName(String n){
		name = n;
	}

	public void setPrice(double p){
		price = p;
	}

	public String getName(){
		return name;
	}

	public double getPrice(){
		return price;
	}

}
