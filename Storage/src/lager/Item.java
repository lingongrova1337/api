package lager;

public class Item {

	private String type;
	private String maker;
	private double outPrice;
	private double inPrice;
	private int quantity;

	public Item(String type, String maker, double outPrice, double inPrice, int quantity) {
		super();
		this.type = type;
		this.maker = maker;
		this.outPrice = outPrice;
		this.inPrice = inPrice;
		this.quantity = quantity;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getMaker() {
		return maker;
	}
	
	public void setMaker(String maker) {
		this.maker = maker;
	}
	
	public double getOutPrice() {
		return outPrice;
	}
	
	public void setOutPrice(double outPrice) {
		this.outPrice = outPrice;
	}
	
	public double getInPrice() {
		return inPrice;
	}
	
	public void setInPrice(double inPrice) {
		this.inPrice = inPrice;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		
	}	
	
	public boolean sellItem(int quantity) {
		boolean ok=false;
		if((this.quantity - quantity) >=0){
			this.quantity-=quantity;
			ok=true;
			//setQuantity(this.quantity);
		}	
		return ok;
	}
	
	public boolean addItemQuantity(int quantity) {
		boolean ok=false;
		if((this.quantity + quantity) <= 120){
			this.quantity += quantity;
			ok=true;
			//setQuantity(this.quantity);
		} else System.out.println("För många items");
		return ok;
	}

	@Override
	public String toString() {
		return "type=" + type + ", maker=" + maker + ", outPrice=" + outPrice + ", inPrice=" + inPrice
				+ ", quantity=" + quantity + " ";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (Double.doubleToLongBits(inPrice) != Double.doubleToLongBits(other.inPrice))
			return false;
		if (maker == null) {
			if (other.maker != null)
				return false;
		} else if (!maker.equals(other.maker))
			return false;
		if (Double.doubleToLongBits(outPrice) != Double.doubleToLongBits(other.outPrice))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
