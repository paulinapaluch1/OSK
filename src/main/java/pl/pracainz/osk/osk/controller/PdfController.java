package pl.pracainz.osk.osk.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import pl.pracainz.osk.osk.entity.Student;
import pl.pracainz.osk.osk.service.PdfService;

@Controller
public class PdfController {
	
	@Autowired private PdfService pdfService;
	@Autowired private ServletContext context;
	
	@GetMapping(value="/view")
	public String allStudent(Model theModel) {
		List<Student> students = pdfService.getAllStudents();
		theModel.addAttribute("students", students);
		
		return "view/students";
	}
	
	@GetMapping(value="/createPdf")
	public void createPdf(HttpServletRequest request, HttpServletResponse response) {
		List<Student> students = pdfService.getAllStudents();
		boolean isFlag = pdfService.createPdf(students, context, request, response);
		if(isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" +"students"+".pdf");
			filedownload(fullPath, response, "students.pdf");
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
