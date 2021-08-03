package cogent.go.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import cogent.go.entities.Order;

@CrossOrigin()
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
