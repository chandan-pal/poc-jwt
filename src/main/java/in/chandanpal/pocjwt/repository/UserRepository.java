package in.chandanpal.pocjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.chandanpal.pocjwt.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
	
}
