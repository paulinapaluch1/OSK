package pl.pracainz.osk.osk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="caropinions")
public class CarOpinion {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_carOpinion")
	private int id;
	
	@Column
	private int id_student;
	
	@Column
	private int id_car;
	
	@Column
	private int carMark;
	
	@Column
	private int carOpinion;
	
	@Column
	private int deleted;

	public CarOpinion(int id_student, int id_car, int carMark, int carOpinion, int deleted) {
		super();
		this.id_student = id_student;
		this.id_car = id_car;
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

	public int getId_student() {
		return id_student;
	}

	public void setId_student(int id_student) {
		this.id_student = id_student;
	}

	public int getId_car() {
		return id_car;
	}

	public void setId_car(int id_car) {
		this.id_car = id_car;
	}

	public int getCarMark() {
		return carMark;
	}

	public void setCarMark(int carMark) {
		this.carMark = carMark;
	}

	public int getCarOpinion() {
		return carOpinion;
	}

	public void setCarOpinion(int carOpinion) {
		this.carOpinion = carOpinion;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	
	
	
	
	
}
