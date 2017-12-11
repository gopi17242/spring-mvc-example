package com.journaldev.spring.controller;

import java.util.ArrayList;
import java.util.List;

import com.journaldev.spring.model.Contacts;
import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.Account;
import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
	public class TestSalesForce {
	static final String USERNAME = "gopinadh17242@gmail.com";
	static final String PASSWORD = "vertex123GtfHm6lSrYmoEoA8NU6FzhBY";
	
	  static EnterpriseConnection connection;

	  public static void getContactDetails() {

	    ConnectorConfig config = new ConnectorConfig();
	    config.setUsername(USERNAME);
	    config.setPassword(PASSWORD);
	    //config.setTraceMessage(true);
	  
	    try {
	    
	      connection = Connector.newConnection(config);
	    
	      // display some current settings
	      System.out.println("Auth EndPoint: "+config.getAuthEndpoint());
	      System.out.println("Service EndPoint: "+config.getServiceEndpoint());
	      System.out.println("Username: "+config.getUsername());
	      System.out.println("SessionId: "+config.getSessionId());
	    
	      // run the different examples
	      queryContacts();
	  //    createAccounts();
	  //    updateAccounts();
	  //    deleteAccounts();
	    
	    } catch (ConnectionException e1) {
	        e1.printStackTrace();
	    } 

	  }
		
public boolean validateUser(String userid,String password) throws ConnectionException {

		boolean isExist = false;
		//////////////
		System.out.println("====================getContactsList===========================");
		System.out.println("====================getContactsList==============test=============");
		// query for the 5 newest contacts     
		QueryResult queryResultsc = connection.query("SELECT Id, Email, Password__c, FirstName, LastName, Phone, Title, Account.Name " +
				"FROM Contact WHERE Email ='"+userid+"'");
		if (queryResultsc.getSize() > 0) {
			
			 Contact c = (Contact)queryResultsc.getRecords()[0];
			String passwordDB = c.getPassword__c();
			
			if(password.equals(passwordDB)){
			isExist = true;
			}

		}

		return isExist;




	}

	public static List<Contacts> getContactsList() throws ConnectionException{
	  
	  
	  //////////////
      System.out.println("===============================================");
      // query for the 5 newest contacts     
      QueryResult queryResultsc1 = connection.query("SELECT Id, Email, Password__c, FirstName, LastName, Phone, Title, Account.Name " +
        "FROM Contact ");
      
      List<Contacts> contactsList = new ArrayList<Contacts>();
      
      if (queryResultsc1.getSize() > 0) {
        for (int i1=0;i1<queryResultsc1.getRecords().length;i1++) {
          // cast the SObject to a strongly-typed Contact
          Contact c1 = (Contact)queryResultsc1.getRecords()[i1];
          System.out.println("Id: " + c1.getId() +" -Email id -"+c1.getEmail()+ " - Name: "+c1.getFirstName()+" "+
              c1.getLastName()+" - Phone: "+c1.getPhone()+" - Title: "+c1.getTitle());
          
          Contacts contacts = new Contacts();
          contacts.setId(c1.getId());
          contacts.setEmail(c1.getEmail());
          contacts.setFirstName(c1.getFirstName());
          contacts.setName(c1.getFirstName());
          contacts.setTitle(c1.getTitle());
          contacts.setPhone(c1.getPhone());
          contactsList.add(contacts);
          
        }
	      
	      
      }
		
		 System.out.println("contactsList===="+contactsList.size());
	return contactsList;
      
      //////////////
	  
  }
  


public static String decrypt(StringBuilder str,int key)
  {
    for(int i=0; i<=(str.length() - 1); i++)
    {
      char c = (char)(str.charAt(i) + key);
      str.setCharAt(i,c);
    }
    return new String(str);
  }
	  // queries and displays the 5 newest contacts
	  private static void queryContacts() {
	  
	    System.out.println("Querying for the 5 newest Contacts...");
	  
	    try {
	    	
	    	
	    	// retrive logins details for validate
	    	
	    	  // query for the 5 newest contacts     
	        QueryResult queryResultsc = connection.query("SELECT Id, Email, Password__c, FirstName, LastName, Account.Name " +
	          "FROM Contact WHERE Id = '0037F00000IL4nFQAT'");
	        if (queryResultsc.getSize() > 0) {
	          for (int i=0;i<queryResultsc.getRecords().length;i++) {
	            // cast the SObject to a strongly-typed Contact
	            Contact c = (Contact)queryResultsc.getRecords()[i];
		//	StringBuilder  str = new StringBuilder(c.getPassword__c());
  		//		String  decriptedPassword = decrypt(str,0xFACA);
		//	  System.out.println("passssssss======"+decriptedPassword);
			   
	    //        System.out.println("Id: " + c.getId() +" -Email id -"+c.getEmail()+" -Password -"+c.getPassword__c()+ " - Name: "+c.getFirstName()+" "+
	    //            c.getLastName()+" - Phone: "+c.getPhone()+" - Title: "+c.getTitle());
	          }
	        }
	    	
	      
	/*    // query for the 5 newest contacts     
	      QueryResult queryResults = connection.query("SELECT Id, Email, Password__c, Phone,Title, FirstName, LastName, Account.Name " +
	        "FROM Contact WHERE AccountId != NULL ORDER BY CreatedDate DESC LIMIT 5");
	      if (queryResults.getSize() > 0) {
	        for (int i=0;i<queryResults.getRecords().length;i++) {
	          // cast the SObject to a strongly-typed Contact
	          Contact c = (Contact)queryResults.getRecords()[i];
	          System.out.println("Id: " + c.getId() +" -Password -"+c.getPassword__c()+" -Email id -"+c.getEmail()+ " - Name: "+c.getFirstName()+" "+
	              c.getLastName()+" - Phone: "+c.getPhone()+" - Title: "+c.getTitle());
	        }
	      }
	          */
	      /*    // query for the 5 newest contacts     
	          QueryResult queryResults = connection.query("SELECT id , Name, Type  " +
	            "FROM Account");
	          if (queryResults.getSize() > 0) {
	            for (int i=0;i<queryResults.getRecords().length;i++) {
	              // cast the SObject to a strongly-typed Contact
	            	Account c = (Account)queryResults.getRecords()[i];
	              System.out.println(" - Id: "+c.getId()+" - Name: "+c.getName()+"- Type "+c.getType());
	          */
	    	
	    	
	    	 /*// query for the 5 newest contacts     
	        QueryResult queryResults = connection.query("SELECT LoginTime " +
	          "FROM LoginHistory");
	        if (queryResults.getSize() > 0) {
	          for (int i=0;i<queryResults.getRecords().length;i++) {
	            // cast the SObject to a strongly-typed Contact
	        	  LoginHistory c = (LoginHistory)queryResults.getRecords()[i];
	            System.out.println(" - Id: "+c.getLoginTime());//+" - Name: "+c.getName()+"- Type "+c.getType());
	        }
	     }*/
	    	
	      
	    	
	        
	     //   LoginResult loginResult = new LoginResult();
	      //  System.out.println("============="+loginResult);
	    
	    } catch (Exception e) {
	      e.printStackTrace();
	    }   
	  
	  }

	  // create 5 test Accounts
	  private static void createAccounts() {
	  
	    System.out.println("Creating 5 new test Accounts...");
	    Account[] records = new Account[5];
	  
	    try {
	      
	      // create 5 test accounts
	      for (int i=0;i<5;i++) {
	        Account a = new Account();
	        a.setName("Test Account "+i);
	        records[i] = a;
	      }
	    
	      // create the records in Salesforce.com
	      SaveResult[] saveResults = connection.create(records);
	    
	      // check the returned results for any errors
	      for (int i=0; i< saveResults.length; i++) {
	        if (saveResults[i].isSuccess()) {
	          System.out.println(i+". Successfully created record - Id: " + saveResults[i].getId());
	        } else {
	          com.sforce.soap.enterprise.Error[] errors = saveResults[i].getErrors();
	          for (int j=0; j< errors.length; j++) {
	            System.out.println("ERROR creating record: " + errors[j].getMessage());
	          }
	        }   
	      }
	    
	    } catch (Exception e) {
	      e.printStackTrace();
	    }   
	  
	  }
	}
	
	

