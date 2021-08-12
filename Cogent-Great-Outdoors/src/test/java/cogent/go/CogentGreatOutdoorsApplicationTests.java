package cogent.go;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.constraints.Size;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import cogent.go.dao.CartRepository;
import cogent.go.dao.CustomerQueryRepository;
import cogent.go.dao.DeliveryAddressRepository;
import cogent.go.dao.OrderRepository;
import cogent.go.dao.ProductRepository;
import cogent.go.dao.UserRepository;
import cogent.go.entities.Cart;
import cogent.go.entities.Product;
import cogent.go.entities.User;
import cogent.go.security.model.MessageResponse;
import cogent.go.service.GoService;


@RunWith(SpringRunner.class)
//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CogentGreatOutdoorsApplicationTests {

	@Autowired
	private GoService service;

	@MockBean
	private ProductRepository product;
	@MockBean
	private UserRepository user;
	@MockBean
	private CustomerQueryRepository query;
	@MockBean
	private DeliveryAddressRepository address;
	@MockBean
	private OrderRepository order;
	@MockBean
	private CartRepository cart;

	@Test
	public void signupTest() {
		User newUser = new User("James", "Jasinski", "123-456-7890", "jay@gmail.com", "password",
			"123 Elm Street", "Avenue Two", "Ohio", 12345);
		when(user.save(newUser)).thenReturn(newUser);
		assertEquals(newUser, service.saveUser(newUser));
	}
	
	@Test
	public void loginTest()
	{
		User newUser = new User("James", "Jasinski", "123-456-7890", "jay@gmail.com", "password",
				"123 Elm Street", "Avenue Two", "Ohio", 12345);
		service.login(newUser.getEmail(), newUser.getPassword());
		verify(user, times(1)).findByEmail(newUser.getEmail());
	}
	
	@Test
	public void getProductByCategoryTest()
	{
		Product prod1 = new Product(1, "Ski Boots", "Boots for skiing", 23, "MountaineeringEquipment");
		Product prod2 = new Product(3, "Ski Goggles", "Goggles for skiing", 23, "MountaineeringEquipment");
		when(product.findByCategory("MountaineeringEquipment"))
			.thenReturn(Stream.of(prod1, prod2).collect(Collectors.toList()));
		assertEquals(2, service.getProductByCategory("MountaineeringEquipment").size());
		assertEquals("Ski Boots", service.getProductByCategory("MountaineeringEquipment").get(0).getProductName());
		assertEquals(3, service.getProductByCategory("MountaineeringEquipment").get(1).getProductId());
	}
	
	@Test
	public void getProductByIdTest()
	{
		Product prod = new Product(5, "Ski Boots", "Boots for skiing", 23, "MountaineeringEquipment");
		when(product.findById(5))
			.thenReturn(Optional.of(prod));
		assertEquals(prod, service.getProductById(5).get(0));
	}
	
	@Test
	public void getCartByUserTest()
	{
		User newUser = new User("James", "Jasinski", "123-456-7890", "jay@gmail.com", "password",
				"123 Elm Street", "Avenue Two", "Ohio", 12345);
		Product prod1 = new Product(5, "Ski Boots", "Boots for skiing", 23, "MountaineeringEquipment");
		Product prod2 = new Product(3, "Ski Goggles", "Goggles for skiing", 57, "MountaineeringEquipment");
		Cart cart1 = new Cart(newUser, prod1, 7, prod1.getPrice()*7);
		Cart cart2 = new Cart(newUser, prod2, 5, prod2.getPrice()*5);
		when(service.getCartByUserId(newUser.getId())).thenReturn(Stream.of(cart1, cart2).collect(Collectors.toList()));
		assertEquals(cart2, service.getCartByUserId(newUser.getId()).get(1));
	}

	/*
	@Test
	public void getUserbyAddressTest() {
		String address = "Bangalore";
		when(repository.findByAddress(address)).thenReturn(Stream.of(new User(376, "Danile", 31, "USA")).collect(Collectors.toList()));
		assertEquals(1, service.getUserbyAddress(address).size());
	}

	@Test
	public void saveUserTest() {
		User user = new User(999, "Pranya", 33, "Pune");
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.addUser(user));
	}

	@Test
	public void deleteUserTest() {
		User user = new User(999, "Pranya", 33, "Pune");
		service.deleteUser(user);
		verify(repository, times(1)).delete(user);
	}
	*/

}
