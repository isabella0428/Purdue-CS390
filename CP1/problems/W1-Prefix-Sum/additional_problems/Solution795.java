// https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/

class Solution795 {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int j = 0, total_num = 0, count = 0;

        for(int i = 0; i < A.length; ++i) {
            if (A[i] >= L && A[i] <= R) {
                // Add number of subarrays that ends with A[i]
                total_num += i - j + 1;
                count = i - j + 1;
            } else if(A[i] < L){
                // Append to previous subarrays
                total_num += count;
            } else {
                j = i + 1;
                count = 0;
            }
        }
        return total_num;
    }
}