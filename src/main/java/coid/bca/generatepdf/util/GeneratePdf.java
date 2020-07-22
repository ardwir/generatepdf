package coid.bca.generatepdf.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import coid.bca.generatepdf.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.util.List;

public class GeneratePdf {
	
	private static final Logger logger = LoggerFactory.getLogger(GeneratePdf.class);
	
	public static ByteArrayInputStream listTrx(List<Transaction> trx) {
		
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		
		//Title
		PdfPTable title = new PdfPTable(1);
		title.setWidthPercentage(100);
		PdfPCell tcell;
		tcell = new PdfPCell(new Phrase("<BranchName> Account Mutation", headFont));
		tcell.setBorderColor(BaseColor.WHITE);
		tcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		title.addCell(tcell);
		tcell = new PdfPCell(new Phrase("Download on: " + timestamp, FontFactory.getFont(FontFactory.HELVETICA)));
		tcell.setBorderColor(BaseColor.WHITE);
		tcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		title.addCell(tcell);
		
		//Table List
		try {			

			PdfPTable table = new PdfPTable(7);
			table.setWidthPercentage(100);
			table.setWidths(new int[]{3, 3, 3, 3, 3, 3, 3});
			
			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Trx ID", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Username", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Destination", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Date", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Amount", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Type", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Status", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			
			for(Transaction transaction : trx) {
				
				PdfPCell cell;
				
				cell = new PdfPCell(new Phrase(transaction.getTrxId().toString()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(transaction.getUname()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(transaction.getAcctDestination().toString()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(transaction.getTrxDate().toString()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(transaction.getTrxAmount().toString()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(transaction.getTrxType()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(transaction.getTrxStatus()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
			}
			
			
			
			PdfWriter.getInstance(document, out);
			document.open();
			document.add(title);
			
			document.addTitle("Branch Transaction Mutation");
			
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));
			
			document.add(table);
			document.close();
			
		}catch(DocumentException ex) {
			
			logger.error("Error Occurred: {0}", ex);
		}
		
		return new ByteArrayInputStream(out.toByteArray());
	}
	
}
