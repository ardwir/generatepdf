package coid.bca.generatepdf.repository;

import coid.bca.generatepdf.model.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
