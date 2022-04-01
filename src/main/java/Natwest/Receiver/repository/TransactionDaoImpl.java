package Natwest.Receiver.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import Natwest.Receiver.bean.Transaction;

@Repository
public class TransactionDaoImpl implements TransactionDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Override
	public boolean addTransaction(Transaction txn) {
		try {
		

			String sql ="insert into transaction (txn_id,account_no,txn_type,currency,amount,account_from) values "
			+ "(?,?,?,?,?,?)";

			jdbcTemplate.update(sql,txn.getTxn_id(),txn.getAccountNumber(),txn.getType(),txn.getCurrency(),txn.getAmount(),txn.getAccountFrom());
			
			return true;
		
		}catch(Exception e ) {
			e.printStackTrace();
			return false;
		}
		
	}


	@Override
	public int getTxnId() {
		try {
			String sql ="select max(txn_id) from transaction ";
			Integer txn_id = jdbcTemplate.queryForObject(sql,Integer.class );
			if(txn_id==null) {
				return 0;
			}
			
			return txn_id+1;
		}catch(Exception e ) {
			e.printStackTrace();
			return -1;
			
		}
		
	}

	
}
