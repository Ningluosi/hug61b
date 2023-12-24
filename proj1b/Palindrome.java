public class Palindrome {
    private boolean help(char first, char last, Deque<Character> deque, CharacterComparator cc) {
        if (cc == null) {
            if (first != last) {
                return false;
            }
        } else {
            if (!cc.equalChars(first, last)) {
                return false;
            }
        }

        if (deque.isEmpty() || deque.size() == 1) {
            return true;
        }
        return help(deque.removeFirst(), deque.removeLast(), deque, cc);
    }

    public Palindrome() {

    }
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        if (deque.isEmpty() || deque.size() == 1) {
            return true;
        }
        return help(deque.removeFirst(), deque.removeLast(), deque, null);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        if (deque.isEmpty() || deque.size() == 1) {
            return true;
        }

        return help(deque.removeFirst(), deque.removeLast(), deque, cc);
    }
}
