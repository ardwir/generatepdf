package coid.bca.generatepdf.service;

import coid.bca.generatepdf.model.Transaction;
import coid.bca.generatepdf.repository.TransactionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ITransactionService{
	
	@Autowired
	private TransactionRepository trxRepository;
	
	@Override
	public List<Transaction> findAll(){
		return (List<Transaction>) trxRepository.findAll();
	}

}
