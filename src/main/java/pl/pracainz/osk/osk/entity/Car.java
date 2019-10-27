package pl.pracainz.osk.osk.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "cars")
public class Car {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_car")
	private int id;

	@Column(name = "registration_number")
	@NotEmpty(message="Numer rejestracyjny nie może być pusty")
	private String registrationNumber;

	@Column(name = "brand")
	@NotEmpty(message="Marka nie może być pusta")
	private String brand;

	@NotEmpty(message="Model nie może być pusty")
	@Column(name = "model")
	private String model;
	
	@Column
	private Integer deleted;

	@OneToMany(mappedBy = "car")
	List<CarOpinion> carOpinions;

	@OneToMany(mappedBy = "car")
	List<Timetable> timetables;

	public Car() {
	}

	public Car(String registration_number, String brand, String model, int deleted) {
		super();
		this.registrationNumber = registration_number;
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

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
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

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", registration_number=" + registrationNumber + ", brand=" + brand + ", model=" + model
				+ ", deleted=" + deleted + "]";
	}

	public List<Timetable> getTimetables() {
		return timetables;
	}

	public void setTimetables(List<Timetable> timetables) {
		this.timetables = timetables;
	}
	

	public List<CarOpinion> getCarOpinions() {
		return carOpinions;
	}

	public void setCarOpinions(List<CarOpinion> carOpinions) {
		this.carOpinions = carOpinions;
	}
	
	public double getMarkAverage() {
		if(!carOpinions.isEmpty()) {
		double sum = 0;
		double quantity = 0;
		for (CarOpinion opinion : carOpinions) {
			if(opinion.getStatus().equals("zatwierdzona")) {
				sum += opinion.getCarMark();
				quantity++;
			}	
				
		}
		return round(sum / quantity, 2);
		}
		else return 0;
	}
	
	private double round(double value, int placesAfterComma) {
	    if (placesAfterComma < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, placesAfterComma);
	    value = value * factor;
	    long roundedNumber = Math.round(value);
	    return (double) roundedNumber / factor;
	}
	
	
	

}
