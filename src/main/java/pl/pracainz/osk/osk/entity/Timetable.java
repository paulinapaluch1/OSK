package pl.pracainz.osk.osk.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "timetable")
public class Timetable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@ManyToOne(cascade = {CascadeType.DETACH })
	@JoinColumn(name = "id_instructor")
	@Nullable
	private Instructor instructor;

	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime begin;

	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime end;

	@ManyToOne
	@JoinColumn(name = "id_car")
	private Car car;

	@ManyToOne(optional=false)
	@JoinColumn(name = "id_type")
	private DrivingType drivingType;
	
	@Column
	private int archived;
	
	@OneToMany(mappedBy = "timetable")
	List<Driving> drivings;
	

	//@Transient
	//private Driving driving;
	
	public Timetable() {
		
		
	}

	public Timetable(Instructor instructor, LocalDateTime begin, LocalDateTime end, Car car, int archived) {

		this.instructor = instructor;
		this.begin = begin;
		this.end = end;
		this.car = car;
		this.archived = archived;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public LocalDateTime getBegin() {
		return begin;
	}

	public void setBegin(LocalDateTime begin) {
		this.begin = begin;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}


	public int getArchived() {
		return archived;
	}

	public void setArchived(int archived) {
		this.archived = archived;
	}

	public DrivingType getDrivingType() {
		return drivingType;
	}

	public void setDrivingType(DrivingType drivingType) {
		this.drivingType = drivingType;
	}

	public List<Driving> getDrivings() {
		return drivings;
	}

	public void setDrivings(List<Driving> drivings) {
		this.drivings = drivings;
	}


	public boolean isReserved() {
		if(getDrivings().isEmpty())
			return false;
		else {
			boolean reserved = false;
			for(Driving driving : getDrivings()) {
				if(driving.getCancelled() == 0)
					reserved = true;
			}
			return reserved;
		}
		
	}
	
	public Driving getDriving() {
		for(Driving driving : getDrivings()) {
			if(driving.getCancelled() == 0){
				return driving;
			}
		}	
		return new Driving();	
	}

	
	public String getDayName() {
		int dayNumber = begin.getDayOfWeek().getValue();
		switch (dayNumber) {
		case 1:
			return "Poniedziałek";
		case 2:
			return "Wtorek";
		case 3:
			return "Środa";
		case 4:
			return "Czwartek";
		case 5:
			return "Piątek";
		case 6:
			return "Sobota";
		case 7:
			return "Niedziela";
		default:
			return "Dzisiaj";

		}
	}


	
}
