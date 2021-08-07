package cogent.go.security.model;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cogent.go.entities.User;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private int id;

	//email is used as username
	private String email;

	@JsonIgnore
	private String password;

	public UserDetailsImpl(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	////////////////////////////FIX////////////////////////////////////////
	public static UserDetailsImpl build(User user) {
		return new UserDetailsImpl(user.getId(), user.getEmail(), user.getPassword());
	}
	///////////////////FIX//////////////////////////

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}