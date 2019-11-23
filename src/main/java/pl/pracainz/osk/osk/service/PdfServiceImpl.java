package pl.pracainz.osk.osk.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import pl.pracainz.osk.osk.dao.DrivingRepository;
import pl.pracainz.osk.osk.entity.Driving;

@Service
@Transactional
public class PdfServiceImpl implements PdfService {

	String poz;
	int i = 0;
			
	@Autowired
	private DrivingRepository drivingRepository;

	@Override
	public List<Driving> getDrivings() {
		//return (List<Driving>) drivingRepository.findAll().stream().filter(d->d.getStudent().getId());
		return (List<Driving>) drivingRepository.findByDone(1);
	}

	@Override
	public boolean createPdf(List<Driving> drivings, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		Document document = new Document(PageSize.A4, 15, 15, 45, 30);
		try {
			String filePath = context.getRealPath("/resources/reports");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			}

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "drivings" + ".pdf"));
			document.open();

			BaseFont arial = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
			Font mainFont = new Font(arial, 12, Font.NORMAL);
			Font titleFont = new Font(arial, 20, Font.BOLD);
			Font titleColumn = new Font(arial, 9, Font.BOLD);
			
//			Font mainFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
//			Font titleFont = FontFactory.getFont("Arial", 20, BaseColor.BLACK);

			
			Paragraph newLineParagraph = new Paragraph("\n ", mainFont);
			newLineParagraph.setAlignment(Element.ALIGN_RIGHT);
			newLineParagraph.setIndentationLeft(50);
			newLineParagraph.setIndentationRight(50);
			newLineParagraph.setSpacingAfter(10);

			
			// get Date of creation
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			DateTimeFormatter hourFormat = DateTimeFormatter.ofPattern("hh:mm a");

			LocalDateTime localDateTime = LocalDateTime.now();
			 
			String ldtString = dateFormat.format(localDateTime);
			
			Paragraph dateParagraph = new Paragraph("Data wydania: " + ldtString, mainFont);
			dateParagraph.setAlignment(Element.ALIGN_RIGHT);
			dateParagraph.setIndentationLeft(50);
			dateParagraph.setIndentationRight(50);
			dateParagraph.setSpacingAfter(10);
			document.add(dateParagraph);
			
			document.add(newLineParagraph);
			
			Paragraph student = new Paragraph("Karta przeprowadzonych zajec", titleFont);
			student.setAlignment(Element.ALIGN_CENTER);
			student.setIndentationLeft(30);
			student.setIndentationRight(30);
			student.setSpacingAfter(10);
			document.add(student);
			
			Paragraph title = new Paragraph("Tu kursant", mainFont);
			title.setAlignment(Element.ALIGN_LEFT);
			title.setIndentationLeft(30);
			title.setIndentationRight(30);
			title.setSpacingAfter(10);
			document.add(title);

			document.add(newLineParagraph);
			
			PdfPTable table = new PdfPTable(7);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);

