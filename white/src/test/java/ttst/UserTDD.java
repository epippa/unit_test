package ttst;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTDD {

    //testPlaceNewBid verifies if placeNewBid() correctly places a new bid on an auction and updates the bid list.
    //then it creates a new auction and a new user (usname=test1, psw=1234).
    //it sets a bid of 100 on the auction using placeNewBid(), and checks if the bid has been successfully added to
    //the auction's and user's bid list.
    @Test
    public void testPlaceNewBid() {
        Auction auction = new Auction();
        User user = new User("test1", "1234");
        user.placeNewBid(auction, 100);
        assertEquals(1, auction.getBids().size());
        assertEquals(1, user.getBids().size());
    }

    //verifies if getBids() retrieves the list of bids placed by the user.
    //it creates a new user (test2, psw=admin) and a new auction.
    //it sets a bid of 100 on the auction using the placeNewBid(), and checks if the user's bid list contains the placed bid.
    @Test
    public void testGetBids() {
        User user = new User("test2", "admin");
        Auction auction = new Auction();
        user.placeNewBid(auction, 100);
        assertEquals(1, user.getBids().size());
    }
}
