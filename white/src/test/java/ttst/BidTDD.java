package ttst;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BidTDD {

    private User user;
    private Bid bid;

    //initializes the user and bid objects.
    //it creates a new user with a name and a password
    //creates a new Bid object with a price associated with this user
    @Before
    public void setUp() {
        user = new User("testTest", "password1");
        bid = new Bid(100.0, user);
    }

    //testGetPrice verifies if getPrice() returns the price set in seUp()
    //it compares the returned value with the expected value
    @Test
    public void testGetPrice() {
        assertEquals(100.0, bid.getPrice(), 0.1);
    }

    //verifies if getTime() returns a time value (within one second from the current time)
    @Test
    public void testGetTime() {
        long currentTime = System.currentTimeMillis();
        long bidTime = bid.getTime();
        assertTrue(bidTime <= currentTime && bidTime > currentTime - 1000);
    }

    //it verifies if getBidder() correctly returns the user associated with the bid
    @Test
    public void testGetBidder() {
        assertEquals(user, bid.getBidder());
    }
}
