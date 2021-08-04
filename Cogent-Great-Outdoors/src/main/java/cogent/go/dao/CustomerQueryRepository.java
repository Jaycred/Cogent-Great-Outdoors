package cogent.go.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import cogent.go.entities.CustomerQuery;

@CrossOrigin()
public interface CustomerQueryRepository extends JpaRepository<CustomerQuery, Integer> {

}
