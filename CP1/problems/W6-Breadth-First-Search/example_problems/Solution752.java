import java.util.ArrayDeque;
import java.util.HashSet;

class Solution {
    public int openLock(String[] deadends, String target) {
        ArrayDeque<int[]> ad = new ArrayDeque<>();
        HashSet<Integer> seen = new HashSet<>();
        ad.add(new int[] { 0, 0 });

        int password = Integer.parseInt(target);

        for (String a : deadends) {
            seen.add(Integer.parseInt(a));
        }

        while (ad.size() != 0) {
            int[] num = ad.removeFirst();
            if (seen.contains(num[0])) {
                continue;
            }

            if (num[0] == password)
                return num[1];
            seen.add(num[0]);

            String curNum = String.format("%04d", num[0]);
            for (int i = 0; i < 4; ++i) {
                char c = curNum.charAt(i);
                int n = Integer.valueOf("" + c);
                n += 1;
                if (n >= 10) n-= 10;

                String newValue = "";
                for (int j = 0; j < 4; ++j) {
                    if (j == i) {
                        newValue += String.valueOf(n);
                    } else {
                        newValue += curNum.charAt(j);
                    }
                }
                ad.add(new int[]{Integer.parseInt(newValue), num[1] + 1});
            }

            for (int i = 0; i < 4; ++i) {
                char c = curNum.charAt(i);
                int n = Integer.valueOf("" + c);
                n -= 1;
                if (n < 0)
                    n += 10;

                String newValue = "";
                for (int j = 0; j < 4; ++j) {
                    if (j == i) {
                        newValue += String.valueOf(n);
                    } else {
                        newValue += curNum.charAt(j);
                    }
                }
                ad.add(new int[] { Integer.parseInt(newValue), num[1] + 1 });
            }
        }
        return -1;
    }
}