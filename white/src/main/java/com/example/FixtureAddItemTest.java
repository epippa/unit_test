package com.example;
import java.util.List;
import com.sun.net.httpserver.Authenticator;

public class FixtureAddItemTest {

    private int bidIncrement;
    private String categoryID, description;

    public void setBidIncrement(int bidIncrement) {
        this.bidIncrement = bidIncrement;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String result(String categoryID, int bidIncrement, String description) {
        if (categoryID.length() > 15){
            return "Failure";
        } else if((bidIncrement < 20) || (bidIncrement%10 != 0)){
            return "Failure";
        } else if((description.length() < 15) || (description.length() > 50)){
            return "Failure";
        }
        return "Success";
    }
}