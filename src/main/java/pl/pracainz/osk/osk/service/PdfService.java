package pl.pracainz.osk.osk.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.pracainz.osk.osk.entity.Student;

public interface PdfService {

	List<Student> getAllStudents();

	boolean createPdf(List<Student> students, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);
}
