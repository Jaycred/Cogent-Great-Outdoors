package cogent.go.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.go.dao.OrderRepository;
import cogent.go.entities.Order;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public Order saveProduct(Order order) {
        return repository.save(order);
    }
}
