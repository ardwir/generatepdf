package coid.bca.generatepdf.service;

import coid.bca.generatepdf.model.Transaction;
import java.util.List;

public interface ITransactionService {
	
	List<Transaction> findAll();

}
