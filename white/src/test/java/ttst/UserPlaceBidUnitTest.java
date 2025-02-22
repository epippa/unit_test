package ttst;

import exceptions.AuctionExpiredException;
import exceptions.InvalidAuctionIdException;
import exceptions.LowPriceException;
import exceptions.NotOpenedAuctionException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class UserPlaceBidUnitTest {

    private User user;
    private final long auctionId;
    private final double price;
    private final boolean expected;
    private final Class<? extends Exception> expectedException;

    public UserPlaceBidUnitTest(long auctionId, double price, boolean expected,
            Class<? extends Exception> expectedException) {
        this.auctionId = auctionId;
        this.price = price;
        this.expected = expected;
        this.expectedException = expectedException;
    }

    @Parameterized.Parameters(name = "{index}: auctionId={0}, price={1}, expected={2}, expectedException={3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 1L, 100.0, true, null }, // Valid bid on auction 1
                { 1L, 200.0, true, null }, // Valid bid on auction 2
                { 1L, 40.0, false, LowPriceException.class }, // Low price on auction 2
                { 2L, 200.0, false, AuctionExpiredException.class }, // Expired auction 3
                { 3L, 150.0, false, NotOpenedAuctionException.class }, // Not opened auction 4
                { -1L, 80.0, false, InvalidAuctionIdException.class }, // Invalid auction ID
        });
    }

    @Before
    public void setUp() {
        // Initialize user
        user = new User("testUser", "testPassword");

        // Clear existing auctions and set up test auctions
        Auction.getAuctions().clear();

        long currentTime = System.currentTimeMillis();
        Item item = new Item("item1", 25.0, new ArrayList<>(), 1);

        // Auction 1: Valid, current and future valid bids
        Auction auction1 = new Auction(item, 50.0, currentTime - 10000, currentTime + 10000);
        Auction.getAuctions().add(auction1);
    }

    @Test
    public void testPlaceBid() {
        try {
            boolean result;
            Long auctionIdLong = auctionId;
            if(auctionIdLong.equals(-1L))
                 result = user.placeBid(auctionId, price);

            Auction auction = Auction.getAuctions().get(0);
            if(auctionIdLong.equals(2L))
                auction.setEndTime((long)10.0);
            else if (auctionIdLong.equals(3L))
                auction.setStartTime((long) System.currentTimeMillis() + 1000000);

            result = user.placeBid(auction.getId(), price);
            assertEquals(expected, result);
        } catch (Exception e) {
            if (expectedException != null) {
                assertTrue(expectedException.isInstance(e));
            } else {
                fail("Unexpected exception: " + e);
            }
        }
    }
}
