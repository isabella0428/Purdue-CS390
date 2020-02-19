import java.io.*;

// https://www.hackerrank.com/contests/cp1-fall-2019-contest-2/challenges/translating-newspeak/
// TODO: 3/4    No idea why the second failed

class Solution {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(System.out);
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();

                int prev = 0,i = 0;
                while(i < line.length()) {
                    char temp = line.charAt(i);
                    if (Character.isUpperCase(temp)){
                        ++i;
                        continue;
                    }
                    if (Character.isLowerCase(temp)){
                        ++i; 
                        continue;
                    }

                    String sToProcess = line.substring(prev, i);
                    pw.print(processString(sToProcess));
                    pw.flush();
                    pw.print(line.charAt(i));
                    pw.flush();
                    ++i;
                    prev = -1;

                    if (i >= line.length()) break;
                    
                    while(!(Character.isLowerCase(line.charAt(i)) || Character.isUpperCase(line.charAt(i)))) {
                        pw.print(line.charAt(i));
                        ++i;
                        if (i >= line.length()) break;
                    }
                    prev = i;
                }
                if (prev != -1) {
                    pw.print(processString(line.substring(prev, line.length())));
                }
                pw.print('\n');
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String processString(String s) {
        if (s.length() <= 10) {
            return s;
        }

        int status = getWordStatus(s);

        int idx = -1;
        String sToFind = "";
        if (status == 0) {
            sToFind = "DOUBLEPLUS";
        } else {
            if (status == 1) {
                sToFind = "doubleplus";
            } else {
                sToFind = "Doubleplus";
            }
        }

        idx = s.lastIndexOf(sToFind);
        if (idx != 0) {
            return s;
        } else {
            String totally = "";
            if (status == 0) {
                totally = "TOTALLY";
            } else {
                if (status == 1) {
                    totally = "totally";
                } else {
                    totally = "Totally";
                }
            }
            return totally + " " + s.substring(idx + 10);
        }
    }

    private static int getWordStatus(String s) {
        if (s.length()< 2) {
            return -1;
        }

        int status = 0; // 0:all capital 1: all small 2: First capital, remainging small
        char first = s.charAt(0);
        char second = s.charAt(1);

        Boolean firstCapital = false, secondCapital = false;
        if (Character.isUpperCase(first)) {
            firstCapital = true;
        }

        if (Character.isUpperCase(second)) {
            secondCapital = true;
        }

        if (firstCapital && secondCapital) {
            status = 0;
        }

        if (!firstCapital && !secondCapital) {
            status = 1;
        }

        if (firstCapital && !secondCapital) {
            status = 2;
        }

        return status;
    }
}