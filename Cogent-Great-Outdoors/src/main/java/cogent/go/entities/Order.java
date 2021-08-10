package cogent.go.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_table", schema = "greatoutdoors")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private Product product;
	private int quantity;
	private int price;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "order_address_table", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
	private DeliveryAddress da;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int orderId, User user, Product product, int quantity, int price) {
		super();
		this.orderId = orderId;
		this.user = user;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
