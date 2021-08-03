package cogent.go.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cogent.go.entities.Product;
import cogent.go.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping("/go")
public class ProductController {
	
	@Autowired
    private ProductService service;
	
	@PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        service.saveProduct(product);
        return new ResponseEntity<>(product.getName() + " was added.", HttpStatus.OK);
    }
}
