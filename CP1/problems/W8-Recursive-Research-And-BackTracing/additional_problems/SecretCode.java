import java.io.*;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=396

class SecretCode {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("scode.in")));
            String s = br.readLine().trim();
            br.close();

            FileWriter fw = new FileWriter(new File("scode.out"));
            int answer = codeSplit(s);
            fw.write(String.valueOf(answer));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int codeSplit(String s) {
        if (s.length() <= 2) {
            return 0;
        }

        int biggerFirstIdx = (s.length() + 1) / 2;          // Start of latter part
        int biggerLatterIdx = (s.length() + 1) / 2 - 1;     // Start of latter part

        int firstCount = 0, secondCount = 0;
        int temp = 0;

        // ABABA -> ABA+BA
        if (s.substring(1, biggerFirstIdx).equals(s.substring(biggerFirstIdx))) {
            firstCount = 1 + codeSplit(s.substring(0, biggerFirstIdx));
            secondCount = 1 + codeSplit(s.substring(biggerFirstIdx));
            temp += firstCount * secondCount;
        }

        // ABAAB -> ABA+AB
        if (s.substring(0, biggerFirstIdx - 1).equals(s.substring(biggerFirstIdx))) {
            firstCount = 1 + codeSplit(s.substring(0, biggerFirstIdx));
            secondCount = 1 + codeSplit(s.substring(biggerFirstIdx));
            temp += firstCount * secondCount;
        }

        // ABAAB -> AB +AAB
        if (s.substring(0, biggerLatterIdx).equals(s.substring(biggerLatterIdx + 1))) {
            firstCount = 1 + codeSplit(s.substring(0, biggerLatterIdx));
            secondCount = 1 + codeSplit(s.substring(biggerLatterIdx));
            temp += firstCount * secondCount;
        }

        // // ABAAB -> AA +AAB
        if (s.substring(0, biggerLatterIdx).equals(s.substring(biggerLatterIdx, s.length() - 1))) {
            firstCount = 1 + codeSplit(s.substring(0, biggerLatterIdx));
            secondCount = 1 + codeSplit(s.substring(biggerLatterIdx));
            temp += firstCount * secondCount;
        }
        return temp;
    }
}