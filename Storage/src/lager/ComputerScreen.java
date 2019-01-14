package lager;

import java.util.List;

public class ComputerScreen extends Item {
	
	private double size;
	//Warranty in months
	private int warranty;
	private String desc;
	private List<String> connect;
	
	public ComputerScreen(String type, String maker, double outPrice, double inPrice, int quantity, double size,
			int warranty, String desc, List<String> list) {
		super(type, maker, outPrice, inPrice, quantity);
		this.size = size;
		this.warranty = warranty;
		this.desc = desc;
		this.connect = list;
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

	public List<String> getConnect() {
		return connect;
	}

	public void setConnect(List<String> connect) {
		this.connect = connect;
	}

	@Override
	public String toString() {
		return "ComputerScreen " + super.toString() + " size=" + size + ", warranty=" + warranty + ", desc=" + desc + ", connect=" + connect;
	}
}
