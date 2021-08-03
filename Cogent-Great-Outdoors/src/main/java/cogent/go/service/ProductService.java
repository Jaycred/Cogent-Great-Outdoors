package cogent.go.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.go.dao.ProductRepository;
import cogent.go.entities.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public Product saveProduct(Product product) {
        return repository.save(product);
    }
}
