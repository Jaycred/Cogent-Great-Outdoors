package cogent.go.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import cogent.go.entities.DeliveryAddress;

@CrossOrigin()
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Integer> {

}
