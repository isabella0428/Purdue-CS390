import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

// https://www.hackerrank.com/contests/cp-1-fall-2019-contest-1-late-submissions/challenges/unique-words-in-literature

class uniqueWords {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine().trim());

            Vector<String> seen = new Vector<>();
            Vector<String> duplicate = new Vector<>();
            while (N > 0) {
                String line = br.readLine().trim();
                String[] splited = line.split(" ", N);
                for (String word : splited) {
                    N--;
                    // Remove non-character, capital
                    String valid_string = processString(word);
                    if (seen.contains(valid_string))
                        duplicate.add(valid_string);
                    seen.add(valid_string);
                }  
            }

            int i = 0;
            for (String s : seen) {
                if (!duplicate.contains(s)) {
                    i++;
                    if (i == 2) {
                        System.out.println(s);
                        return;
                    }
                }
            }

            if (i < 2) {
                System.out.println(-1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String processString(String oldString) {
        String newString = "";
        for (char c : oldString.toCharArray()) {
            if (!Character.isLetter(c)) {
                continue;
            }

            if (c - 'A' > 0 && c - 'A' <= 'Z' - 'A') {
                c -= 'A' + 'a';
            }
            newString += c;
        }
        return newString;
    }
 }