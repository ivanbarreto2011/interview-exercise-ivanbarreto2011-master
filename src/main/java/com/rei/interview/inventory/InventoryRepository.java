package com.rei.interview.inventory;

import com.rei.interview.util.Cache;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.Map;

@Repository
public class InventoryRepository {

    private Map<String, Inventory> inventories = new Cache<>();

    public void addInventory(Inventory inventory) {
    	inventories.put(inventory.getKey(), inventory);
    }

    public Inventory getInventory(String productId,String location) {
        return inventories.get(productId +"-"+location);
    }

    public Collection<Inventory> getAll() {
        return inventories.values();
    }
    
    /**
     * This method allows loading the inventory from the CSV document just for load in memory on the repository 
     * @throws IOException
     */
    public void loadInventory() throws IOException {
	    try(Reader in = new InputStreamReader(getClass().getResourceAsStream("/product_inventory.csv"))) {
	        Iterable<CSVRecord> records = CSVFormat.DEFAULT
	                .withHeader("productId", "location", "quantity")
	                .withFirstRecordAsHeader()
	                .parse(in);
	
	        for (CSVRecord recordProducts : records) {
	        	Inventory inventory = new Inventory();
	        	inventory.setProductId(recordProducts.get("productId"));
	        	inventory.setLocation(recordProducts.get("location"));
	        	inventory.setQuantity(Integer.parseInt(recordProducts.get("quantity")));
	        	this.addInventory(inventory);
	        }
	    }
    }
}
