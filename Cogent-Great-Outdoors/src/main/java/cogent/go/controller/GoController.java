package cogent.go.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cogent.go.dao.UserRepository;
import cogent.go.entities.Cart;
import cogent.go.entities.CustomerQuery;
import cogent.go.entities.DeliveryAddress;
import cogent.go.entities.Order;
import cogent.go.entities.Product;
import cogent.go.entities.User;
import cogent.go.security.config.JwtTokenUtil;
import cogent.go.security.model.JwtResponse;
import cogent.go.security.model.LoginRequest;
import cogent.go.security.model.MessageResponse;
import cogent.go.security.model.SignupRequest;
import cogent.go.security.service.JwtUserDetailsImpl;
import cogent.go.service.GoService;

//Testing push
@CrossOrigin
@RestController
@RequestMapping("/go")
public class GoController {
	
	@Autowired
    private GoService service;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenUtil jwtUtils;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        service.saveProduct(product);
        return new ResponseEntity<>(product.getProductName() + " was added.", HttpStatus.OK);
    }
	
	@PostMapping("/addAddress")
    public ResponseEntity<String> addAddress(@RequestBody DeliveryAddress address) {
        service.saveAddress(address);
        return new ResponseEntity<>("Delivery address #" + address.getId() + " was added.", HttpStatus.OK);
    }
	
	@GetMapping("/getAddress")
	public DeliveryAddress getAddressById(@RequestParam("addressId") int id) {
        return service.getAddressById(id);
    }
	
	@PostMapping("/placeOrder")
    public ResponseEntity<String> addOrder(@RequestBody Order order) {
        service.saveOrder(order);
        String query = order.getUser().getFirstName() + " just added " + order.getProduct().getProductName() + " to Order #" + order.getOrderId();
        CustomerQuery custQuery = new CustomerQuery(order.getUser().getFirstName(), order.getUser().getLastName(), order.getUser().getEmail(), query);
        service.saveQuery(custQuery);
        return new ResponseEntity<>("Order " + order.getOrderId() + " was placed.", HttpStatus.OK);
    }
	
	
	@PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        service.saveUser(user);
        return new ResponseEntity<>(user.getFirstName() + " was added.", HttpStatus.OK);
    }
	
	
	@PostMapping("/createQuery")
    public ResponseEntity<String> addQuery(@RequestBody CustomerQuery query) {
        service.saveQuery(query);
        return new ResponseEntity<>("Query from Customer " + query.getCustId() + " was created.", HttpStatus.OK);
    }
	
	@PostMapping("/saveCart")
    public ResponseEntity<String> addCart(@RequestParam("productId") int productId,
    		@RequestParam("price") int price, @RequestParam("userId") int userId) {
		Product product = service.getProductById(productId).get(0);
		User user = service.getUserById(userId);
		Cart cart = new Cart(user, product, 1, price);
        service.saveCart(cart);
        String query = cart.getUser().getFirstName() + " just added " + cart.getProduct().getProductName() + " to Cart #" + cart.getCartId();
        CustomerQuery custQuery = new CustomerQuery(cart.getUser().getFirstName(), cart.getUser().getLastName(), cart.getUser().getEmail(), query);
        service.saveQuery(custQuery);
        return new ResponseEntity<>("Cart #" + cart.getCartId() + " was saved.", HttpStatus.OK);
    }
	
	@PostMapping("/changeCart")
    public ResponseEntity<String> changeCart(@RequestParam("cartId") int cartId, @RequestParam("quantity")int quantity, @RequestParam("productId")int productId) {
		List<Cart> cart_list = service.getCartById(cartId);
		if(cart_list != null && quantity > 0) {
			Cart cart = cart_list.get(0);
			Product product = service.getProductById(productId).get(0);
			cart.setProduct(product);
			cart.setQuantity(quantity);
			cart.setPrice(quantity*product.getPrice());
			service.saveCart(cart);
			String query = cart.getUser().getFirstName() + " just updated Cart #" + cart.getCartId() + ": Product = " + cart.getProduct().getProductName() + ", Quantity = " + cart.getQuantity()+ ", Price = " + cart.getPrice();
	        CustomerQuery custQuery = new CustomerQuery(cart.getUser().getFirstName(), cart.getUser().getLastName(), cart.getUser().getEmail(), query);
	        service.saveQuery(custQuery);
			return new ResponseEntity<>("Cart #" + cart.getCartId() + " changes were saved.", HttpStatus.OK);
		}
		else if(quantity == 0)
		{
			return deleteCart(cartId);
		}
		else
		{
			return new ResponseEntity<>("Cart #" + cartId + " was not found or new quantity is invalid", HttpStatus.NOT_FOUND);
		}
		
    }
	
	@DeleteMapping("/deleteCart")
	public ResponseEntity<String> deleteCart(@RequestParam("cartId") int cartId){
		Cart cart = service.getCartById(cartId).get(0);
		service.deleteCart(cart);
		String query = cart.getUser().getFirstName() + " deleted Cart #" + cartId;
        CustomerQuery custQuery = new CustomerQuery(cart.getUser().getFirstName(), cart.getUser().getLastName(), cart.getUser().getEmail(), query);
        service.saveQuery(custQuery);
		return new ResponseEntity<>("Cart #" + cartId + " was deleted.", HttpStatus.OK);
	}
	
	@GetMapping("/getCart")
	public List<Cart> getCart(@RequestParam("userId") int userId)
	{
		return service.getCartByUserId(userId);
	}
	
	@GetMapping("/findAllCarts")
	public List<Cart> getCartList(){
		return service.getCartList();
	}
	@GetMapping("/getCartById")
	public List<Cart> getCartById(@RequestParam("cartId") int cartId){
		return service.getCartById(cartId);
	}
	
	
	@GetMapping("/findAllProducts")
	public List<Product> getProductList(){
		return service.getProductList();
	}
	@GetMapping("/findProductsByCategory")
	public List<Product> getProductsByCategory(@RequestParam("category") String category){
		return service.getProductByCategory(category);
	}
	@GetMapping("/findProductsById")
	public List<Product> getProductsById(@RequestParam("id") int id){

		return service.getProductById(id);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		JwtUserDetailsImpl userDetails = (JwtUserDetailsImpl) authentication.getPrincipal();
		
		String query = userDetails.getFirstName() + " logged in.";
		CustomerQuery custQuery = new CustomerQuery(userDetails.getFirstName(), userDetails.getLastName(), userDetails.getEmail(), query);
        service.saveQuery(custQuery);

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(),
												 userDetails.getEmail(),
												 userDetails.getFirstName(),
												 userDetails.getLastName(),
												 userDetails.getPhoneNumber(),
												 userDetails.getAddressLine1(),
												 userDetails.getAddressLine2(),
												 userDetails.getState(),
												 userDetails.getPincode()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username/email is already taken!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getPhoneNumber(),
							signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()),
							signUpRequest.getAddressLine1(), signUpRequest.getAddressLine2(), signUpRequest.getState(),
							signUpRequest.getPincode());

		service.saveUser(user);
		
		String query = "New Customer registered [Name: " + user.getFirstName() + " " + user.getLastName() + ", Email: " + user.getEmail() + "]";
        CustomerQuery custQuery = new CustomerQuery(user.getFirstName(), user.getLastName(), user.getEmail(), query);
        service.saveQuery(custQuery);
        
		// mail service to send plain text mail to user's email account about successful
		// registration
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		
		}
	
}
