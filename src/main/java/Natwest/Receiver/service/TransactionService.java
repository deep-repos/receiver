package Natwest.Receiver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Natwest.Receiver.bean.Transaction;
import Natwest.Receiver.repository.TransactionDaoImpl;

@Service
public class TransactionService {

	@Autowired
	TransactionDaoImpl transactionDaoImpl;
	
	public boolean addTransaction(Transaction txn) {
		
		int txn_id = transactionDaoImpl.getTxnId();
		
		if(txn_id!=-1) {
			
			txn.setTxn_id(txn_id);
			
			return transactionDaoImpl.addTransaction(txn);
		}
		else {
			return false;
		}
		
	}
	
	
}
