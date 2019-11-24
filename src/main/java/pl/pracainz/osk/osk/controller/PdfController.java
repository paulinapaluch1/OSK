package pl.pracainz.osk.osk.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.pracainz.osk.osk.dao.DrivingRepository;
import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.entity.Driving;
import pl.pracainz.osk.osk.service.PdfService;

@Controller
public class PdfController {
	
	@Autowired private PdfService pdfService;
	@Autowired private ServletContext context;
	
	private StudentRepository studentRepository;
	private DrivingRepository drivingRepository;
	
	
	@GetMapping(value="/createPdf")
	public void createPdf(@RequestParam("id_student") int id, HttpServletRequest request, HttpServletResponse response) {
//		List<Driving> drivings = studentRepository.findDoneDrivingsForStudentById(7);
		List<Driving> drivings = pdfService.getDrivings(id);
		boolean isFlag = pdfService.createPdf(drivings, context, request, response);
		if(isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" +"drivings"+".pdf");
			filedownload(fullPath, response, "drivings.pdf");
		}

	}

	private void filedownload(String fullPath, HttpServletResponse response, String fileName) {
		File file = new File(fullPath);
		final int BUFFER_SIZE= 4096;
		if(file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename=" + fileName);
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outputStream.close();
				file.delete();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
