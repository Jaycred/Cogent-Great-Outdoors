package cogent.go.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cogent.go.security.service.*;
import cogent.go.dao.CartRepository;
import cogent.go.dao.CustomerQueryRepository;
import cogent.go.dao.DeliveryAddressRepository;
import cogent.go.dao.OrderRepository;
import cogent.go.dao.ProductRepository;
import cogent.go.dao.UserRepository;
import cogent.go.entities.Cart;
import cogent.go.entities.CustomerQuery;
import cogent.go.entities.DeliveryAddress;
import cogent.go.entities.Order;
import cogent.go.entities.Product;
import cogent.go.entities.User;

@Service
public class GoService implements UserDetailsService{
	
	@Autowired
	private ProductRepository productRep;
	@Autowired
	private CartRepository cartRep;
	@Autowired
	private UserRepository userRep;
	@Autowired
	private CustomerQueryRepository custQueryRep;
	@Autowired
	private DeliveryAddressRepository addressRep;
	@Autowired
	private OrderRepository orderRep;
	
	public Product saveProduct(Product product) {
        return productRep.save(product);
    }
	public Cart saveCart(Cart cart) {
        return cartRep.save(cart);
    }
	public CustomerQuery saveQuery(CustomerQuery query) {
        return custQueryRep.save(query);
    }
	public Order saveOrder(Order order) {
        return orderRep.save(order);
    }
	public DeliveryAddress saveAddress(DeliveryAddress address) {
        return addressRep.save(address);
    }
	public User saveUser(User user) {
        return userRep.save(user);
    }
	public List<Product> getProductList(){
		return productRep.findAll();
	}
	public List<Product> getProductById(int id){
		List<Product> product = new ArrayList<>();
		product.add(productRep.findById(id).get());
		return product;
	}
	public List<Product> getProductByCategory(String category){
		return productRep.findByCategory(category);
	}
	public List<Cart> getCartList() {
		return cartRep.findAll();
	}
	public Optional<Cart> getCartById(int id){
		return cartRep.findById(id);
	}
	public List<Cart> getCartByUserId(int id){
		User user = userRep.getById(id);
		return cartRep.findByUser(user);
	}
	
	public User login(String email, String password) {
		Optional<User> user = userRep.findByEmail(email);
		User u1 = new User();
		if(user.isPresent()) {
			u1 = user.get();
		}
		return u1;
	}
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRep.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
		return JwtUserDetailsImpl.build(user);
	}
	
}
