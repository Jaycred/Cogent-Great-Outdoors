package cogent.go.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.go.dao.UserRepository;
import cogent.go.entities.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User saveProduct(User user) {
        return repository.save(user);
    }
}
