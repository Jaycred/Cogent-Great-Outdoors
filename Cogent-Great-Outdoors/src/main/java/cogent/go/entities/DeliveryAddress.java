package cogent.go.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "delivery_address_table", schema = "greatoutdoors")
public class DeliveryAddress {
	@Id
	@Column(name = "address_id")
	private int addressId;
	@Size(max = 60)
	@Column(name = "address_line_1")
	private String addressLine1;
	@Size(max = 60)
	@Column(name = "address_line_2")
	private String addressLine2;
	@Size(max = 30)
	private String state;
	private int pincode;
	
	public DeliveryAddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeliveryAddress(int id, @Size(max = 60) String addressLine1, @Size(max = 60) String addressLine2,
			@Size(max = 30) String state, int pincode) {
		super();
		this.addressId = id;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.state = state;
		this.pincode = pincode;
	}

	public int getId() {
		return addressId;
	}

	public void setId(int id) {
		this.addressId = id;
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
