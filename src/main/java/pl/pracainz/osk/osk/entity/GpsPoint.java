package pl.pracainz.osk.osk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gpspoints")
public class GpsPoint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "GPS_NS")
	private String gps_ns;

	@Column(name = "GPS_EW")
	private String gps_ew;


	@Column(name = "time")
	private Date time;

	@Column
	private int id_driving;

	@Column
	private int id_instructor;

	public GpsPoint() {

	}

	public GpsPoint(String gps_ns, String gps_ew, String gps_z, Date time, int id_driving, int id_instructor) {
		super();
		this.gps_ns = gps_ns;
		this.gps_ew = gps_ew;
		this.time = time;
		this.id_driving = id_driving;
		this.id_instructor = id_instructor;
	}

	public GpsPoint(String ns, String nw, Date date, int i, int j) {
		this.gps_ns = ns;
		this.gps_ew = nw;
		this.time = date;
		this.id_driving = i;
		this.id_instructor = j;
	}

	public GpsPoint(String ns, String nw, String time2, int i, int j) {
		
		this.gps_ns = ns;
		this.gps_ew = nw;
		
		this.time = new Date();
		this.id_driving = i;
		this.id_instructor = j;
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGps_ns() {
		return gps_ns;
	}

	public void setGps_ns(String gps_ns) {
		this.gps_ns = gps_ns;
	}

	public String getGps_ew() {
		return gps_ew;
	}

	public void setGps_ew(String gps_ew) {
		this.gps_ew = gps_ew;
	}


	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getId_driving() {
		return id_driving;
	}

	public void setId_driving(int id_driving) {
		this.id_driving = id_driving;
	}

	public int getId_instructor() {
		return id_instructor;
	}

	public void setId_instructor(int id_instructor) {
		this.id_instructor = id_instructor;
	}

}
