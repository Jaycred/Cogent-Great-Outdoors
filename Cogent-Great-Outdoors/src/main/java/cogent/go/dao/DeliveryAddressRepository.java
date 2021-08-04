package cogent.go.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import cogent.go.entities.DeliveryAddress;

@CrossOrigin()
@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Integer> {

}
