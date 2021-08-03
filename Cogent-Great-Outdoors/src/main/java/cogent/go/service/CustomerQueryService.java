package cogent.go.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.go.dao.CustomerQueryRepository;
import cogent.go.entities.CustomerQuery;

@Service
public class CustomerQueryService {
	
	@Autowired
	private CustomerQueryRepository repository;
	
	public CustomerQuery saveProduct(CustomerQuery query) {
        return repository.save(query);
    }
}
