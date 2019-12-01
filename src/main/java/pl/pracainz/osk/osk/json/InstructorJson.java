package pl.pracainz.osk.osk.json;

public class InstructorJson {

	private Integer id;
	private String name;
	private String surname;
	private String message;

	public InstructorJson(String message) {
		this.message = message;
	}

	public InstructorJson(String message, int id, String name, String surname) {
		this.message = message;
		this.id = id;
		this.name = name;
		this.surname = surname;
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

}
