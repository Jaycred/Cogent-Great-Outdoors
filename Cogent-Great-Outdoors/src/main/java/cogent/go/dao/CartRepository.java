package cogent.go.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import cogent.go.entities.Cart;

@CrossOrigin()
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
