import java.io.*;

// https://open.kattis.com/problems/restaurant

class workAtRestaurant {
    public static void main(String...args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
            String line;
            while (!(line = br.readLine().trim()).equals("0")) {
                int ad1 = 0, ad2 = 0;
                int N = Integer.parseInt(line);
                
                for (int i = 0; i < N; ++i) {
                    line = br.readLine().trim();
                    String parts[] = line.split(" ");
                    String command = parts[0];
                    int num = Integer.parseInt(parts[1]);

                    if (command.equals("DROP")) {
                        ad2 += num;
                        out.println("DROP 2 " + num);
                        out.flush();
                    }

                    if (command.equals("TAKE")) {
                        int times = Math.min(num, ad1);

                        if (times != 0) {
                            out.println("TAKE 1 " + times);
                            out.flush();
                        }
                        
                        int reminder = num - times;
                        if (reminder > 0) {
                            ad1 = 0;
                        } else {
                            ad1 -= num;
                        }

                        if (times < num) {
                            ad1 += ad2;
                            ad2 = 0;
                            out.println("MOVE 2->1 " +  ad1);
                            out.flush();

                            ad1 -= reminder;
                            out.println(String.format("TAKE 1 " + reminder));
                            out.flush();
                        }
                    }
                }
                System.out.println();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}