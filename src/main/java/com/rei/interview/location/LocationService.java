package com.rei.interview.location;

import com.rei.interview.rs.Location;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationService {

	/**
	 * I decided not to use this method to get Nearby stores because I don't have enough information to made
	 * a good Location service, I don't can know what stores are near to what location, this method always
     * return the same two stores for any given location, and also I don't have information for obtaining the inventory 
     * from the store, so I decided just to make an inventory validation just using concurrent location.
     * We need to review this definition and work on it for useful Location Service.
	 * @param location is never used
	 * @return two stores Seattle and Issaquah
	 */
    public List<Store> getNearbyStores(Location location) {
        return List.of(new Store("Seattle"), new Store("Issaquah"));
    }
}
