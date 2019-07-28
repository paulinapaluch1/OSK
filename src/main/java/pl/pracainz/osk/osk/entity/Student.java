package pl.pracainz.osk.osk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="students")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_student")
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column
	private String login;
	
	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
    @NotNull
    @Past	
    private Date birthdate;
	
	@Column
	private String street;
	
	@Column
	private String buildingNumber;
	
    @Column
	private String apartmentNumber;
	
	@Column
	private String city;
	
	@Column
	private String postcode;
	
	@Column
	private String phoneNumber;
	
	@Column
	private String email;
	
	@Column
	private String PKK;
	
	
	@Column
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


	public Student(Integer id, String name, String surname, String login, Date birthdate, String street,
			String buildingNumber, String apartmentNumber, String city, String postcode, String phoneNumber,
			String email, String pKK, Integer deleted) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.birthdate = birthdate;
		this.street = street;
		this.buildingNumber = buildingNumber;
		this.apartmentNumber = apartmentNumber;
		this.city = city;
		this.postcode = postcode;
		this.phoneNumber = phoneNumber;
		this.email = email;
		PKK = pKK;
		this.deleted = deleted;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", surname=" + surname + ", login=" + login + ", birthdate="
				+ birthdate + ", street=" + street + ", buildingNumber=" + buildingNumber + ", apartmentNumber="
				+ apartmentNumber + ", city=" + city + ", postcode=" + postcode + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + ", PKK=" + PKK + ", deleted=" + deleted + "]";
	}



	
	
	

}
