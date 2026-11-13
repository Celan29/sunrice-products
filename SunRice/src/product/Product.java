package product;

//NOTES:
//Enabled functions so far: setters and getters, addStock, reduceStock
//Existing product names and category names must not be duplicated
//Products are added and subtracted through ProductCatalog based on the product name and not the entire product itself.
//(this is because the same products don't necessarily have the same mfds and exps)

public class Product {
	//attributes
	public static final int MAX_QUANTITY = 100;
	private Category category;
	private String name;
	private String description;
	private Batch batch; //contains manufacturing date and expiration date
	private double price;
	private int stock;
	
	//constructor
	public Product(Category category, String name, String description, Batch batch, double price, int quantity) {
		this.category = category;
		this.name = name;
		this.description = description;
		this.batch = batch;
		this.price = price;
		this.stock = quantity;
	}
	
	//methods
	
	//filling/refilling
	public boolean addStock(int quantity) {
		if (this.stock == MAX_QUANTITY) {
			System.out.println("Product is fully stocked.");
			return false;
		}
		else if (this.stock + quantity > MAX_QUANTITY) {
			System.out.println("Inappropriate quantity.");
			return false;
		}
		
		this.stock += quantity;
		return true;
	}
	
	//reducing current stock
	public boolean reduceStock(int quantity) {
		if (this.stock == 0) {
			System.out.println("Product is out of stock.");
			return false;
		}
		else if (this.stock - quantity < 0) {
			System.out.println("Inappropriate quantity.");
			return false;
		}
		
		this.stock -= quantity;
		return true;
	}
	
	//getters and setters
	
	//getters
	public Category getCategory() {
		return this.category;
	}
	public String getName() {
		return this.name;
	}
	public String getDescription() {
		return this.description;
	}
	public Batch getBatch() {
		return this.batch;
	}
	public double getPrice() {
		return this.price;
	}
	public int getStock() {
		return this.stock;
	}
	
	//setters
	public void setCategory(Category category) {
		this.category = category;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
