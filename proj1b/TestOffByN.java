import org.junit.Test;
import static org.junit.Assert.*;
public class TestOffByN {
    @Test
    public void testOffByFive() {
        CharacterComparator offByFive = new OffByN(5);
        assertFalse(offByFive.equalChars('b', 'a'));
        assertTrue(offByFive.equalChars('f', 'a'));
        assertTrue(offByFive.equalChars('a', 'f'));
    }
}
