/*
 *
 * @author: Suresh Bhaskaran
 *
 * $Id$
 *
 */
package com.mastercard.city.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CityService {

	@Value("${city.names}")
	private List<String> cities;
	
	private Map<String, Set<String>> theMap;

	/**
	 * init: Make a HashMap with key as city and set of cities that can be reached
	 */
	public void init() {
		for(String city:cities) {
			String[] fromTo = city.split("-");
			String from = fromTo[0]; 
			String to = fromTo[1];
			
			CityService.connect(from, to, theMap);
			CityService.connect(to, from, theMap);
		}
	}
	
	/**
	 * @param origin
	 * @param destination
	 * @return
	 */
	public String canConnect(String origin, String destination) {
		
		if(theMap == null) {
			 theMap = new HashMap<>();
			 this.init();
		}
		
		boolean originFound = false, destFound=false;
		for(String key:theMap.keySet()) {
			if(key.equalsIgnoreCase(origin.trim())) {
				originFound = true;
			}
			if(key.equalsIgnoreCase(destination.trim())) {
				destFound = true;
			}
		}
		if(!originFound) {
			return origin + " Not Found"; 
		}
		if(!destFound) {
			return destination+ " Not Found"; 
		}
		
		Set<String> alreadyTested = new HashSet<>();
		Set<String> testFurther = new HashSet<>();
		return testConnectivity(origin, origin, destination, alreadyTested, testFurther);
	}

	/**
	 * @param origin
	 * @param destination
	 * @return
	 */
	private String testConnectivity(String origin, String from, String destination, Set<String> alreadyTested, Set<String> testFurther) {
		
		Set<String> toSet = theMap.get(from);
		for(String city:toSet) {
			if(alreadyTested.contains(city)) {
				continue;
			}
			if(city.equals(destination)) {
				return "yes";
			}else if(!city.equals(origin)){
				testFurther.add(city);
			}
			alreadyTested.add(city);
		}
		
		for(String city:testFurther) {
			testFurther.remove(city);
			return testConnectivity(origin, city, destination, alreadyTested, testFurther);
		}
		
		return "no";
	}
	
	/**
	 * init: Make a HashMap with key as city and set of cities that can be reached
	 * @param from
	 * @param to
	 * @param theMap
	 * @return
	 */
	static Map<String, Set<String>> connect(String from, String to, Map<String, Set<String>> theMap) {
		
		Set<String> toSet = theMap.get(from);
		if(toSet == null) {
			toSet = new HashSet<>();
			theMap.put(from, toSet);
		}
		toSet.add(to);
		
		return theMap;
	}

}