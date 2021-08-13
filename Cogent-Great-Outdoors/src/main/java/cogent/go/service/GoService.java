package cogent.go.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import cogent.go.security.config.JwtRequestFilter;
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
	
	final Logger logger = LogManager.getLogger(GoService.class);
	
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
		logger.info("Saving new Product");
        return productRep.save(product);
    }
	public Cart saveCart(Cart cart) {
		logger.info("Saving changes to Cart");
        return cartRep.save(cart);
    }
	public CustomerQuery saveQuery(CustomerQuery query) {
		logger.info("Saving customer action");
        return custQueryRep.save(query);
    }
	public Order saveOrder(Order order) {
		logger.info("Saving order details");
        return orderRep.save(order);
    }
	public DeliveryAddress saveAddress(DeliveryAddress address) {
		logger.info("Saving delivery address");
        return addressRep.save(address);
    }
	public User saveUser(User user) {
		logger.info("Saving changes to user");
        return userRep.save(user);
    }
	public List<Product> getProductList(){
		logger.info("Fetching all Products");
		return productRep.findAll();
	}
	public List<Product> getProductById(int id){
		logger.info("Fetching Product #" + id);
		List<Product> product = new ArrayList<>();
		product.add(productRep.findById(id).get());
		return product;
	}
	public List<Product> getProductByCategory(String category){
		logger.info("Fetching Products by category " + category);
		return productRep.findByCategory(category);
	}
	public List<Cart> getCartList() {
		logger.info("Fetching all Carts");
		return cartRep.findAll();
	}
	public List<Cart> getCartById(int id){
		logger.info("Fetching Cart #" + id);
		return cartRep.findById(id);
	}
	public void deleteCart(Cart cart) {
		logger.info("Deleting Cart");
		cartRep.delete(cart);
	}
	public List<Cart> getCartByUserId(int id){
		logger.info("Fetching Cart for User #" + id);
		User user = userRep.getById(id);
		return cartRep.findByUser(user);
	}
	public User getUserById(int id) {
		logger.info("Fetching User by Id");
		return userRep.getById(id);
	}
	public List<User> getAllUsers(){
		logger.info("Fetching all Users");
		return userRep.findAll();
	}
	
	public DeliveryAddress getAddressById(int id) {
		logger.info("Fetching Address by Id");
		return addressRep.getById(id);
	}
	
	public User login(String email, String password) {
		Optional<User> user = userRep.findByEmail(email);
		User u1 = new User();
		if(user.isPresent()) {
			u1 = user.get();
			logger.info("Successful Login");
		}
		else
		{
			logger.error("Login Failed");
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
