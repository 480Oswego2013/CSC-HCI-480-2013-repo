package edu.oswego.csc480_hci521_2013.shared.h2o.urlbuilders;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Brian Bailey
 */
public class AbstractBuilderTest {
    
    public AbstractBuilderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addArg method, of class AbstractBuilder.
     */
    @Test
    public void testAddArg() {
        try {
            System.out.println("addArg");
            String key = ""; String value = "";
            
            // Test AbstractBuilder "addArg" method.
            AbstractBuilder instance = new AbstractBuilderImpl();
            AbstractBuilder expResult = instance.addArg(key, value);
            assertNotNull(expResult);
            assertTrue(expResult instanceof AbstractBuilder);
            
            // Test if "key / value" is added to "args" Map<String, String>.
            Field argsField = AbstractBuilder.class.getDeclaredField("args");
            argsField.setAccessible(true);
            Map<String, String> arg = (Map<String, String>) argsField.get(instance);
            
            assertFalse(arg.isEmpty());            
            boolean containsKey = arg.containsKey(key);
            assertTrue(containsKey);
            boolean containsValue = arg.containsValue(value);
            assertTrue(containsValue);
            
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of addMultiArg method, of class AbstractBuilder.
     */
    @Test
    public void testAddMultiArg() {
        try {
            System.out.println("addMultiArg");
            String key = ""; String value = "";
            
            // Test AbstractBuilder "setProtocol" method.
            AbstractBuilder instance = new AbstractBuilderImpl();            
            AbstractBuilder expResult = instance.addMultiArg(key, value);
            assertNotNull(expResult);
            assertTrue(expResult instanceof AbstractBuilder);
            
            // Test if "AddMultiArg" is added to "multiargs" List<Arg>.
            Field multiargsField = AbstractBuilder.class.getDeclaredField("multiargs");
            multiargsField.setAccessible(true);
            List multiarg = (List) multiargsField.get(instance);
            
            assertFalse(multiarg.isEmpty()); // Works for now, Check if object is added to list
            //boolean containsKey = multiarg.contains(key);
            Object argObj = multiarg.get(0);
            assertNotNull(argObj);
            
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Test of setProtocol method, of class AbstractBuilder.
     */
    @Test
    public void testSetProtocol() {
        try {
            String protocol = "setProtocol";
            System.out.println(protocol);
            
            // Test AbstractBuilder "setProtocol" method.
            AbstractBuilder instance = new AbstractBuilderImpl();
            AbstractBuilder expResult = instance.setProtocol(protocol);
            assertNotNull(expResult);
            assertTrue(expResult instanceof AbstractBuilder);

            // Test if AbstractBuilder "port" field was set.
            Field portField = AbstractBuilder.class.getDeclaredField("protocol");
            portField.setAccessible(true);
            String result = (String) portField.get(instance);
            assertEquals(protocol, result);
            
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setHost method, of class AbstractBuilder.
     */
    @Test
    public void testSetHost() {
        try {
            String host = "setHost";
            System.out.println(host);            
            
            // Test AbstractBuilder "setHost" method.
            AbstractBuilder instance = new AbstractBuilderImpl();
            AbstractBuilder expResult = instance.setHost(host);
            assertNotNull(expResult);
            assertTrue(expResult instanceof AbstractBuilder);
            
            // Test if AbstractBuilder "port" field was set.
            Field portField = AbstractBuilder.class.getDeclaredField("host");
            portField.setAccessible(true);
            String result = (String) portField.get(instance);
            assertEquals(host, result);
            
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setPort method, of class AbstractBuilder.
     */
    @Test
    public void testSetPort() {
        try {
            System.out.println("setPort");
            Integer port = 0;
            
            // Test AbstractBuilder "setPort" method.
            AbstractBuilder instance = new AbstractBuilderImpl();
            AbstractBuilder expResult = instance.setPort(port.intValue());
            assertNotNull(expResult);
            assertTrue(expResult instanceof AbstractBuilder);
            
            // Test if AbstractBuilder "port" field was set.
            Field portField = AbstractBuilder.class.getDeclaredField("port");
            portField.setAccessible(true);
            Integer result = (Integer) portField.get(instance);
            assertEquals(port, result);

        } catch (IllegalArgumentException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AbstractBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of build method, of class AbstractBuilder.
     */
    @Test
    public void testBuild() {
//        System.out.println("build");
//        AbstractBuilder instance = new AbstractBuilderImpl();
//        String expResult = "";
//        String result = instance.build();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    public class AbstractBuilderImpl extends AbstractBuilder {
    }
}