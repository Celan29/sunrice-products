package product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductCatalog {
	//attributes
	private ArrayList<Product> products;
	private ArrayList<Category> categories;
	private Map<String, Integer> productCount;
	
	
	//constructor
	public ProductCatalog() {
		this.products = new ArrayList<>();
		this.productCount = new HashMap<>();
	}
	
	//methods
	
	//productView (assumes product exists, use searchProduct to determine parameter)
	public void productView(Product product1) {
		String productName = product1.getName();
		
		System.out.println(productName);
		System.out.println("Current Stock: " + this.productCount.get(productName));
		System.out.println("Existing Batches: ");
		
		for (Product product : this.products) {
			if (product.getName().equals(productName)) {
				System.out.println("Batch " + product.getBatch().getID() + product.getStock());
				System.out.println("MFD: " + product.getBatch().getMFD());
				System.out.println("EXP: " + product.getBatch().getEXP() + "\n");
			}
		}
		System.out.println(product1.getDescription());
		System.out.println("Established Price: " + product1.getPrice());
		
	}
	
	//createCategory
	public void createCategory(String name, String description) {
		Category category = new Category(name, description);
		this.categories.add(category);
	}
	
	//searchCategory
	public Category searchCategory(String name) {
		for (Category category : this.categories) {
			if (category.getName().equals(name)) {
				return category;
			}
		}
		return null;
	}
	
	//createProduct
	public void createProduct(Category category, String name, String description, String mfd, String exp, double price, int quantity) {
		
		Batch newBatch1 = new Batch(1, mfd, exp);
		Product newProduct = new Product(category, name, description, newBatch1, price, quantity);
				
		this.products.add(newProduct);
		this.productCount.put(name, quantity);
		System.out.println("Successfully introduced " + name + "!");
	}
	
	//deleteProduct
	public void deleteProduct(String name) {
		Product product = searchProduct(name);
		
		if (product == null) {
			System.out.println(name + " does not exist.");
		}
		else {
			ArrayList<Product> batches = getAllBatches(name);
			for (Product p : batches) {
				this.products.remove(p);
			}
			this.productCount.remove(name);
			System.out.println("Successfully removed " + name + "!");
		}
	}
	
	//reduceProduct 
	public void reduceProduct(String name, String mfd, String exp, int quantity) {
		Product product = searchBatch(name, mfd, exp);
		
		if (product == null) {
			System.out.println("No matching products.");
		}
		else {
			boolean attempt = product.reduceStock(quantity);
			
			if (attempt == true) {
				System.out.println("Successfully extracted " + quantity + " " + name + "!");
			}
			else {
				System.out.println(name + "extraction unsuccessful.");
			}
			this.productCount.put(name, this.productCount.get(name) - quantity);
		}
		
	}
	
	//searchProduct (returns first product matching input name)
	public Product searchProduct(String name) {
		for (Product product : this.products) {
			if (product.getName().equals(name)) {
				return product;
			}
		}
		return null;
	}
	
	//getNewID (used by addProduct if existing product batches do not match)
	private int getNewID(String name) {
		int id = 0;
		
		for (Product product : this.products) {
			if (product.getName().equals(name)) {
				id = product.getBatch().getID();	
			}
		}
		return id++;
	}
	//getAllBatches
	private ArrayList<Product> getAllBatches(String name) {
		ArrayList<Product> batches = new ArrayList<>();
		
		for (Product product : this.products) {
			if (product.getName().equals(name)) {
				batches.add(product);	
			}
		}
		return batches;
	}
	//searchBatch (used by addProduct and reduceProduct)
	private Product searchBatch(String name, String mfd, String exp) {
		ArrayList<Product> batches = getAllBatches(name);
		
		for (Product product: batches) {
			if (product.getBatch().getMFD().equals(mfd) && product.getBatch().getEXP().equals(exp)) {
				return product;
			}
			else {
				continue;
			}
		}
		return null;
	}
	
	//addProduct (assumes product exists)
	public void addProduct(String name, String mfd, String exp, int quantity) {
		Product product = searchBatch(name, mfd, exp);
		
		if (product != null) {
			boolean attempt = product.addStock(quantity);
			
			if (attempt == true) {
				System.out.println(name + " successfully stocked!");
			}
			else {
				System.out.println(name + "resupplying unsuccessful.");
			}
			this.productCount.put(name, this.productCount.get(name) + quantity);
		}
		else {
			int id = getNewID(name);
			Batch newBatch = new Batch(id, mfd, exp);
			Product target = searchProduct(name);
			Category category = target.getCategory();
			String description = target.getDescription();
			double price = target.getPrice();
			
			this.products.add(new Product(category, name, description, newBatch, price, quantity));
			this.productCount.put(name, this.productCount.get(name) + quantity);
			
		}
	}
	
	
}
