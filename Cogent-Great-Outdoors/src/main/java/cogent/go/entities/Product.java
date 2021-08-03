package cogent.go.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Product {
	@Id
	private int id;
	@Size(max = 30)
	private String name;
	@Size(max = 200)
	private String description;
	private int price;
	@Size(max = 20)
	private String category;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int id, @Size(max = 30) String name, @Size(max = 200) String description, int price,
			@Size(max = 20) String category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
