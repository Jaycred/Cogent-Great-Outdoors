package cogent.go.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cogent.go.entities.Cart;
import cogent.go.service.CartService;

@CrossOrigin
@RestController
@RequestMapping("/go/cart")
public class CartController {
	
	@Autowired
    private CartService service;
	
	@PostMapping("/save")
    public ResponseEntity<String> addCart(@RequestBody Cart cart) {
        service.saveCart(cart);
        return new ResponseEntity<>("Cart #" + cart.getCartId() + " was saved.", HttpStatus.OK);
    }
}
