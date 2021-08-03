package cogent.go.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cogent.go.entities.Order;
import cogent.go.service.OrderService;

@CrossOrigin
@RestController
@RequestMapping("/go/order")
public class OrderController {
	
	@Autowired
    private OrderService service;
	
	@PostMapping("/placeOrder")
    public ResponseEntity<String> addOrder(@RequestBody Order order) {
        service.saveOrder(order);
        return new ResponseEntity<>("Order " + order.getOrderId() + " was placed.", HttpStatus.OK);
    }
}
