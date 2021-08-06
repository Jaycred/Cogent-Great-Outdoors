package cogent.go.dao;

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
	Page<Product> findByCategory(@RequestParam("category") String category, Pageable pageable);

	Page<Product> findByProductNameContaining(@RequestParam("productName") String productName, Pageable pageable);
}
