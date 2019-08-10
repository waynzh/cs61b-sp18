import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    @Test
    public void testEqualChars() {
        assertTrue(offByN.equalChars('a', 'f'));
    }
}
