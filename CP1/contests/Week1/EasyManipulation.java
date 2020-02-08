import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.hackerrank.com/contests/cp-1-fall-2019-week-1-input-output-practice/challenges/easy-multiplication

class EasyManipulation {
    public static void main(String... args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String line;

            long product = 1;
            while ((line = bf.readLine()) != null) {
                int num = Integer.parseInt(line.trim());
                if (num == 0) {
                    System.out.println(product);
                } else {
                    long temp = product * num;
                    while (temp < 0) {
                        temp += 1000000007;
                    }
                    product = (temp) % (1000000007);
                }
            }
            System.out.println(product);
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}