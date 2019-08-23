package pl.pracainz.osk.osk.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="caropinions")
public class CarOpinion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_carOpinion")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_student")
	private Student student;
	
	@ManyToOne
	@JoinColumn(name = "id_car")
	private Car car;
	
	@Column(name = "carMark")
	private int carMark;
	
	@Column(name = "carOpinion")
	private String carOpinion;
	
	@Column(name = "deleted")
	private int deleted;

	public CarOpinion() {}
	
	public CarOpinion(Student id_student, Car id_car, int carMark, String carOpinion, int deleted) {
		super();
		this.student = id_student;
		this.car = id_car;
		this.carMark = carMark;
		this.carOpinion = carOpinion;
		this.deleted = deleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public int getCarMark() {
		return carMark;
	}

	public void setCarMark(int carMark) {
		this.carMark = carMark;
	}


	public String getCarOpinion() {
		return carOpinion;
	}

	public void setCarOpinion(String carOpinion) {
		this.carOpinion = carOpinion;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	
}
