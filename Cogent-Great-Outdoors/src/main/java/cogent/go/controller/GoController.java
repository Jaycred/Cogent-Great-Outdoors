package cogent.go.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cogent.go.entities.Cart;
import cogent.go.entities.CustomerQuery;
import cogent.go.entities.DeliveryAddress;
import cogent.go.entities.Order;
import cogent.go.entities.Product;
import cogent.go.entities.User;
import cogent.go.service.GoService;

@CrossOrigin
@RestController
@RequestMapping("/go")
public class GoController {
	
	@Autowired
    private GoService service;
	
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
    public ResponseEntity<String> addCart(@RequestBody Cart cart) {
        service.saveCart(cart);
        return new ResponseEntity<>("Cart #" + cart.getCartId() + " was saved.", HttpStatus.OK);
    }
	
	@GetMapping("/findAllProducts")
	public List<Product> getProductList(){
		return service.getProductList();
	}
	@GetMapping("/findProductsByCategory")
	public List<Product> getProductList(@RequestParam("category") String category){
		return service.getProductList();
	}
	@GetMapping("/findProductsById")
	public List<Product> getProductList(@RequestParam("id") int id){
		return service.getProductList();
	}
	
	
	// please work
}
