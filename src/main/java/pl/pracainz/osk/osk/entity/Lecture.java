package pl.pracainz.osk.osk.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "lectures")
public class Lecture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_lecture")
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_course")
	private Course course;

	@Column
	@NotEmpty(message="Pole numer sali nie może być puste")
	private String roomNumber;

	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	//@NotNull(message="Pole data nie może być puste")
	private LocalDateTime date;
	
	@Column
	private Integer deleted;

	public Lecture(Course course, String roomNumber, LocalDateTime date, int deleted) {
		super();
		this.course = course;
		this.roomNumber = roomNumber;
		this.date = date;
		this.deleted = deleted;
	}

	public Lecture() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

}
