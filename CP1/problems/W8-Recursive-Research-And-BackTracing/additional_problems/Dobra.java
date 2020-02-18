import java.io.*;

// https://open.kattis.com/problems/dobra
// TODO: I don't quite understand what the constaints are.....
// Failed to pass the final given example

class Dobra{
    private static int count = 0;
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine().trim();

            int vowelCount = 0;
            for (char c : line.toCharArray()) {
                if (isVowel(c))
                    ++vowelCount;
            }

            backtrace(vowelCount, line.toCharArray(), 0, false, "", '0', '0', '0');

            System.out.println(count);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Boolean isVowel(char c) {
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            return true;
        }
        return false;
    }

    private static void backtrace(int vowelCount, char[] line, int pos, Boolean hasL, String cur, char prev1, char prev2, char prev3) {
        while (pos < line.length && line[pos] != '_') {
            if (line[pos] == 'L') 
                hasL = true;
            if (isVowel(line[pos]))
                ++vowelCount;
            ++pos;
        }

        if (pos >= line.length) {
            if (isVowel(prev1) && isVowel(prev2) && isVowel(prev3)) {
                return;
            }

            if (!isVowel(prev1) && !isVowel(prev2) &&!isVowel(prev3)) {
                return;
            }

            if (!hasL) return;

            ++count;
            return;
        }

        for (char c = 'A'; c <= 'Z'; ++c) {
            if (isVowel(c)) {
                backtrace(vowelCount + 1, line, pos + 1, hasL, cur + c, prev2, prev3, c);
            }

            if (c == 'L') {
                backtrace(vowelCount, line, pos + 1, true, cur + c, prev2, prev3, c);
            }
        }
    }
}