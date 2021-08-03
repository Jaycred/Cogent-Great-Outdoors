package cogent.go.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cogent.go.entities.DeliveryAddress;
import cogent.go.service.DeliveryAddressService;

@CrossOrigin
@RestController
@RequestMapping("/go/address")
public class DeliveryAddressController {
	
	@Autowired
    private DeliveryAddressService service;
	
	@PostMapping("/addAddress")
    public ResponseEntity<String> addAddress(@RequestBody DeliveryAddress address) {
        service.saveAddress(address);
        return new ResponseEntity<>("Delivery address #" + address.getId() + " was added.", HttpStatus.OK);
    }
}
