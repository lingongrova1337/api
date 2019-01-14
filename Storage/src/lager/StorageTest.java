package lager;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StorageTest {

	private Storage storage;

	@Before
	public void setUp() throws Exception {
		this.storage = new Storage();
	}

	@Test
	public void sizeOfStorageTest() {
		assertEquals(0, storage.getStoredItems());
		storage.addItem(new Tv("LED", "Philips", 10.0, 5.0, 1, 10.0, 12000, "En billig tv"));
		assertEquals(1, storage.getStoredItems());
		storage.addItem(new ComputerScreen("LED", "Sony", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI")));
		assertEquals(2, storage.getStoredItems());
		assertEquals(11, storage.getSize());
		
	}
	
	@Test
	public void removeList() {
		assertEquals(0, storage.getStoredItems());
		Tv tv = new Tv("LED", "Philips", 10.0, 5.0, 1, 10.0, 12000, "En billig tv");
		storage.addItem(tv);
		assertEquals(1, storage.getStoredItems());
		storage.deleteItem(tv);
		assertEquals(0, storage.getStoredItems());
		
		
	}
	
	@Test
	public void quantityAfterSelling() {
		
		Tv tv = new Tv("LED", "Philips", 10.0, 5.0, 10, 10.0, 12000, "En billig tv");
		storage.addItem(tv);
		ComputerScreen cs = new  ComputerScreen("LED", "Sony", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI"));
		storage.addItem(cs);
		
		storage.sellItem(tv, 8);
		assertEquals(2,tv.getQuantity());
		assertEquals(12,storage.getSize());
		
	}
	
	@Test
	public void quantityAfterAdding() {
		
		Tv tv = new Tv("LED", "Philips", 10.0, 5.0, 10, 10.0, 12000, "En billig tv");
		
		storage.addItem(tv);
		ComputerScreen cs = new  ComputerScreen("LED", "Sony", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI"));
		storage.addItem(cs);
		
		assertEquals(20, storage.getSize());
		storage.addItemQuantity(tv, 111);
		assertEquals(130, storage.getSize());
		storage.sellItem(cs, 7);
		assertEquals(123, storage.getSize());
		
	}

	@Test
	public void listAll() {
		
		Tv tv1 = new Tv("LED", "Philips", 10.0, 5.0, 10, 10.0, 12000, "En billig tv");
		storage.addItem(tv1);

		Tv tv = new Tv("LED", "Samsung", 10.0, 5.0, 10, 10.0, 12000, "En billig tv");
		storage.addItem(tv);
		ComputerScreen cs = new  ComputerScreen("LED", "Sony", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI"));
		storage.addItem(cs);
		
		storage.printAllItems();

		//assertEquals(20, storage.getSize());
		
		storage.addItemQuantity(tv1, 30);
		//assertEquals(130, storage.getSize());
		//storage.sellItem(cs, 7);
		//assertEquals(123, storage.getSize());
		
		storage.printAllItems();
	}
	
	
	@Test
	public void storageInValue() {
		ComputerScreen cs = new  ComputerScreen("LED", "Sony", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI"));
		Tv tv = new Tv("LED", "Philips", 10.0, 5.0, 10, 10.0, 12000, "En billig tv");
		Tv tv2 = new Tv("LED", "LG", 10.0, 5.0, 10, 10.0, 12000, "En dyr tv");
		storage.addItem(tv);
		storage.addItem(tv2);
		storage.addItem(cs);
		assertEquals(160.0,storage.calcStorageTotValue(),0.0 );
		double val = storage.calcStorageTotValue();
		System.out.println(val);
		
		storage.sellItem(tv, 1);
		assertEquals(155.0,storage.calcStorageTotValue(),0.0 );
	}
	
	@Test
	public void storageSellValue() {
		ComputerScreen cs = new  ComputerScreen("LED", "Sony", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI"));
		Tv tv = new Tv("LED", "Philips", 10.0, 5.0, 10, 10.0, 12000, "En billig tv");
		Tv tv2 = new Tv("LED", "LG", 10.0, 5.0, 10, 10.0, 12000, "En dyr tv");
		storage.addItem(tv);
		storage.addItem(tv2);
		storage.addItem(cs);
		//11*10
		//10*10*2 
		// = 310
		assertEquals(310.0, storage.calcStorageSalesValue(),0.0);
		double val = storage.calcStorageSalesValue();
		System.out.println(val);
		
		storage.addItem(tv2);
		assertEquals(410.0,storage.calcStorageSalesValue(),0.0);
		
		storage.addItemQuantity(tv2, 4);
		assertEquals(450,storage.calcStorageSalesValue(),0);
		double val1 = storage.calcStorageSalesValue();
		System.out.println(val1);
	}
	
	@Test
	public void listAllTypes() {
		ComputerScreen cs1 = new  ComputerScreen("LED", "Sony", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI"));
		ComputerScreen cs2 = new  ComputerScreen("LED", "Dell", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI"));
		ComputerScreen cs3 = new  ComputerScreen("LED", "BenQ", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI"));
		
		Tv tv1 = new Tv("LED", "Philips", 10.0, 5.0, 10, 10.0, 12000, "En billig tv");
		Tv tv2 = new Tv("LED", "LG", 10.0, 5.0, 10, 10.0, 12000, "En dyr tv");
		Tv tv3 = new Tv("LED", "Samsung", 10.0, 5.0, 10, 10.0, 12000, "En billig tv");
		Tv tv4 = new Tv("LED", "Sony", 10.0, 5.0, 10, 10.0, 12000, "En dyr tv");
		
		storage.addItem(tv1);
		storage.addItem(tv2);
		storage.addItem(tv3);
		storage.addItem(tv4);
		storage.addItem(cs1);
		storage.addItem(cs2);
		storage.addItem(cs3);
		
		storage.printComponentTypes();
	}
	
	@Test
	public void updateAll() {
		ComputerScreen cs1 = new  ComputerScreen("LED", "Sony", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI"));
		ComputerScreen cs2 = new  ComputerScreen("LED", "Dell", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI"));
		ComputerScreen cs3 = new  ComputerScreen("LED", "BenQ", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI"));
		ComputerScreen cs4 = new  ComputerScreen("LED", "BenQ", 11.0, 6.0, 7, 10.0, 12 , "bc", Arrays.asList("RGB", "VGA"));
		Tv tv1 = new Tv("LED", "Philips", 10.0, 5.0, 10, 10.0, 12000, "En billig tv");
		Tv tv2 = new Tv("LED", "LG", 10.0, 5.0, 10, 10.0, 12000, "En dyr tv");
		Tv tv3 = new Tv("LED", "Samsung", 10.0, 5.0, 10, 10.0, 12000, "En billig tv");
		Tv tv4 = new Tv("LED", "Sony", 10.0, 5.0, 10, 10.0, 12000, "En dyr tv");
		
		storage.addItem(tv1);
		storage.addItem(tv2);
		storage.addItem(tv3);
		storage.addItem(tv4);
		storage.addItem(cs1);
		storage.addItem(cs2);
		storage.addItem(cs3);
		
		storage.printComponentTypes();
		
		Tv tv5 = new Tv("LCD", "Hitachi", 30.0, 6.0, 7, 43.0, 12, "En tv med lite garanti");
		
		storage.updateAll(tv1, tv5);
		storage.printComponentTypes();
		
		storage.updateAll(cs3, cs4);
		storage.printComponentTypes();
		
		System.out.println(cs3.getConnect());
		
		
	}
	@Test
	public void setDiscountAll() {
		ComputerScreen cs = new  ComputerScreen("LED", "Sony", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI"));
		Tv tv = new Tv("LED", "Philips", 10.0, 5.0, 10, 10.0, 12000, "En billig tv");
		Tv tv2 = new Tv("LED", "LG", 10.0, 5.0, 10, 10.0, 12000, "En dyr tv");
		storage.addItem(tv);
		storage.addItem(tv2);
		storage.addItem(cs);
		//11*.7*10=77
		//10*0,7*10*2 = 140 
		// 217
		//storage.setDiscountAll(0.3);
		assertEquals(310.0, storage.calcStorageSalesValue(),0.0);
		double val = storage.calcStorageSalesValue();
		System.out.println(val);
		
		//storage.addItem(tv2);
		storage.setDiscountAll(0.3);
		assertEquals(217,storage.calcStorageSalesValue(),0);
		double val1 = storage.calcStorageSalesValue();
		System.out.println(val1);
	}
	@Test
	public void setDiscountGroup() {
		fill();
		System.out.println(storage.calcStorageSalesValue());
		storage.setDiscountGroup(1, 0.3);
		System.out.println(storage.calcStorageSalesValue());
	}
	
	@Test 
	public void setDiscountForUniqueProduct() {
		fill();
		//Tv tv4 = new Tv("LED", "Philips1", 10.0, 5.0, 10, 10.0, 12000, "En billig tv");
		//storage.addItem(tv1);
		//Tv tv = new Tv("LED", "Philips", 10.0, 5.0, 10, 10.0, 12000, "En billig tv");
		System.out.println(storage.calcStorageSalesValue());
		storage.setDiscountForUniqueProduct(new Tv("LED", "Philips", 10.0, 5.0, 10, 10.0, 12000, "En billig tv"), 0.2);
		System.out.println(storage.calcStorageSalesValue());
		
	}
	
	@Test
	public void searchTest() {
		ComputerScreen cs1 = new  ComputerScreen("LED", "Sony", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI"));
		ComputerScreen cs2 = new  ComputerScreen("LED", "Dell", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI"));
		ComputerScreen cs3 = new  ComputerScreen("LED", "BenQ", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI"));

		Tv tv1 = new Tv("LED", "Philips", 10.0, 5.0, 10, 10.0, 12000, "En billig tv");
		Tv tv2 = new Tv("LED", "LG", 10.0, 5.0, 10, 10.0, 12000, "En dyr tv");
		Tv tv3 = new Tv("LED", "Samsung", 10.0, 5.0, 10, 10.0, 12000, "En billig tv");
		Tv tv4 = new Tv("LED", "Sony", 10.0, 5.0, 10, 10.0, 12000, "En dyr tv");
		
		storage.addItem(tv1);
		storage.addItem(tv2);
		storage.addItem(tv3);
		storage.addItem(tv4);
		storage.addItem(cs1);
		storage.addItem(cs2);
		storage.addItem(cs3);
		
		List<Item> resultItems = storage.search("Sony");
		for (Item item : resultItems) {
			System.out.println(item);
		}
	}
	
	
	
	
	public void fill() {
		ComputerScreen cs = new  ComputerScreen("LED", "Sony", 11.0, 6.0, 10, 10.0, 12 , "bc", Arrays.asList("HDMI"));
		Tv tv = new Tv("LED", "Philips", 10.0, 5.0, 10, 10.0, 12000, "En billig tv");
		Tv tv2 = new Tv("LED", "LG", 10.0, 5.0, 10, 10.0, 12000, "En dyr tv");
		storage.addItem(tv);
		storage.addItem(tv2);
		storage.addItem(cs);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
