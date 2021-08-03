package cogent.go.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.go.dao.DeliveryAddressRepository;
import cogent.go.entities.DeliveryAddress;

@Service
public class DeliveryAddressService {
	
	@Autowired
	private DeliveryAddressRepository repository;
	
	public DeliveryAddress saveAddress(DeliveryAddress address) {
        return repository.save(address);
    }
}
