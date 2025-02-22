package ttst;

import com.example.FixtureAddItemTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddItemUnitTest {

    // FixtureAddItemTest
    //(categoryID.length() > 15) -> "Failure"
    //((bidIncrement < 20) || (bidIncrement%10 != 0)) -> "Failure"
    //((description.length() < 15) || (description.length() > 50)) -> "Failure"

    @Test
    public void testFixtureAddItem1() {   //bidIncrement < 20, description < 15
        FixtureAddItemTest t = new FixtureAddItemTest();
        String res = t.result("ciao", 12, "0");
        assertEquals("Failure", res);
    }

    @Test
    public void testFixtureAddItem2() {
        FixtureAddItemTest t = new FixtureAddItemTest();
        String res = t.result("ciao", 30, "gagagagagagagagagaggagagagag");
        assertEquals("Success", res);
    }

    @Test
    public void testFixtureAddItem3() {   //bidIncrement < 20
        FixtureAddItemTest t = new FixtureAddItemTest();
        String res = t.result("ciao", 15, "gagagagagagagagagaggagagagag");
        assertEquals("Failure", res);
    }

    @Test
    public void testFixtureAddItem4() {   //description < 15
        FixtureAddItemTest t = new FixtureAddItemTest();
        String res = t.result("ciao", 20, "pizza");
        assertEquals("Failure", res);
    }

    @Test
    public void testFixtureAddItem5() {   //categoryID > 15
        FixtureAddItemTest t = new FixtureAddItemTest();
        String res = t.result("ciaociaociaociaociaociaociao", 20, "pizzapizzapizzapizzapizza");
        assertEquals("Failure", res);
    }

}
