package cogent.go.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.go.dao.CartRepository;
import cogent.go.entities.Cart;

@Service
public class CartService {
	
	@Autowired
	private CartRepository repository;
	
	public Cart saveCart(Cart cart) {
        return repository.save(cart);
    }
}
