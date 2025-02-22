package ttst;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class AuctionUnitTest {

    // Create an instance of the Auction class for testing
    private Auction auction = new Auction();

    // Define the parameters for the test cases
    @Parameterized.Parameters(name = "{index}: nextPrice={0}, price={1}, expected={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 1.0, 1.0, true },
                { 1.5, 1.0, false },
                { -2.0, -1.0, true },
                { -0.5, -1.0, false },
                { 100.0, 150.0, true }, // nextPrice = 100.0, price = 150.0, expected = true
                { 100.0, 100.0, true }, // nextPrice = 100.0, price = 100.0, expected = true
                { 100.0, 50.0, false } // nextPrice = 100.0, price = 50.0, expected = false
        });
    }

    // Parameters for the current test case
    private double nextPrice;
    private double price;
    private boolean expected;

    // Constructor to initialize the parameters
    public AuctionUnitTest(double nextPrice, double price, boolean expected) {
        this.nextPrice = nextPrice;
        this.price = price;
        this.expected = expected;
    }

    @Test
    public void testCheckPriceValidity() {
        auction.setNextPrice(nextPrice); // Set nextPrice
        assertEquals(expected, auction.checkPriceValidity(price)); // Test with the given price
    }

    @Test
    public void testStartPrice1(){    //delta = 0,001
        Auction a = new Auction();
        a.setStartPrice(0.0);
        double temp = a.getNextPrice();
        assertEquals(0.0, temp, 0.001);
    }

    @Test
    public void testStartPrice2() {    //delta = 0,5
        Auction a = new Auction();
        a.setStartPrice(1.0);
        double temp = a.getNextPrice();
        assertEquals(1.248, temp, 0.5); //delta is the tolerance
    }

    @Test
    public void testPriceValidity1(){ //Price < NextPrice;
        Auction a = new Auction();
        a.setNextPrice(12.0);
        boolean temp = a.checkPriceValidity(2.0);
        assertFalse(temp);
    }

    @Test
    public void testPriceValidity2(){
        Auction a = new Auction();
        a.setNextPrice(8.0);
        boolean temp = a.checkPriceValidity(9.0);
        assertTrue(temp);
    }
}
