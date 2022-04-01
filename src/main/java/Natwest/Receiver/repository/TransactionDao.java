package Natwest.Receiver.repository;

import Natwest.Receiver.bean.Transaction;

public interface TransactionDao {

	public boolean addTransaction(Transaction txn);
	public int getTxnId();
	
	
}
