package cogent.go;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import cogent.go.entities.CustomerQuery;
import cogent.go.entities.DeliveryAddress;
import cogent.go.entities.Order;
import cogent.go.entities.Product;
import cogent.go.entities.User;

import cogent.go.service.GoService;


@RunWith(SpringRunner.class)
//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CogentGreatOutdoorsApplicationTests {

	@Autowired
	private GoService service;

	@MockBean
	private UserRepository userRep;
	
	@MockBean
	private OrderRepository orderRep;
	
	@MockBean
	private DeliveryAddressRepository daRep;

	@MockBean
	private ProductRepository prodRep;
	
	@MockBean
	private CartRepository cartRep;
	
	@MockBean
	private CustomerQueryRepository custRep;
	
	@Test
	public void signupTest() {
		User newUser = new User("James", "Jasinski", "123-456-7890", "jay@gmail.com", "password",
			"123 Elm Street", "Avenue Two", "Ohio", 12345);
		when(userRep.save(newUser)).thenReturn(newUser);
		assertEquals(newUser, service.saveUser(newUser));
	}
	
	@Test
	public void loginTest()
	{
		User newUser = new User("James", "Jasinski", "123-456-7890", "jay@gmail.com", "password",
				"123 Elm Street", "Avenue Two", "Ohio", 12345);
		service.login(newUser.getEmail(), newUser.getPassword());
		verify(userRep, times(1)).findByEmail(newUser.getEmail());
	}
	
	@Test
	public void getProductByCategoryTest()
	{
		Product prod1 = new Product(1, "Ski Boots", "Boots for skiing", 23, "MountaineeringEquipment");
		Product prod2 = new Product(3, "Ski Goggles", "Goggles for skiing", 23, "MountaineeringEquipment");
		when(prodRep.findByCategory("MountaineeringEquipment"))
			.thenReturn(Stream.of(prod1, prod2).collect(Collectors.toList()));
		assertEquals(2, service.getProductByCategory("MountaineeringEquipment").size());
		assertEquals("Ski Boots", service.getProductByCategory("MountaineeringEquipment").get(0).getProductName());
		assertEquals(3, service.getProductByCategory("MountaineeringEquipment").get(1).getProductId());
	}
	
	@Test
	public void getProductByIdTest1()
	{
		Product prod = new Product(5, "Ski Boots", "Boots for skiing", 23, "MountaineeringEquipment");
		when(prodRep.findById(5))
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

  @Test
	public void getUsersTest() {
		when(userRep.findAll()).thenReturn(Stream
				.of(new User("Jaclyn", "Frank", "8765435263", "jfrank@purdue.edu", "JtotheF", "345 First St.", "West Lafayette, IN 47906", "IN", 97636),
						new User("Clara", "Pauker", "8455365263", "cpauk@purdue.edu", "CPauker", "856 Rise Ave.", "West Lafayette, IN 47906", "IN", 97765)).collect(Collectors.toList()));
		assertEquals(2, service.getAllUsers().size());
		
	}

	@Test
	public void getUserbyIdTest() {
		int id = 2;
		when(userRep.getById(id)).thenReturn(new User("Jeremy", "Frederick", "8455365263", "jfred@purdue.edu", "BretLee", "856 Rise Ave.", "West Lafayette, IN 47906", "IN", 97765));
		assertEquals("jfred@purdue.edu", service.getUserById(id).getEmail());
		assertEquals("BretLee", service.getUserById(id).getPassword());
	}

	@Test
	public void saveUserTest() {
		User user = new User("Jaclyn", "Frank", "8765435263", "jfrank@purdue.edu", "JtotheF", "345 First St.", "West Lafayette, IN 47906", "IN", 97636);
		when(userRep.save(user)).thenReturn(user);
		assertEquals(user, service.saveUser(user));
	}
	
	@Test
	public void getProductsTest() {
		Product product = new Product(11, "Nike Shoes", "Compatible for playing any sport, including golf", 30, "GolfEquipment");
		Product product1 = new Product(11, "Nike Socks", "Can wear shoes without socks", 15, "GolfEquipment");
		when(prodRep.findAll()).thenReturn(Stream.of(product, product1).collect(Collectors.toList()));
		assertEquals(2, service.getProductList().size());
	}
	
	@Test
	public void getProductByIdTest() {
		Optional<Product> product = Optional.of(new Product(11, "Nike Shoes", "Compatible for playing any sport, including golf", 30, "GolfEquipment"));
		//Product product1 = new Product(11, "Nike Socks", "Can wear shoes without socks", 15, "GolfEquipment");
		when(prodRep.findById(11)).thenReturn(product);
		assertEquals(product.get(), service.getProductById(11).get(0));
	}
	
	@Test
	public void getProductByCategoryTest2() {
		Product product = new Product(11, "Nike Shoes", "Compatible for playing any sport, including golf", 30, "GolfEquipment");
		List<Product> prodList = new ArrayList<>();
		prodList.add(product);
		//Product product1 = new Product(11, "Nike Socks", "Can wear shoes without socks", 15, "GolfEquipment");
		when(prodRep.findByCategory("GolfEquipment")).thenReturn(Stream.of(product).collect(Collectors.toList()));
		assertEquals(prodList, service.getProductByCategory("GolfEquipment"));
	}
	
	@Test
	public void saveProductTest() {
		Product product = new Product(11, "Nike Shoes", "Compatible for playing any sport, including golf", 30, "GolfEquipment");
		when(prodRep.save(product)).thenReturn(product);
		assertEquals(product, service.saveProduct(product));
	}
	
	@Test
	public void getCartsTest() {
		User user = new User("Jaclyn", "Frank", "8765435263", "jfrank@purdue.edu", "JtotheF", "345 First St.", "West Lafayette, IN 47906", "IN", 97636);
		Product product = new Product(11, "Nike Shoes", "Compatible for playing any sport, including golf", 30, "GolfEquipment");
		Cart cart = new Cart(user, product, 6, 180);
		when(cartRep.findById(1)).thenReturn(Stream
				.of(cart).collect(Collectors.toList()));
		assertEquals(1, service.getCartById(1).size());
	}
	
	@Test
	public void getCartByIdTest() {
		User user = new User("Jaclyn", "Frank", "8765435263", "jfrank@purdue.edu", "JtotheF", "345 First St.", "West Lafayette, IN 47906", "IN", 97636);
		Product product = new Product(11, "Nike Shoes", "Compatible for playing any sport, including golf", 30, "GolfEquipment");
		Cart cart = new Cart(user, product, 6, 180);
		List<Cart> cartList = new ArrayList<>();
		cartList.add(cart);
		when(cartRep.findById(6)).thenReturn(Stream.of(cart).collect(Collectors.toList()));
		assertEquals(cartList, service.getCartById(6));
	}
	
	@Test
	public void getCartByUserIdTest() {
		User user = new User("Jaclyn", "Frank", "8765435263", "jfrank@purdue.edu", "JtotheF", "345 First St.", "West Lafayette, IN 47906", "IN", 97636);
		Product product = new Product(11, "Nike Shoes", "Compatible for playing any sport, including golf", 30, "GolfEquipment");
		Cart cart = new Cart(user, product, 6, 180);
		List<Cart> cartList = new ArrayList<>();
		cartList.add(cart);
		when(userRep.getById(user.getId())).thenReturn(user);
		when(cartRep.findByUser(user)).thenReturn(Stream.of(cart).collect(Collectors.toList()));
		assertEquals(cartList, service.getCartByUserId(user.getId()));
	}
	
	@Test
	public void saveCartTest() {
		User user = new User("Jaclyn", "Frank", "8765435263", "jfrank@purdue.edu", "JtotheF", "345 First St.", "West Lafayette, IN 47906", "IN", 97636);
		Product product = new Product(11, "Nike Shoes", "Compatible for playing any sport, including golf", 30, "GolfEquipment");
		Cart cart = new Cart(user, product, 6, 180);
		when(cartRep.save(cart)).thenReturn(cart);
		assertEquals(cart, service.saveCart(cart));
	}
	
	@Test
	public void saveOrderTest() {
		User user = new User("Jaclyn", "Frank", "8765435263", "jfrank@purdue.edu", "JtotheF", "345 First St.", "West Lafayette, IN 47906", "IN", 97636);
		Product product = new Product(11, "Nike Shoes", "Compatible for playing any sport, including golf", 30, "GolfEquipment");
		Order order = new Order(1, user, product, 6, 180);
		when(orderRep.save(order)).thenReturn(order);
		assertEquals(order, service.saveOrder(order));
	}
	
	@Test
	public void saveQueryTest() {
		CustomerQuery custQuery = new CustomerQuery("James", "Jasinski", "jayjasin@gmail.com", "jayjasin@gmail.com logged in.");
		when(custRep.save(custQuery)).thenReturn(custQuery);
		assertEquals(custQuery, service.saveQuery(custQuery));
	}
	
	@Test
	public void saveAddressTest() {
		DeliveryAddress da = new DeliveryAddress(1, "3724 Arbuckle Dr.", "San Jose, CA 95124", "CA", 95124);
		when(daRep.save(da)).thenReturn(da);
		assertEquals(da, service.saveAddress(da));
	}
}
