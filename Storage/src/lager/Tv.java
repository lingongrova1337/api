package lager;

public class Tv extends Item {
	
	private double size;
	//Warranty in months
	private int warranty;
	private String desc;
	
	public Tv(String type, String maker, double outPrice, double inPrice, int quantity, double size, int warranty, String desc) {
		super(type, maker, outPrice, inPrice, quantity);
		this.size = size;
		this.warranty = warranty;
		this.desc = desc;
	}
	
	public double getSize() {
		return size;
	}
	
	public void setSize(double size) {
		this.size = size;
	}
	
	public int getWarranty() {
		return warranty;
	}
	
	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Tv " + super.toString() + " size=" + size + ", warranty=" + warranty + ", desc=" + desc;
	}	
}
