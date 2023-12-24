public class OffByN implements CharacterComparator {
    private int offSize;
    public OffByN(int N) {
        offSize = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (diff == offSize || diff == -offSize) {
            return true;
        }
        return false;
    }
}
