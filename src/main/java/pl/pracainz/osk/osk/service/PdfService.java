package pl.pracainz.osk.osk.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.pracainz.osk.osk.entity.Driving;

public interface PdfService {

	List<Driving> getDrivings();

	boolean createPdf(List<Driving> drivings, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);
}
