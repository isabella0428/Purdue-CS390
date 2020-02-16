import java.util.Arrays;
import java.util.HashSet;

class Solution {
    public int numSpecialEquivGroups(String[] A) {
        int n = A.length;
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            int count[] = new int[52];
            for (int j = 0; j < A[i].length(); ++j) {
                char c = A[i].charAt(j);
                count[c - 'a' + 26 * (j % 2)]++;
            }
            set.add(Arrays.toString(count));
        }
        return set.size();
    }
}