//			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);

			float[] columnWidths = {0.6f, 1.6f, 1.3f, 1.3f, 1.3f, 2f, 1.5f};
			table.setWidths(columnWidths);

			PdfPCell id = new PdfPCell(new Paragraph("Poz.", titleColumn));
			id.setBorderColor(BaseColor.BLACK);
			id.setPaddingLeft(10);
			table.addCell(id);
			
			PdfPCell type = new PdfPCell(new Paragraph("Typ jazdy", titleColumn));
			type.setBorderColor(BaseColor.BLACK);
			type.setPaddingLeft(10);
			table.addCell(type);
			
			PdfPCell date = new PdfPCell(new Paragraph("Data", titleColumn));
			date.setBorderColor(BaseColor.BLACK);
			date.setPaddingLeft(10);
			table.addCell(date);
			
			PdfPCell startHour = new PdfPCell(new Paragraph("Godz. rozpoczęcia", titleColumn));
			startHour.setBorderColor(BaseColor.BLACK);
			startHour.setPaddingLeft(10);
			table.addCell(startHour);
			
			PdfPCell finishHour = new PdfPCell(new Paragraph("Godz. zakończenia", titleColumn));
			finishHour.setBorderColor(BaseColor.BLACK);
			finishHour.setPaddingLeft(10);
			table.addCell(finishHour);
			
			PdfPCell instructor = new PdfPCell(new Paragraph("Instruktor", titleColumn));
			instructor.setBorderColor(BaseColor.BLACK);
			instructor.setPaddingLeft(10);
			table.addCell(instructor);
			
			PdfPCell car = new PdfPCell(new Paragraph("Nr rejestracyjny", titleColumn));
			car.setBorderColor(BaseColor.BLACK);
			car.setPaddingLeft(10);
			table.addCell(car);
			
		
			
			for (Driving driving : drivings) {
				i++;
				poz = String.valueOf(i);
				PdfPCell idValue = new PdfPCell(new Paragraph(poz, tableBody));
				idValue.setBorderColor(BaseColor.BLACK);
				idValue.setPaddingLeft(10);
				idValue.setBackgroundColor(BaseColor.WHITE);
				table.addCell(idValue);
				
				PdfPCell typeValue = new PdfPCell(new Paragraph(driving.getTimetable().getDrivingType().getType(), tableBody));
				typeValue.setBorderColor(BaseColor.BLACK);
				typeValue.setPaddingLeft(10);
				typeValue.setBackgroundColor(BaseColor.WHITE);
				table.addCell(typeValue);
				
				String drivingDate = dateFormat.format(driving.getTimetable().getBegin());
				PdfPCell dateValue = new PdfPCell(new Paragraph(drivingDate, tableBody));
				dateValue.setBorderColor(BaseColor.BLACK);
				dateValue.setPaddingLeft(10);
				dateValue.setBackgroundColor(BaseColor.WHITE);
				table.addCell(dateValue);
				
				String drivingStartHour = hourFormat.format(driving.getTimetable().getBegin());
				PdfPCell startHourValue = new PdfPCell(new Paragraph(drivingStartHour, tableBody));
				startHourValue.setBorderColor(BaseColor.BLACK);
				startHourValue.setPaddingLeft(10);
				startHourValue.setBackgroundColor(BaseColor.WHITE);
				table.addCell(startHourValue);
				
				String drivingEndHour = hourFormat.format(driving.getTimetable().getEnd());
				PdfPCell finishHourValue = new PdfPCell(new Paragraph(drivingEndHour, tableBody));
				finishHourValue.setBorderColor(BaseColor.BLACK);
				finishHourValue.setPaddingLeft(10);
				finishHourValue.setBackgroundColor(BaseColor.WHITE);
				table.addCell(finishHourValue);
				
				PdfPCell instructorValue = new PdfPCell(new Paragraph(driving.getTimetable().getInstructor().getName().concat(" ".concat(driving.getTimetable().getInstructor().getSurname())), tableBody));
				instructorValue.setBorderColor(BaseColor.BLACK);
				instructorValue.setPaddingLeft(10);
				instructorValue.setBackgroundColor(BaseColor.WHITE);
				table.addCell(instructorValue);
				
				PdfPCell carValue = new PdfPCell(new Paragraph(driving.getTimetable().getCar().getRegistrationNumber(), tableBody));
				carValue.setBorderColor(BaseColor.BLACK);
				carValue.setPaddingLeft(10);
				carValue.setBackgroundColor(BaseColor.WHITE);
				table.addCell(carValue);

				

			}
			
			document.add(table);
			
			
			document.add(newLineParagraph);

			
			Paragraph dots = new Paragraph(".............................", mainFont);
			dots.setAlignment(Element.ALIGN_RIGHT);
			dots.setIndentationLeft(50);
			dots.setIndentationRight(50);
			dots.setSpacingAfter(10);
			document.add(dots);
			
			Paragraph sign = new Paragraph("Podpis", mainFont);
			sign.setAlignment(Element.ALIGN_RIGHT);
			sign.setIndentationLeft(50);
			sign.setIndentationRight(50);
			sign.setSpacingAfter(10);
			document.add(sign);
			

			document.addAuthor("Ośrodek Szkolenia Kierowców");
			document.addCreationDate();
			
			document.close();
			writer.close();
			return true;

		} catch (Exception e) {
			return false;
		}

	}
}
