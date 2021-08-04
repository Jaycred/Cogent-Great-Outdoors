package cogent.go.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;

	@Size(max = 30)
	@Column(name = "product_name")
	private String productName;
	@Size(max = 200)
	private String description;
	private int price;
	@Size(max = 20)
	private String category;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int id, @Size(max = 30) String productName, @Size(max = 200) String description, int price,
			@Size(max = 20) String category) {
		super();
		this.productId = id;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.category = category;
	}
	public int getId() {
		return productId;
	}
	public void setId(int id) {
		this.productId = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
