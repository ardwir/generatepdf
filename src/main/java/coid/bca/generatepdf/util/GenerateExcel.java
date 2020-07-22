package coid.bca.generatepdf.util;

import coid.bca.generatepdf.model.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	private static final Logger logger = LoggerFactory.getLogger(GenerateExcel.class);
	
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
			System.out.println("Masuk Util broww");
			Workbook wb = new XSSFWorkbook(is);
			System.out.println("WB udah siap");
			
			Sheet sheet = wb.getSheet(SHEET);
			System.out.println("udh ad sheetnya");
			
			Iterator<Row> rows = sheet.iterator();
			System.out.println("Iterator Row: " + rows);
			
			List<Transaction> listTrx = new ArrayList<Transaction>();
			System.out.println("udah siapin array");
			
			int rowNumber = 0;
			while(rows.hasNext()) {
				Row currentRow = rows.next();
				System.out.println("Current Row: "+ currentRow);
				
				//skip header
				if (rowNumber == 0) {
					rowNumber++;
					System.out.println("sekip header");
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
						System.out.println("Masuk baris kolom 1");
						transaction.setTrxId((long) currentCell.getNumericCellValue());
						break;
					
					case 1:
						System.out.println("Masuk baris kolom 2");
						transaction.setUname(currentCell.getStringCellValue());
						break;
						
					case 2:
						System.out.println("Masuk baris kolom 3");
						transaction.setAcctDestination((int) currentCell.getNumericCellValue());
						break;
						
					case 3:
						System.out.println("Masuk baris kolom 4");
						transaction.setTrxDate((Date) currentCell.getDateCellValue());
						break;
						
					case 4:
						System.out.println("Masuk baris kolom 5");
						transaction.setTrxAmount(currentCell.getNumericCellValue());
						break;
						
					case 5:
						System.out.println("Masuk baris kolom 6");
						transaction.setTrxType(currentCell.getStringCellValue());
						break;
					
					case 6:
						System.out.println("Masuk baris kolom 7");
						transaction.setTrxStatus(currentCell.getStringCellValue());
						break;
						
					default:
						System.out.println("Masuk default");
						break;
					}
					
					cellIdx++;
					System.out.println("CellIdx BARU: " + cellIdx);
				}
				
				System.out.println("Udah jadi masukin ke listTrx");
				listTrx.add(transaction);
				System.out.println("Jadi gini: " + listTrx);
			}
			wb.close();
			return listTrx;
		}
		catch(IOException e){
			System.out.println("Baca Excelnya gagal nih");
			throw new RuntimeException("Fail to parse Excel File: " + e.getMessage());
		}
		
	}
	
}
