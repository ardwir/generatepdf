package coid.bca.generatepdf.util;

import coid.bca.generatepdf.model.Transaction;

import java.util.Date;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class GenerateExcel {
	
	
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "trxId", "Username", "Destination", "Date", "Amount", "Type", "Status" };
	static String SHEET = "Sheet1";
	
	public static boolean hasExcelFormat(MultipartFile file) {
		
		if(!TYPE.contentEquals(file.getContentType())) {
			return false;
		}
		return true;
	}
	
	public static List<Transaction> excelTrx(InputStream is) {
		try {
			Workbook wb = new XSSFWorkbook(is);
			
			Sheet sheet = wb.getSheet(SHEET);
			
			Iterator<Row> rows = sheet.iterator();
			
			List<Transaction> listTrx = new ArrayList<Transaction>();
			
			int rowNumber = 0;
			while(rows.hasNext()) {
				Row currentRow = rows.next();
				System.out.println("Current Row: "+ currentRow);
				
				//skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				
				Iterator<Cell> cellInRow = currentRow.iterator();
				System.out.println("iterator cell: " + cellInRow);
				
				Transaction transaction = new Transaction();
				
				int cellIdx = 0;
				while(cellInRow.hasNext()) {
					Cell currentCell = cellInRow.next();
					System.out.println("currentCell: " + currentCell);
					System.out.println("cellIdx: " + cellIdx);
					
					switch(cellIdx) {
					case 0:
						transaction.setTrxId((long) currentCell.getNumericCellValue());
						break;
					
					case 1:
						transaction.setUname(currentCell.getStringCellValue());
						break;
						
					case 2:
						transaction.setAcctDestination((int) currentCell.getNumericCellValue());
						break;
						
					case 3:
						transaction.setTrxDate((Date) currentCell.getDateCellValue());
						break;
						
					case 4:
						transaction.setTrxAmount(currentCell.getNumericCellValue());
						break;
						
					case 5:
						transaction.setTrxType(currentCell.getStringCellValue());
						break;
					
					case 6:
						transaction.setTrxStatus(currentCell.getStringCellValue());
						break;
						
					default:
						break;
					}
					
					cellIdx++;
				}
				
				listTrx.add(transaction);
			}
			wb.close();
			return listTrx;
		}
		catch(IOException e){
			throw new RuntimeException("Fail to parse Excel File: " + e.getMessage());
		}
		
	}
	
}
