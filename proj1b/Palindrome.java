public class Palindrome {
    /**  Task2: word to deque
     *
     *  将String变为Deque
     *
     *   @Args:
     *       word (String)
     *
     *   @Returns:
     *       wordDeq (Deque)
     * */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeq = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordDeq.addLast(word.charAt(i));
        }
        return wordDeq;
    }

    /**  Task3B: is palindrome
     *
     *   @Args:
     *       word (String)
     *
     *   @Returns:
     *       true / false
     * */
    public boolean isPalindrome(String word) {
        Deque<Character> wordDeq = wordToDeque(word);
        if (wordDeq.size() == 0 || wordDeq.size() == 1) {
            return true;
        } else {
            if (wordDeq.removeFirst() == wordDeq.removeLast()) {
                return isPalindrome(dequeToString(wordDeq));
            } else {
                return false;
            }
        }
    }

    /**  isPalindrome helper function
     *
     *   @Args:
     *       wordDeq (Deque)
     *
     *   @Returns:
     *       res (String)
     * */
    private String dequeToString(Deque wordDeq) {
        StringBuilder res = new StringBuilder();
        int size = wordDeq.size();  // 随着remove，size会变化，提前初始化
        for (int i = 0; i < size; i++) {
            res.append(wordDeq.removeFirst());
        }
        return res.toString();
    }

    /**  Task4: offByOne
     *
     *   @Args:
     *       word (String)
     *       cc (CharacterComparator)
     *
     *   @Returns:
     *      true: the word is a palindrome
     *      false
     *
     * */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeq = wordToDeque(word);
        if (wordDeq.size() == 0 || wordDeq.size() == 1) {
            return true;
        } else {
            if (cc.equalChars(wordDeq.removeFirst(), wordDeq.removeLast())) {
                return isPalindrome(dequeToString(wordDeq), cc);
            } else {
                return false;
            }
        }
    }

}
