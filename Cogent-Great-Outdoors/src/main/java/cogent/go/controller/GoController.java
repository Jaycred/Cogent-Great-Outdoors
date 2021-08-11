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
	
	@PostMapping("/placeOrder")
    public ResponseEntity<String> addOrder(@RequestBody Order order) {
        service.saveOrder(order);
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
        return new ResponseEntity<>("Cart #" + cart.getCartId() + " was saved.", HttpStatus.OK);
    }
	
	@PostMapping("/changeCart")
    public ResponseEntity<String> changeCart(@RequestParam("cartId") int cartId, @RequestParam("quantity")int quantity, @RequestParam("productId")int productId) {
		Optional<Cart> cart = service.getCartById(cartId);
		if(cart.isPresent()) {
			Product product = service.getProductById(productId).get(0);
			cart.get().setProduct(product);
			cart.get().setQuantity(quantity);
			cart.get().setPrice(quantity*product.getPrice());
			return new ResponseEntity<>("Cart #" + cart.get().getCartId() + " changes were saved.", HttpStatus.OK);
		}
		return new ResponseEntity<>("Cart #" + cart.get().getCartId() + " was not found!", HttpStatus.NOT_FOUND);
		
    }
	
	@DeleteMapping("/deleteCart")
	public ResponseEntity<String> deleteCart(@RequestParam("cartId") int cartId){
		Cart cart = service.getCartById(cartId).get();
		service.deleteCart(cart);
		return new ResponseEntity<>("Cart #" + cartId + " was deleted.", HttpStatus.OK);
	}
	
	@GetMapping("/getCart")
	public List<Cart> getCart(@RequestParam("userId") int userId)
	{
		List<Cart> cartContents = service.getCartByUserId(userId);
		return cartContents;
	}
	
	
	/*
	@GetMapping("/findAllCarts")
	public List<Product> getCartList(){
		return service.getProductList();
	}
	@GetMapping("/getCartsByUser")
	public List<Product> getCartList(){
		return service.getProductList();
	}
	*/
	
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

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(),
												 userDetails.getEmail()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username/email is already taken!"));
		}

		////////////////////////////FIX///////////////////////////////
		// Create new user's account
		User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getPhoneNumber(),
							signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()),
							signUpRequest.getAddressLine1(), signUpRequest.getAddressLine2(), signUpRequest.getState(),
							signUpRequest.getPincode());

		//////////////////////////////FIX/////////////////////////////////////////

		service.saveUser(user);
		// mail service to send plain text mail to user's email account about successful
		// registration
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		
		}
	
}
