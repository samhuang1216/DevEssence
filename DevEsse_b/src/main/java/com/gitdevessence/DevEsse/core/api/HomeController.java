package com.gitdevessence.DevEsse.core.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HomeController {

	@GetMapping("/hh")
	public String getMethodName(@RequestParam String param) {

		return new String();
	}

	public String home() {
		return "home";

	}

}
