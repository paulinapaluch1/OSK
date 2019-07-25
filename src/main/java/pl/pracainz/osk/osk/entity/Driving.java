package pl.pracainz.osk.osk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="drivings")
public class Driving {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_driving")
	private int id;
	
	@Column
	private int id_type;
	
	
	@Column 
	private int id_student;
	
	@Column
	private Date startHour;
	
	@Column
	private Date finishHour;
	
	@Column
	private int done;
	
	@Column
	private int cancelled;

	public Driving(int id_type, int id_student, Date startHour, Date finishHour, int done, int cancelled) {
		super();
		this.id_type = id_type;
		this.id_student = id_student;
		this.startHour = startHour;
		this.finishHour = finishHour;
		this.done = done;
		this.cancelled = cancelled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_type() {
		return id_type;
	}

	public void setId_type(int id_type) {
		this.id_type = id_type;
	}

	public int getId_student() {
		return id_student;
	}

	public void setId_student(int id_student) {
		this.id_student = id_student;
	}

	public Date getStartHour() {
		return startHour;
	}

	public void setStartHour(Date startHour) {
		this.startHour = startHour;
	}

	public Date getFinishHour() {
		return finishHour;
	}

	public void setFinishHour(Date finishHour) {
		this.finishHour = finishHour;
	}

	public int getDone() {
		return done;
	}

	public void setDone(int done) {
		this.done = done;
	}

	public int getCancelled() {
		return cancelled;
	}

	public void setCancelled(int cancelled) {
		this.cancelled = cancelled;
	}
	
	
	
	
	

	
}
