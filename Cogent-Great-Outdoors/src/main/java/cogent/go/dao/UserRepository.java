package cogent.go.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import cogent.go.entities.User;

@CrossOrigin()
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Page<User> findByFirstNameContaining(@RequestParam("firstName") String name, Pageable pageable);
	
	User findUserByEmail(@RequestParam("email") String email);
}
