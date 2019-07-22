package pl.pracainz.osk.osk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="students")
public class Student {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_student")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@Column(name="login")
	private String login;
	
	@Column(name="birthdate")
	private Date birthdate;
	
	@Column(name="street")
	private String street;
	
	@Column(name="buildingNumber")
	private String buildingNumber;
	
    @Column(name="apartmentNumber")
	private String apartmentNumber;
	
	@Column(name="city")
	private String city;
	
	@Column(name="postcode")
	private String postcode;
	
	@Column(name="phoneNumber")
	private String phoneNumber;
	
	@Column(name="email")
	private String email;
	
	@Column(name="PKK")
	private String PKK;
	
	
	@Column(name="deleted")
	private Integer deleted;
	
	
	public Student() {}
	
	public Student(String name, String surname
			, String login, Date birthdate, String street, 
			String buildingNumber,
		    String apartmentNumber, 
			String city, String postcode,
			String phoneNumber, 
			String email, String pKK,
			 Integer deleted) {
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.street = street;
		this.login=login;
		this.buildingNumber = buildingNumber;
		this.apartmentNumber = apartmentNumber;
		this.city = city;
		this.postcode = postcode;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.PKK = pKK;
		this.deleted = deleted;
	
	}


	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public Date getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}

	public String getBuildingNumber() {
		return buildingNumber;
	}


	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}


	public String getApartmentNumber() {
		return apartmentNumber;
	}


	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPostcode() {
		return postcode;
	}

	

	public void setPostcode(String postcode) {
		this.postcode = postcode;
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


	public String getPKK() {
		return PKK;
	}


	public void setPKK(String pKK) {
		PKK = pKK;
	}


	public Integer getDeleted() {
		return deleted;
	
	}


	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}



	
	
	

}
