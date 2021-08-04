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
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	
	@Size(max = 30)
	@Column(name = "first_name")
	private String firstName;
	@Size(max = 30)
	@Column(name = "last_name")
	private String lastName;
	@Size(max = 10)
	@Column(name = "phone_number")
	private String phoneNumber;
	@Size(max = 30)
	private String email;
	@Size(max = 30)
	private String password;
	@Size(max = 60)
	@Column(name = "address_line_1")
	private String addressLine1;
	@Size(max = 60)
	@Column(name = "address_line_2")
	private String addressLine2;
	@Size(max = 30)
	private String state;
	private int pincode;
	
	@OneToMany(mappedBy = "user")
	private List<Order> orderList;
	@OneToMany(mappedBy = "user")
	private List<Cart> cartList;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(int id, String firstName, String lastName, String phoneNumber, String email, String password,
			String addressLine1, String addressLine2, String state, int pincode) {
		super();
		this.userId = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.state = state;
		this.pincode = pincode;
	}
	
	public int getId() {
		return userId;
	}
	public void setId(int id) {
		this.userId = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	

}
