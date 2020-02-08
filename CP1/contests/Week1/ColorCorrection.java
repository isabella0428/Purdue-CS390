import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.hackerrank.com/contests/cp-1-fall-2019-week-1-input-output-practice/challenges/color-correction

class ColorCorrection {
    public static void main(String[] args) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            int num = Integer.parseInt(bf.readLine().trim());

            for(int i = 0; i < num; ++i) {
                String line = bf.readLine().trim();
                int darkest = -1;

                int red = Integer.parseInt(line.substring(0, 2), 16);   // 0
                if (red > darkest) {
                    darkest = red;
                }

                int green = Integer.parseInt(line.substring(2, 4), 16); // 1
                if (green > darkest) {
                    darkest = green;
                }

                int blue = Integer.parseInt(line.substring(4, 6), 16);  // 2
                if(blue > darkest) {
                    darkest = blue;
                }

                if (darkest == red) {
                    System.out.println("Red");
                } else {
                    if (darkest == green) {
                        System.out.println("Green");
                    } else {
                        System.out.println("Blue");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}