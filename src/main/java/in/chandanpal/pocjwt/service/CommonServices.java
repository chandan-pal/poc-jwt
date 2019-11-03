package in.chandanpal.pocjwt.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonServices {
	
	@GetMapping("/")
	public String sayHi() {
		return "Hi!";
	}
}
