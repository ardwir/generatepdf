package coid.bca.generatepdf.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import coid.bca.generatepdf.repository.TransactionRepository;
import coid.bca.generatepdf.util.GenerateExcel;
import coid.bca.generatepdf.model.Transaction;

@Service
public class ExcelService {
	
	@Autowired
	TransactionRepository trxRepository;
	
	public void save(MultipartFile file) {
		try {
			System.out.println("Masuk service");
			List<Transaction> transactions = GenerateExcel.excelTrx(file.getInputStream());
			System.out.println("Udah dari Utility");
			trxRepository.saveAll(transactions);
		} catch (IOException e) {
			System.out.println("Gagal cuk!!");
			System.out.println(e.getMessage());
			throw new RuntimeException("Fail to Store ExcelData: " + e.getMessage());
		}
	}
	
	public List<Transaction> getAllTrasnsaction(){
		return trxRepository.findAll();
	}

}
