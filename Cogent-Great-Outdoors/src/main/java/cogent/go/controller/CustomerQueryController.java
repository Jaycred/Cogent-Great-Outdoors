package cogent.go.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cogent.go.entities.CustomerQuery;
import cogent.go.service.CustomerQueryService;

@CrossOrigin
@RestController
@RequestMapping("/go/customer")
public class CustomerQueryController {
	
	@Autowired
    private CustomerQueryService service;
	
	@PostMapping("/createQuery")
    public ResponseEntity<String> addQuery(@RequestBody CustomerQuery query) {
        service.saveQuery(query);
        return new ResponseEntity<>("Query from Customer " + query.getCustId() + " was created.", HttpStatus.OK);
    }
}
