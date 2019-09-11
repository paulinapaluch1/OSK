package pl.pracainz.osk.osk.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_user")
	private int id;

	@Column(name = "login",nullable=false)
	private String username;
	@Column(nullable=false)
	private String password;



/*
	@ManyToOne(cascade = { CascadeType.DETACH })
	@JoinColumn(name = "id_role")
	private Role role;
*/
	
	
	private int id_role;
	
	public User(String login, String password, int role) {
		this.username = login;
		this.password = password;
		this.id_role = role;
	}

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * public Instructor getInstructor() { return id_instructor; }
	 * 
	 * public void setInstructor(Instructor instructor) { this.id_instructor =
	 * instructor; }
	 */

	
/*
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
*/


	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}
	
	
	
	
}
