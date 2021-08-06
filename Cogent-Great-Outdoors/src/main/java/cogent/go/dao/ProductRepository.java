package cogent.go.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import cogent.go.entities.Product;

@CrossOrigin()
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByCategory(@RequestParam("categoryName") String cName);
	
	List<Product> findById(@RequestParam("productId") int id);

	List<Product> findByProductNameContaining(@RequestParam("productName") String productName);
}
