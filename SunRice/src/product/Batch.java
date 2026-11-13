package product;

public class Batch {
	//attributes
	private int id;
	private String mfd;
	private String exp;
	
	//constructor
	public Batch(int id, String mfd, String exp) {
		this.id = id;
		this.mfd = mfd;
		this.exp = exp;
	}
	
	//methods
	
	//getters
	public int getID() {
		return this.id;
	}
	public String getMFD() {
		return this.mfd;
	}
	public String getEXP() {
		return this.exp;
	}
}
