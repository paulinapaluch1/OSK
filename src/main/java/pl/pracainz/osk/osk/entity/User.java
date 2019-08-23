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
	@GeneratedValue(strategy = GenerationType.IDENTITY
	)
	private int id;

	@Column(name = "login")
	private String username;
	@Column
	private String password;

//	@Nullable
	// @OneToOne
	// @MapsId
	// private Instructor instructor;

	@Column(nullable = true)
	@Nullable
	private Integer id_instructor;

	@Column(nullable = true)
	@Nullable
	private Integer id_student;
/*
	@ManyToOne(cascade = { CascadeType.DETACH })
	@JoinColumn(name = "id_role")
	private Role role;
*/
	
	
	private int id_role;
	
	public User(String login, String password, int role, int instructor, int id_student) {
		this.username = login;
		this.password = password;
		this.id_instructor = instructor;
		this.id_student = id_student;
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

	public Integer getId_student() {
		return id_student;
	}

	public void setId_student(Integer id_student) {
		this.id_student = id_student;
	}
/*
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
*/

	public Integer getId_instructor() {
		return id_instructor;
	}

	public void setId_instructor(Integer id_instructor) {
		this.id_instructor = id_instructor;
	}

	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}
	
	
	
	
}
