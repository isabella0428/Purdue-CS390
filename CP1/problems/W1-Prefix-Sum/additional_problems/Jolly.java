// https://open.kattis.com/problems/jollyjumpers

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Vector;

class Jolly{
    public static void main(String ... args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line;
        
        try {
            while((line = bf.readLine()) != null) {
                if (line.equalsIgnoreCase(""))  break;
                Vector<Integer> numbers = new Vector<>();
                line = line.split(" ", 2)[1];
                for (String n : line.split(" ", 3001)) {
                    numbers.add(Integer.parseInt(n));
                }

                int size = numbers.size();
                Boolean jolly = true;
                HashSet<Integer> seen = new HashSet<>();
                for (int i = 0; i < numbers.size() - 1; ++i) {
                    int diff = numbers.get(i) - numbers.get(i + 1);
                    if (diff < 0) diff *= -1;
                    if ((diff < 1) || (diff >= size)){
                        jolly = false;
                        break;
                    }
                    seen.add(diff);
                }

                if (jolly && seen.size() == size - 1) {
                    System.out.println("Jolly");
                } else {
                    System.out.println("Not jolly");
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}