package cogent.go.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cogent.go.entities.*;

public class JwtUserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;

	private int id;

	private String firstName;
	
	private String lastName;
	
	private String phoneNumber;

	@NotBlank
	private String email;

	@NotBlank
	@JsonIgnore
	private String password;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String addressLine1;
	
	private String addressLine2;
	
	private String state;
	
	private int pincode;
	
	public JwtUserDetailsImpl(int id, String firstName, String lastName, String phoneNumber, String email,
			String password, String addressLine1, String addressLine2, String state, int pincode) {
		super();
		this.id = id;
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

	public static JwtUserDetailsImpl build(User user) {
		return new JwtUserDetailsImpl(user.getId(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), 
				user.getEmail(), user.getPassword(), user.getAddressLine1(), user.getAddressLine2(), 
				user.getState(), user.getPincode());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getAddressLine1() {
		return addressLine1;
	}
	
	public String getAddressLine2() {
		return addressLine2;
	}

	public String getState() {
		return state;
	}

	public int getPincode() {
		return pincode;
	}

	
}
