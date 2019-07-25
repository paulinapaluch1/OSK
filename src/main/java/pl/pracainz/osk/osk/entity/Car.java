package pl.pracainz.osk.osk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cars")
public class Car {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_car")
	private int id;
	
	@Column
	private String registration_number;
	
	@Column
	private String brand;
	
	@Column
	private String model;
	
	@Column
	private int deleted;
	

	public Car(String registration_number, String brand, String model, int deleted) {
		super();
		this.registration_number = registration_number;
		this.brand = brand;
		this.model = model;
		this.deleted = deleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegistration_number() {
		return registration_number;
	}

	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", registration_number=" + registration_number + ", brand=" + brand + ", model="
				+ model + ", deleted=" + deleted + "]";
	}
	
	
	
	
	
	
	
	
}
