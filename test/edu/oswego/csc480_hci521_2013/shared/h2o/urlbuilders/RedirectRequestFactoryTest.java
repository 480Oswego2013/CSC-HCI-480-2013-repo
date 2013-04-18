package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import org.junit.Before;
import org.junit.Test;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.ResponseStatus;

public class RedirectRequestFactoryTest {

// jamie made this test 
	RedirectRequestFactory myRRF; 
	ResponseStatus myStatus; 
	H2ORequest myH2oResult; 

	
	@Before
	public void setUp() throws Exception {
		//lets me use my jar without initializing in every test
		myRRF = new RedirectRequestFactory();
                myStatus = new ResponseStatus();
	}
	
	/*
	 * check if the redirect test is being called 
	 */
	@Test
	public void testisRedirect() throws Exception {
		String result;
		myStatus.setStatus("redirect");
		 
              result = myStatus.getStatus();
		//if the status is true
		//a redirect was requested and message was sent
		if(result.equals("redirect")) {
			
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
		myStatus.setStatus("error");
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
		myStatus.setStatus("redirect");
                myStatus.setRedirectRequest("error");
		
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
            
                // excpected value
                String myValue = "ImportUrl";
                    
                // setting the status object
		myStatus.setStatus("redirect");
                myStatus.setRedirectRequest("ImportUrl");
                
                //checking the methods of the status object and redirect factory
                if(myStatus.getRedirectRequest().equals(myValue))
                {
                    System.out.println("the ImportUrl name is correct");
                }
                else
                    System.out.println("the ImportUrl name is wrong");
	}// end method
        
        /* 
	 * this test will test the InspectBuilder name 
	 */
	@Test
	public void testResquestInspectBuilder(){
            
                // excpected value
                String myValue = "Inspect";
                    
                // setting the status object
		myStatus.setStatus("redirect");
                myStatus.setRedirectRequest(myValue);
                
                //checking the methods of the status object and redirect factory
                if(myStatus.getRedirectRequest().equals(myValue))
                {
                    System.out.println("the InspectBuilder name is correct");
                }
                else
                    System.out.println("the InspectBuilder name is wrong");
	}// end method
        
         /* 
	 * this test will test the ParseBuilder name 
	 */
	@Test
	public void testResquestParseBuilder(){
            
                // excpected value
                String myValue = "Parse";
                    
                // setting the status object
		myStatus.setStatus("redirect");
                myStatus.setRedirectRequest(myValue);
                
                //checking the methods of the status object and redirect factory
                if(myStatus.getRedirectRequest().equals(myValue))
                {
                    System.out.println("the ParseBuilder name is correct");
                }
                else
                    System.out.println("the ParseBuilder name is wrong");
	}// end method
        
        /* 
	 * this test will test the ProgressBuilder name 
	 */
	@Test
	public void testResquestProgressBuilder(){
            
                // excpected value
                String myValue = "Progress";
                    
                // setting the status object
		myStatus.setStatus("redirect");
                myStatus.setRedirectRequest(myValue);
                
                //checking the methods of the status object and redirect factory
                if(myStatus.getRedirectRequest().equals(myValue))
                {
                    System.out.println("the ProgressBuilder name is correct");
                }
                else
                    System.out.println("the ProgressBuilder name is wrong");
	}// end method
        
        /* 
	 * this test will test the RFBuilder name 
	 */
	@Test
	public void testResquestRFBuilder(){
            
                // excpected value
                String myValue = "RF";
                    
                // setting the status object
		myStatus.setStatus("redirect");
                myStatus.setRedirectRequest(myValue);
                
                //checking the methods of the status object and redirect factory
                if(myStatus.getRedirectRequest().equals(myValue))
                {
                    System.out.println("the RFBuilder name is correct");
                }
                else
                    System.out.println("the RFBuilder name is wrong");
	}// end method
        
        /* 
	 * this test will test the InspectBuilder name 
	 */
	@Test
	public void testResquestRFTreeViewBuilder(){
            
                // excpected value
                String myValue = "RFTreeView";
                    
                // setting the status object
		myStatus.setStatus("redirect");
                myStatus.setRedirectRequest(myValue);
                
                //checking the methods of the status object and redirect factory
                if(myStatus.getRedirectRequest().equals(myValue))
                {
                    System.out.println("the RFTreeViewBuilder name is correct");
                }
                else
                    System.out.println("the RFTreeViewBuilder name is wrong");
	}// end method
        
                                         /* 
	 * this test will test the InspectBuilder name 
	 */
	@Test
	public void testResquestStoreViewBuilder(){
            
                // excpected value
                String myValue = "StoreView";
                    
                // setting the status object
		myStatus.setStatus("redirect");
                myStatus.setRedirectRequest(myValue);
                
                //checking the methods of the status object and redirect factory
                if(myStatus.getRedirectRequest().equals(myValue))
                {
                    System.out.println("the StoreViewBuilder name is correct");
                }
                else
                    System.out.println("the StoreViewBuilder name is wrong");
	}// end method
        
}

