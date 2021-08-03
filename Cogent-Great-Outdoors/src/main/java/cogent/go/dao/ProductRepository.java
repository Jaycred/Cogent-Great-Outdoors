package cogent.go.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import cogent.go.entities.Product;

@CrossOrigin()
public interface ProductRepository extends JpaRepository<Product, Integer> {
	Page<Product> findByCategory(@RequestParam("id") String id, Pageable pageable);

	Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
}
