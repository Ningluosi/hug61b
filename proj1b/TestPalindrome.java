import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void isPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertFalse(palindrome.isPalindrome("aaaaab"));
        assertFalse(palindrome.isPalindrome("racecaR"));
    }

    @Test
    public void testisPalindromeOffByOne() {
        CharacterComparator cc = new OffByOne();
        assertFalse(palindrome.isPalindrome("flakz", cc));
        assertTrue(palindrome.isPalindrome("flake", cc));
        assertTrue(palindrome.isPalindrome("flabke", cc));
        assertFalse(palindrome.isPalindrome("flabre", cc));
        assertTrue(palindrome.isPalindrome("b", cc));
        assertFalse(palindrome.isPalindrome("flaBke", cc));
    }
}
