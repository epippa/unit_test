package ttst;

import exceptions.AuctionExpiredException;
import exceptions.InvalidAuctionIdException;
import exceptions.LowPriceException;
import exceptions.NotOpenedAuctionException;
import org.junit.Before;
import org.junit.Test;

import com.example.FixturePlaceBid;

import static org.junit.Assert.*;

public class UserPlaceBidFixture {

    private Auction openAuction;
    private Auction closeAuction;
    private Auction itemToBeSold;
    private long openAuctionID;
    private long closedAuctionID;
    private long itemToBeSoldID;
    private long auctionId;
    private double price;
    private String expectedException;

    @Before
    public void setup() {
        long currentTime = System.currentTimeMillis();
        long startTime = currentTime - 1000;
        long endTime = currentTime + 1000 * 60 * 60;

        Item item1 = new Item("Casa", 1000, null, 1);
        Item item2 = new Item("OcchialiSole", 20, null, 3);

        this.openAuction = new Auction(item1, 10000, startTime, endTime);
        this.openAuctionID = openAuction.getId();

        this.closeAuction = new Auction(item1, 10000, 0, 10);
        this.closedAuctionID = closeAuction.getId();

        this.itemToBeSold = new Auction(item2, 20, 38, 55);
        this.itemToBeSoldID = itemToBeSold.getId();
    }

    @Test
    public void placeBid1() throws Exception { // True
        User user = new User("Ema", "12345");
        boolean result = user.placeBid(openAuctionID, 11000);
        assertTrue(result);
    }

    @Test // Wrong Bid
    public void placeBid2() throws AuctionExpiredException, NotOpenedAuctionException, InvalidAuctionIdException {
        try {
            User user = new User("Simo", "blabla");
            user.placeBid(openAuctionID, 0);
            fail();
        } catch (LowPriceException e) {
        }
    }

    @Test // placeBidOnClosedAuction
    public void placeBid3() throws LowPriceException, NotOpenedAuctionException, InvalidAuctionIdException {
        try {
            User user = new User("Emanuele", "blabla");
            user.placeBid(closedAuctionID, 12000);
            fail();
        } catch (AuctionExpiredException e) {
        }
    }

    @Test
    public void placeBid4()
            throws LowPriceException, AuctionExpiredException, NotOpenedAuctionException, InvalidAuctionIdException {
        User user = new User("Tony", "admin");
        boolean res = user.placeBid(openAuctionID, 35000.69);
        assertTrue(res);
    }

    /*
     * @Test
     * public void testBidForAuction() throws Exception { ////////////// NON TROVO
     * L'ERRORE /////////////
     * ////////////// IL DEBUG MI BUTTA IN SELLER ////
     * ////////////// RIGA 130 (INVALID OWNER) ///////
     * Seller seller = new Seller("Tony", "admin");
     * boolean sell = seller.createAuction(itemToBeSoldID, 70, 45, 50);
     * //boolean sell = seller.createAuction(0, 70, 45, 126);
     * assertFalse(sell);
     * }
     */

    // FixturePlaceBid
    // (auctionID < 1) -> "Failure"
    // ((bidIncrement < 20) || (bidIncrement%10 != 0)) -> "Failure"

    @Test
    public void testFixturePlaceBid1() {
        FixturePlaceBid t = new FixturePlaceBid();
        String result = t.result(30, 12);
        assertEquals("Success", result);
    }

    @Test
    public void testFixturePlaceBid2() { // bidIncrement < 20
        FixturePlaceBid t = new FixturePlaceBid();
        String result = t.result(12, 12);
        assertEquals("Failure", result);
    }

    @Test
    public void testFixturePlaceBid3() { // auctionID < 1
        FixturePlaceBid t = new FixturePlaceBid();
        String result = t.result(30, 0);
        assertEquals("Failure", result);
    }

    public void setAuctionId(long auctionId) {
        this.auctionId = auctionId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setExpectedResult(String expectedException) {
        this.expectedException = expectedException;
    }

    public boolean placeBidTest() {
        try {
            User user = User.getInstance();
            return user.placeBid(auctionId, price);
        } catch (InvalidAuctionIdException e) {
            return expectedException.equals("InvalidAuctionIdException");
        } catch (NotOpenedAuctionException e) {
            return expectedException.equals("NotOpenedAuctionException");
        } catch (LowPriceException e) {
            return expectedException.equals("LowPriceException");
        } catch (AuctionExpiredException e) {
            return expectedException.equals("AuctionExpiredException");
        }
    }
}
