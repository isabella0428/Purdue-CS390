import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.hackerrank.com/contests/cp-1-fall-2019-week-1-input-output-practice/challenges/really-simple-input

class ReallySimpleInput {

    public static void main(String[] args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String line = bf.readLine();
            long n1 = Integer.parseInt(line.split(" ", 2)[0].trim());
            long n2 = Integer.parseInt(line.split(" ", 2)[1].trim());
            System.out.println(n1 + n2);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}