package pl.pracainz.osk.osk.json;

public class InstructorJson {

	private Integer id;
	private String name;
	private String surname;
	private String message;
	private String email;
	private String login;
	private String phoneNumber;
	private double markAverage;

	public InstructorJson(String message) {
		this.message = message;
	}

	public InstructorJson(String message, int id, String name, String surname) {
		this.message = message;
		this.id = id;
		this.name = name;
		this.surname = surname;
	}

	public InstructorJson(String message, int id2, String name2, String surname2, String email2, String login2,
			String phoneNumber2, double markAverage2) {
		
		this.id=id2;
		this.name=name2;
		this.surname=surname2;
		this.message=message;
		this.email=email2;
		this.login=login2;
		this.phoneNumber=phoneNumber2;
		this.markAverage=markAverage2;
		
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getMarkAverage() {
		return markAverage;
	}

	public void setMarkAverage(double markAverage) {
		this.markAverage = markAverage;
	}





}
