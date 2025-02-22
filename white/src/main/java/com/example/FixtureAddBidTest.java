package com.example;
import java.util.List;

public class FixtureAddBidTest {

    private int last_bidded_price, bidIncrement, auctionID;

    public void setBidIncrement(int bidIncrement) {
        this.bidIncrement = bidIncrement;
    }

    public void setLast_bidded_price(int last_bidded_price) {
        this.last_bidded_price = last_bidded_price;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    public String result(int auctionID, int bidIncrement, int last_bidded_price) {
        if (auctionID < 1){
            return "Failure";
        } else if((bidIncrement < 20) || (bidIncrement%10 != 0)){
            return "Failure";
        } else if((last_bidded_price < 20) || (last_bidded_price%10 != 0)){
            return "Failure";
        }
        return "Success";
    }
}