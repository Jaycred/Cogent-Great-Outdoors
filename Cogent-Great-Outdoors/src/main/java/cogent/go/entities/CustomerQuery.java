package cogent.go.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer_query", schema = "greatoutdoors")
public class CustomerQuery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cust_id")
	private int custId;
	
	@Size(max = 30)
	@Column(name = "first_name")
	private String firstName;
	@Size(max = 30)
	@Column(name = "last_name")
	private String lastName;
	@Size(max = 30)
	private String email;
	
	@Column(columnDefinition = "LONGTEXT")
	private String query;

	public CustomerQuery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerQuery(int custId, @Size(max = 30) String firstName, @Size(max = 30) String lastName,
			@Size(max = 30) String email, String query) {
		super();
		this.custId = custId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.query = query;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
}
