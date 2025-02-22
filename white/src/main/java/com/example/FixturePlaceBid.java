package com.example;

public class FixturePlaceBid {
    private int bidIncrement;
    private int auctionID;

    public void setAuction(int auctionID) {
        this.auctionID = auctionID;
    }

    public void setBidIncrement(int bidIncrement) {
        this.bidIncrement = bidIncrement;
    }

    public String result(int bidIncrement, int auctionID) {
        if (auctionID < 1) {
            return "Failure";
        } else if((bidIncrement < 20) || (bidIncrement%10 != 0)) {
            return "Failure";
        }
        return "Success";
    }

    public void setAuctionID(String auctionID) {
    }
}