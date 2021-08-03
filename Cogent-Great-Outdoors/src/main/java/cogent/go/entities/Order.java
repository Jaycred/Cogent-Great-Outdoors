package cogent.go.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Order {
	@Id
	private int orderId;
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "productId", referencedColumnName = "id")
	private Product product;
	private int quantity;
	private int price;
}
