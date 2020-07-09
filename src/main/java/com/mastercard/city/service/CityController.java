package com.mastercard.city.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

	@Autowired
	private CityService service;

	@RequestMapping("/connected")
	public String connect(@RequestParam String origin, @RequestParam String destination) {
		return service.canConnect(origin, destination);
	}

}
