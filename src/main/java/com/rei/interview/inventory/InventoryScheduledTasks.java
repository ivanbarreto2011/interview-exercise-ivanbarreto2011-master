package com.rei.interview.inventory;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * Add the ability for us to get dynamic data
 * @author IBarreto
 *
 */
@Component
public class InventoryScheduledTasks {
	
	private static final Logger log = LoggerFactory.getLogger(InventoryScheduledTasks.class);
	private InventoryRepository inventoryRepository;
	
    @Autowired
    public InventoryScheduledTasks(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    
    /**
     * Load the inventory every 3 seconds
     * @throws IOException
     */
	@Scheduled(fixedRate = 3000)
	public void loadProducts() throws IOException{
			inventoryRepository.loadInventory();
            log.info("Inventory Load");
        		
	}
}
