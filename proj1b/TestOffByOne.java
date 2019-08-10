import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars1() {
        assertTrue(offByOne.equalChars('a', 'b'));
    }

    @Test
    public void testEqualChars2() {
        assertFalse(offByOne.equalChars('1', 'b'));
    }

    @Test
    public void testEqualChars3() {
        assertFalse(offByOne.equalChars('A', 'b'));
    }

    @Test
    public void testEqualChars4() {
        assertTrue(offByOne.equalChars('%', '&'));
    }
}
