package ttst;

import exceptions.AuctionExpiredException;
import exceptions.InvalidAuctionIdException;
import exceptions.LowPriceException;
import exceptions.NotOpenedAuctionException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AuctionTDD {

    private Auction auction;
    private Item item;
    private User user;

    //initalizes: auction, item, user
    //create a new item (with a bid increment and 2 images) and initializes Auction with this item, a start price,
    //and a start(past) + end(future) time.
    @Before
    public void setUp() {
        ArrayList<Image> images = new ArrayList<>();
        images.add(new Image("file1"));
        images.add(new Image("file2"));

        item = new Item("testItem", 1.0, images, 1);
        auction = new Auction(item, 10.0, System.currentTimeMillis() - 1000, System.currentTimeMillis() + 100000);
        user = new User("test", "pass");
    }

    //tests if addBid() adds correctly a bid to the auction.
    //it creates a new bid and checks if the bid is added to the auction, and if the bid amount matches the expected value.
    @Test
    public void testAddBid() throws InvalidAuctionIdException, NotOpenedAuctionException, LowPriceException, AuctionExpiredException {
        double bidAmount = 20.0;
        auction.addBid(new Bid(bidAmount, user));

        assertEquals(1, auction.getBids().size());
        assertEquals(bidAmount, auction.getBids().get(0).getPrice(), 0.01);
    }

    //verifies if it correctly validates the bid price, and checks if the inserted bid is considered
    //valid(1°), and if a bid is considered invalid(2°)
    @Test
    public void testCheckPriceValidity() {
        assertTrue(auction.checkPriceValidity(20.0));
        assertFalse(auction.checkPriceValidity(5.0));
    }

    //it tests if hasStarted() correctly determines if the auction is started by asserting that it returns true
    @Test
    public void testHasStarted() {
        assertTrue(auction.hasStarted());
    }

    //it tests if hasExpired() correctly determines if the auction is started by asserting that it returns false
    @Test
    public void testHasExpired() {
        assertFalse(auction.hasExpired());
    }

    //testAcceptBid tests if acceptBid() accepts or rejects the bid, based on its amount:
    //it adds a bid (amount=20) to the auction. it checks if a higher bid (25) is accepted
    //and if a lower bid (10) is rejected.
    @Test
    public void testAcceptBid() {
        auction.addBid(new Bid(20.0, user));
        assertTrue(auction.acceptBid(25.0));
        assertFalse(auction.acceptBid(10.0));
    }

    //verifies if correctly returns the highest bid amount in the auction.
    //it adds two bids (25 and 35) to the auction and checks if the highest amount matches the expected value.
    @Test
    public void testGetHighestBidAmount() {
        auction.addBid(new Bid(25.0, user));
        auction.addBid(new Bid(35.0, user));
        assertEquals(35.01, auction.getHighestBidAmount(), 0.01);
    }
}
