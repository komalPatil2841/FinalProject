package com.mouritech.onlineshoppingsystem.entity;

import java.util.HashSet;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;



@Entity
@Table(name = "users")
public class User {

    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID",length = 64)
    private String userId;

    @Column(unique = true,name = "USER_NAME")
    @NotEmpty
	@Size(min=2, message="User name should have at least 2 characters")
	private String userName;
    
    @Column(name = "EMAIL")
    @Email(message = "Not a valid email")
	private String userEmail;
    
    @Column(name = "CONTACT")
    @NotBlank(message = "Contact Number is required")
    @Size(min = 10, max = 10,message = "Invalid Number")
	private String userPhn;
    
    @Column(name = "ADDRESS")
    @NotEmpty
	private String userAddress;
    
    @Column(name = "PASSWORD")
    @NotEmpty
	@Size(min=8, message="Password should have at least 8 characters")
	private String password;
    @Column(name = "ENABLED")
    private boolean enabled;
	
    public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    private Set<Role> roles = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	
	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}


	public User() {
		super();

	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhn() {
		return userPhn;
	}

	public void setUserPhn( String userPhn) {
		this.userPhn = userPhn;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String userId, String userName, String userEmail,  String userPhn, String userAddress, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhn = userPhn;
		this.userAddress = userAddress;
		this.password = password;
	}


	public User(String userId, String userName, String userEmail,  String userPhn, String userAddress, String password,
			boolean enabled, Set<Role> roles) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhn = userPhn;
		this.userAddress = userAddress;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
	}

	
	
}
