package com.rei.interview.inventory;

import com.rei.interview.location.LocationService;
import com.rei.interview.product.Product;
import com.rei.interview.rs.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class InventoryService {

    
    private InventoryRepository inventoryRepository;
    
    private static final int threshold = 2;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    
    @PostConstruct
    public void readInInventory() throws IOException {
    	inventoryRepository.loadInventory();
    }
    
    /**
     * This method allows check if the product has inventory
     * @param product
     * @param quantity
     * @param currentLocation
     * @return true if the requested quantity of the product exists online or at the current location 
     */
    public boolean hasInventory(Product product, int quantity, Location currentLocation) {
    	
		    return hasInventoryOnline(product, quantity) || hasInventoryInCurrentLocation(product,quantity,currentLocation);
    }

    /**
     * This method allows to check the inventory online
     * @param product
     * @param quantity
     * @return true if the requested quantity of the product exists online
     */
    public boolean hasInventoryOnline(Product product, int quantity) {
    	Inventory inventory = inventoryRepository.getInventory(product.getProductId(), Location.ONLINE.name());
    	if(inventory!=null) {
    		return inventory.getQuantity()>=quantity;
    	}else {
    		return false;
    	}
    }

    /**
     * This method allows to check the inventory in the current location
     * @param product
     * @param quantity
     * @param currentLocation
     * @return true if there is a sufficient quantity of the product on the current location
     */
    public boolean hasInventoryInCurrentLocation(Product product, int quantity, Location currentLocation) {
    	Inventory inventory = inventoryRepository.getInventory(product.getProductId(), currentLocation.name());
    	if(inventory!=null) {
    		return inventory.getQuantity()>=quantity+threshold;
    	}else {
    		return false;
    	}
    }
}
