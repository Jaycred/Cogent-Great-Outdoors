package cogent.go.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import cogent.go.entities.Cart;
import cogent.go.entities.User;

@CrossOrigin()
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	public List<Cart> findByUser(User user);
	public List<Cart> findAll();
	//
}
