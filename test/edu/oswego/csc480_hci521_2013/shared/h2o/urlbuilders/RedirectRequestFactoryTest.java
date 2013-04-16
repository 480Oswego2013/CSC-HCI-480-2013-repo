package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import org.junit.Before;
import org.junit.Test;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.ResponseStatus;

public class RedirectRequestFactoryTest {


	RedirectRequestFactory myRRF; 
	ResponseStatus myStatus; 
	H2ORequest myH2oResult; 

	
	@Before
	public void setUp() throws Exception {
		//lets me use my jar without initializing in every test
		myRRF = new RedirectRequestFactory();
	}
	
	/*
	 * check if the redirect test is being called 
	 */
	@Test
	public void testisRedirect() throws Exception {
		
		//get the redirect status 
		boolean result = myStatus.isRedirect();
		
		//if the status is true
		//a redirect was requested and message was sent
		if(result == true){
			
			System.out.println("test isRedirect Passed");
		}
		else
			
			// no message a was sent 
			System.out.println("test isRedirect failed");
	}// end method
	
	/*
	 * this test will check the exception 
	 * is working at then end of the argument
	 */
	@Test 
	public void testFirstException(){
		//set the boolean to false
		myStatus.isError();
		try{
			RedirectRequestFactory.getRequest(myStatus);
		}
		catch(IllegalArgumentException e){
			
			System.out.println("caught expected error");
		}
	}// end method 
	
	/*
	 * this test will check if the last catch exception is working
	 */
	@Test 
	public void testLastException(){
		//set the redirect to fail
		myStatus.setRedirectRequest("null");
		
		try{
			RedirectRequestFactory.getRequest(myStatus);
		}
		catch(IllegalArgumentException e){
			
			System.out.println("caught expected error");
		}
	}
	
	/* 
	 * this test will test the importurl name 
	 */
	@Test
	public void testResquestImportURL(){
		String statusString = "ImportUrl";// set the name
		boolean myResult; // store result of string equals
		
		//set up the status
		myStatus.setRedirectRequest(statusString); 
		
		//pass the method into RRF
		RedirectRequestFactory.getRequest(myStatus);
		
		// get the result 
		myResult = statusString.equals(myStatus.getRedirectRequest());
		
		//check to see if the names are matching
		if(myResult == true){
			System.out.println("test RequestImportUrl name Passed");
		}
		else {
			System.out.println("test RequestImportUrl name failed");
		}// end if 
	}// end method
}
