package coid.bca.generatepdf.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import coid.bca.generatepdf.message.ResponseMessage;
import coid.bca.generatepdf.model.Transaction;
import coid.bca.generatepdf.service.ExcelService;
import coid.bca.generatepdf.service.ITransactionService;
import coid.bca.generatepdf.util.GenerateExcel;
import coid.bca.generatepdf.util.GeneratePdf;

@Controller
public class MyController {
	
	@Autowired
	ExcelService fileService;
	
	@Autowired
	private ITransactionService trxService;
	
	@RequestMapping(value="/download", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> listTrx(){
		
		List<Transaction> trx = trxService.findAll();
		
		ByteArrayInputStream bis = GeneratePdf.listTrx(trx);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listTransaction.pdf");
		
		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
	
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file ){
		String message = "null";
		
		if(GenerateExcel.hasExcelFormat(file)) {
			try {
				System.out.println("Masuk try");
				fileService.save(file);
				System.out.println("udah save");
				
				message = "Upload Successfully: " + file.getOriginalFilename() + " !";
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			}catch (Exception e) {
				System.out.println("Gagal cuy");
				message = "Could not upload file: " + file.getOriginalFilename() + " !";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}
		
		message = "Please Upload an Excel File!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}
	
	@GetMapping("/show")
	public ResponseEntity<List<Transaction>> getAllTutorials() {
	    try {
	      List<Transaction> transactions = fileService.getAllTrasnsaction();

	      if (transactions.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(transactions, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

}
