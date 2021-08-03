package cogent.go.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import cogent.go.entities.Cart;

@CrossOrigin()
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
