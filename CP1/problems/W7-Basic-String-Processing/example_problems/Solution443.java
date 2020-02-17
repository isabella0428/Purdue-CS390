class Solution {
    public int compress(char[] chars) {
        int i = 0, j = 0;
        if (chars.length < 2) {
            return chars.length;
        }
        while (i < chars.length) {
            ++i;
            int count = 1;
            while (i < chars.length && chars[i - 1] == chars[i]) {
                i++;
                count++;
            }
            chars[j++] = chars[i - 1];
            if (count == 1)
                continue;
            for (char c : String.valueOf(count).toCharArray()) {
                chars[j++] = c;
            }
        }
        return j;
    }
}