package Natwest.Receiver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import Natwest.Receiver.Util.AES;
import Natwest.Receiver.bean.Transaction;
import Natwest.Receiver.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	
	@Value("${secretKey}")
	private  String secretKey;
	
	
	@PostMapping("/transaction")
	public ResponseEntity<Void> addTransaction(@RequestBody String  encryptedTxn ) {
		try {
	
			System.out.println("In receiver encryptedTxn>>\n"+encryptedTxn);
			
			//1. decrypting given String
			String decryptedString = AES.decrypt(encryptedTxn, secretKey) ;	
			System.out.println("In receiver decrypted String>>\n"+decryptedString);
	
			//2. converting decrypted jsonString to Transaction object
			ObjectMapper mapper =new ObjectMapper();		
			Transaction txn = mapper.readValue(decryptedString, Transaction.class);
		
			//3.Adding Transaction into database
			boolean isTxnAdded = transactionService.addTransaction(txn);
			
			if(isTxnAdded) {
				return new ResponseEntity<Void>(HttpStatus.CREATED);
				
			}else {
				return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}catch(Exception  e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}

