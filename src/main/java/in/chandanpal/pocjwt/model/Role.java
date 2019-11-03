package in.chandanpal.pocjwt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
	
	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int role_id;
	
	private String role_name;
}
