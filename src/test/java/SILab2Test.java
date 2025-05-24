import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    public void everyStatementTest() {
        // 1 - allItems = null
        List<Item> list1 = null;
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(list1, "1111222233334444"));

        // 2 - item with empty name
        List<Item> list2 = new ArrayList<>();
        list2.add(new Item("", 2, 150, 0.3));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(list2, "1111222233334444"));

        // 3 - price > 300 and discount > 0, invalid card length
        List<Item> list3 = new ArrayList<>();
        list3.add(new Item("Monitor", 3, 500, 0.2));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(list3, "11112222333344445555"));

        // 4 - valid item, but invalid card characters
        List<Item> list4 = new ArrayList<>();
        list4.add(new Item("Mouse", 1, 200, 0));
        assertThrows(RuntimeException.class, () -> SILab2.checkCart(list4, "1111abcd33334444"));

        // 5 - everything valid
        List<Item> list5 = new ArrayList<>();
        list5.add(new Item("Keyboard", 2, 50, 0));
        double result = SILab2.checkCart(list5, "1111222233334444");
        assertEquals(100.0, result, 0.001);
    }

    @Test
    public void multipleConditionTest() {
        // 1 - only quantity > 10 is true
        List<Item> list1 = new ArrayList<>();
        list1.add(new Item("Paper", 11, 50  , 0));
        double result1 = SILab2.checkCart(list1, "2222333344445555");
        assertEquals(520.0, result1, 0.001);

        // 2 - only price > 300 is true
        List<Item> list2 = new ArrayList<>();
        list2.add(new Item("Desk", 1, 350, 0));
        double result2 = SILab2.checkCart(list2, "2222333344445555");
        assertEquals(320.0, result2, 0.001);

        // 3 - only discount > 0 is true
        List<Item> list3 = new ArrayList<>();
        list3.add(new Item("Pen", 1, 100, 0.3));
        double result3 = SILab2.checkCart(list3, "2222333344445555");
        assertEquals(40.0, result3, 0.001);

        // 4 - all conditions false
        List<Item> list4 = new ArrayList<>();
        list4.add(new Item("Notebook", 1, 100, 0));
        double result4 = SILab2.checkCart(list4, "2222333344445555");
        assertEquals(100.0, result4, 0.001);
    }
}
