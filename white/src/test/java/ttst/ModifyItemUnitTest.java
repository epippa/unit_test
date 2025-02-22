package ttst;

import com.example.FixtureModifyItemTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModifyItemUnitTest {

    // FixtureModifyItemTest
    //(categoryID.length() > 15) -> "Failure"
    //((bidIncrement < 20) || (bidIncrement%10 != 0)) -> "Failure"
    //((description.length() < 15) || (description.length() > 50)) -> "Failure"

    @Test
    public void testFixtureModifyItem() {
        FixtureModifyItemTest t = new FixtureModifyItemTest();
        String res = t.result("a", 20, "pizzapizzapizza");
        assertEquals("Success", res);
    }
}
