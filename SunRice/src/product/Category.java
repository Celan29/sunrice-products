package product;

public class Category {
	//attributes
	private String name;
	private String description;
	
	//constructor
	public Category(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	//methods
	
	//getters
	public String getName() {
		return this.name;
	}
	public String getDescription() {
		return this.description;
	}
	//setters
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
