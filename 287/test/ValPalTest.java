import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Crystal on 2/4/17.
 */
public class ValPalTest {
    ValPal testObj;
    boolean result;

    @Before
    public void setTestObj(){
        testObj = new ValPal();
        result = false;
    }

    @After
    public void setResult(){
        result = false; // set default to false;
    }

    //assertEquals
    @Test
    public void testOddT() {
        result = testObj.valPal("aaa");
        Assert.assertEquals(true, result);
    }

    @Test
    public void testOddF(){
        result = testObj.valPal("baa");
        Assert.assertEquals(false, result);
    }

    @Test
    public void testEvenT(){
        result = testObj.valPal("abba");
        Assert.assertEquals(true, result);
    }

    @Test
    public void testEvenF(){
        result = testObj.valPal("abbb");
        Assert.assertEquals(false, result);
    }

    //assertTrue
    @Test
    public void testWhiteSpaceT(){
        result = testObj.valPal("abb a");
        Assert.assertTrue(result);
    }

    //assertFalse
    @Test
    public void testWhiteSpaceF(){
        result = testObj.valPal("abb aa");
        Assert.assertFalse(result);
    }

    @Test
    public void testPuncMarks1(){
        result = testObj.valPal("aa,aa");
        Assert.assertFalse(result);
    }

    @Test
    public void testPuncMards2(){
        result = testObj.valPal("a,aaa");
        Assert.assertFalse(result);
    }

    @Test
    public void testEmptyStr(){
        result = testObj.valPal(" ");
        Assert.assertTrue(result);
    }

    @Test
    public void testNull(){
        result = testObj.valPal(null);
        Assert.assertFalse(result);
    }
}