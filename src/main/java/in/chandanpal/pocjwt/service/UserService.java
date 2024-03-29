package in.chandanpal.pocjwt.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.chandanpal.pocjwt.model.User;
import in.chandanpal.pocjwt.repository.UserRepository;

@Service("userService")
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
    
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public Optional<User> findUserById(long userId) {
        return userRepository.findById(userId);
    }
}