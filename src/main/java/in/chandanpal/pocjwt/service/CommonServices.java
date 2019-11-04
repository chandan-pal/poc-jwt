package in.chandanpal.pocjwt.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.chandanpal.pocjwt.model.AuthenticationRequest;
import in.chandanpal.pocjwt.model.AuthenticationResponse;
import in.chandanpal.pocjwt.model.User;
import in.chandanpal.pocjwt.util.JwtUtil;

@RestController
public class CommonServices {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@GetMapping("/")
	public void home(HttpServletResponse response) throws IOException {
		response.sendRedirect("/hello");
	}
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello!";
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		System.out.println("Entered login controller...");
		try 
		{
			authenticationManager.authenticate
			(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		System.out.println("authenticationRequest.getUsername()=" + authenticationRequest.getUsername());
		final User user = userService.findUserByEmail(authenticationRequest.getUsername());
		final String jwtToken = jwtUtil.generateToken(user);
		System.out.println("jwtToken=" + jwtToken);
		return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
	}
}
