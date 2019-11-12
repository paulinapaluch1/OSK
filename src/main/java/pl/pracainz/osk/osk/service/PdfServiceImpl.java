package pl.pracainz.osk.osk.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.entity.Student;

@Service
@Transactional
public class PdfServiceImpl implements PdfService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudents() {
		return (List<Student>) studentRepository.findAll();
	}

	@Override
	public boolean createPdf(List<Student> students, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		Document document = new Document(PageSize.A4, 15, 15, 45, 30);
		try {
			String filePath = context.getRealPath("/resources/reports");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			}

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "students" + ".pdf"));
			document.open();

			Font mainFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);

			Paragraph paragraph = new Paragraph("Kursanci", mainFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);

			document.add(paragraph);

			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);

			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);

			float[] columnWidths = { 2f, 2f, 2f};
			table.setWidths(columnWidths);

			PdfPCell firstName = new PdfPCell(new Paragraph("Imię", tableHeader));
			firstName.setBorderColor(BaseColor.BLACK);
			firstName.setPaddingLeft(10);
			table.addCell(firstName);

			PdfPCell lastName = new PdfPCell(new Paragraph("Nazwisko", tableHeader));
			lastName.setBorderColor(BaseColor.BLACK);
			lastName.setPaddingLeft(10);
			table.addCell(lastName);

			PdfPCell email = new PdfPCell(new Paragraph("Email", tableHeader));
			email.setBorderColor(BaseColor.BLACK);
			email.setPaddingLeft(10);
			table.addCell(email);

			for (Student student : students) {
				PdfPCell firstNameValue = new PdfPCell(new Paragraph(student.getName(), tableBody));
				firstNameValue.setBorderColor(BaseColor.GRAY);
				firstNameValue.setPaddingLeft(10);
				firstNameValue.setBackgroundColor(BaseColor.WHITE);
				table.addCell(firstNameValue);

				PdfPCell lastNameValue = new PdfPCell(new Paragraph(student.getSurname(), tableBody));
				lastNameValue.setBorderColor(BaseColor.GRAY);
				lastNameValue.setPaddingLeft(10);
				lastNameValue.setBackgroundColor(BaseColor.WHITE);
				table.addCell(lastNameValue);

				PdfPCell emailValue = new PdfPCell(new Paragraph(student.getEmail(), tableBody));
				emailValue.setBorderColor(BaseColor.GRAY);
				emailValue.setPaddingLeft(10);
				emailValue.setBackgroundColor(BaseColor.WHITE);
				table.addCell(emailValue);
			}

			document.addAuthor("Ośrodek Szkolenia Kierowców");
			document.addCreationDate();
			document.add(table);
			document.close();
			writer.close();
			return true;

		} catch (Exception e) {
			return false;
		}

	}
}
