class Solution638 {
    public boolean checkValidString(String s) {
        int lo = 0, hi = 0;

        // When I set all * to (, it should be positive, oppositely, 
        // when O set all *to ), it should be <= 0
        // To sum up, finally there is a way to set it to zero
        for (char c : s.toCharArray()) {
            lo += c == '(' ? 1 : -1;        
            hi += c != ')' ? 1 : -1;
            if (hi < 0)                     
                break;
            lo = Math.max(lo, 0);
        }
        return lo == 0;
    }
}