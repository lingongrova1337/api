package lager;

import java.util.ArrayList;
import java.util.List;

public class Storage {

	private int totalSize;
	private List<Item> items;

	public Storage() {
		this.items = new ArrayList<>();
	}

	public void addItem(Item newItem) {

		if (!items.contains(newItem)) {
			this.items.add(newItem);
			this.totalSize=newItem.getQuantity()+totalSize;
		}
		else {			
			this.items.get(items.indexOf(newItem)).addItemQuantity(newItem.getQuantity());
			//this.items.set(this.items.indexOf(oldItem), oldItem);
		}
	}

	public int getStoredItems() {
		return items.size();
	}

	public int getSize() {
		return this.totalSize;

	}

	public void deleteItem(Item item) {
		this.items.remove(item);
	}

	public void sellItem(Item item, int quantity) {
		if(item.sellItem(quantity)) {
			this.totalSize -= quantity;
			System.out.println("Köpet genomfördes");
		}	
	}

	public void addItemQuantity(Item item, int quantity) {
		if(item.addItemQuantity(quantity)) {
			this.totalSize += quantity;
			System.out.println("Inköp gjort");
		}
	}

	public void printAllItems() {
		for (Item item : items) {
			System.out.println(item.toString());
		}
	}

	public double calcStorageTotValue() {
		double sum=0;
		for(Item item :items) {
			double inPrice = item.getInPrice();
			double qty= item.getQuantity();
			sum += qty * inPrice;
		}
		return sum;
	}

	public double calcStorageSalesValue() {
		double sum=0;
		for(Item item :items) {
			double OutPrice = item.getOutPrice();
			double qty= item.getQuantity();
			sum += qty * OutPrice;
		}
		return sum;
	}

	public void printComponentTypes() {
		int tvTypes = 0, tvs = 0;
		int screenTypes = 0, screens = 0;
		for (Item item : items) {
			if (item instanceof Tv) {
				tvTypes++;
				tvs += item.getQuantity();
			}
			else if (item instanceof ComputerScreen) {
				screenTypes++;
				screens += item.getQuantity();
			}
		}
		System.out.println("Antal Tv-modeller: " + tvTypes + " st, totalt antal: " + tvs + 
				" st\nAntal skärmmodeller: " + screenTypes + " st, totalt antal: " + screens + " st");
	}

	public Object updateAll(Item oldItem, Item newItem) {
		String type =newItem .getType();
		String maker = newItem.getMaker();
		double outPrice = newItem.getOutPrice();
		double inPrice = newItem.getInPrice();
		int quantity = newItem.getQuantity();

		oldItem.setType(type);
		oldItem.setMaker(maker);
		oldItem.setOutPrice(outPrice);
		oldItem.setInPrice(inPrice);
		oldItem.setQuantity(quantity);

		if(newItem instanceof Tv) {
			((Tv) oldItem).setSize(((Tv) newItem).getSize());
			((Tv) oldItem).setWarranty(((Tv) newItem).getWarranty());
			((Tv) oldItem).setDesc(((Tv) newItem).getDesc());


		} else if(newItem instanceof ComputerScreen) {
			((ComputerScreen) oldItem).setSize(((ComputerScreen) newItem).getSize());
			((ComputerScreen) oldItem).setWarranty(((ComputerScreen) newItem).getWarranty());
			((ComputerScreen) oldItem).setDesc(((ComputerScreen) newItem).getDesc());
			((ComputerScreen) oldItem).setConnect(((ComputerScreen) newItem).getConnect());

		}

		return oldItem;
	}

	public void setDiscountAll(double discount) {
		for (Item item:items) {
			if(item.getOutPrice()*(1-discount)>item.getInPrice()){
				item.setOutPrice(item.getOutPrice()*(1-discount));
			}
		}		
	}

	public void setDiscountGroup(int select, double discount) {
		switch(select) {
		case 1: //tv
			for(Item item : items) {
				if(item instanceof Tv) {
					if(item.getOutPrice()*(1-discount)>= item.getInPrice()){
						item.setOutPrice(item.getOutPrice()*(1-discount));
					}
				}
			}
			break;

		case 2: //CompScreen
			for(Item item : items) {
				if(item instanceof ComputerScreen) {
					if(item.getOutPrice()*(1-discount)>= item.getInPrice()){
						item.setOutPrice(item.getOutPrice()*(1-discount));
					}
				}
			}
			break;

		case 3:
			break;
		}

	}
	public void setDiscountForUniqueProduct(Item item, double discount) {
		for (Item i: items) {
			if(i.equals(item)) {
				
				if (i.getOutPrice()*(1-discount)>i.getInPrice()) {
				
					i.setOutPrice(i.getOutPrice()*(1-discount));
		}
		}
		}
	}
	// String, Integer, Double
	public List<Item> search(String stringValue) {
		List<Item> resultItems = new ArrayList<>();
		for (Item item : items) {
			if (item.getMaker().equalsIgnoreCase(stringValue)) {
				resultItems.add(item);
			}
			//String type;
			//private String maker;
		}
		return resultItems;
	}

	public List<Item> search(Integer integerValue) {
		return null;
	}

	public List<Item> search(Double doubleValue) {
		return null;
	}
}
