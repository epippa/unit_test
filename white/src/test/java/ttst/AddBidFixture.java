package ttst;

import ttst.Bid;
import ttst.Auction;

public class AddBidFixture {
    private long auctionID;
    private double lastBiddedPrice;
    private double incrementOfBid;

    private Bid bid;
    private boolean expectedResult;

    public void setAuctionID(long auctionID) {
        this.auctionID = auctionID;
    }

    public void setLastBiddedPrice(double lastBiddedPrice) {
        this.lastBiddedPrice = lastBiddedPrice;
    }

    public void setIncrementOfBid(double incrementOfBid) {
        this.incrementOfBid = incrementOfBid;
    }

    public boolean getExpectedResult() {
        return expectedResult;
    }


    public Boolean addBidTest() {
        Auction auction = Auction.getInstance();
        return auction.addBid(bid);
    }

}
