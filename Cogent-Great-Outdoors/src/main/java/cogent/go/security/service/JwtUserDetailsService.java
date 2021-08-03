/*
package cogent.go.security.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    //DI for DAO
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//dao.findByUsername(username)
		if ("nitin".equals(username)) {
			return new User("nitin", "$2a$10$IgjknFWnHQ6eBa5v81mtb.L/G0M8fZuXfFHq4ZkAjwLrVM3Mw1ZAK",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}
*/