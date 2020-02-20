import java.util.HashMap;

class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int a = 0; a < A.length; ++a) {
            for (int b = 0; b < B.length; ++b) {
                map.merge(A[a] + B[b], 1, Integer::sum);
            }
        }

        int ans = 0;
        for (int c = 0; c < C.length; ++c) {
            for (int d = 0; d < D.length; ++d) {
                ans += map.getOrDefault(-(C[c] + D[d]), 0);
            }
        }
        return ans;
    }
}