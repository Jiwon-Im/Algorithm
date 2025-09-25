package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RoomNumber_1475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int len = input.length();

        int[] ary = new int[10];

        for (int i = 0; i < len; i++) {
            int target = input.charAt(i) - '0';
            ary[target]++;
        }
        int max = (int) Math.ceil((ary[6] + ary[9]) / (double)2);
        for (int i = 0; i < 9; i++) {
            if (i == 6) continue;
            if (ary[i] > max) {
                max = ary[i];
            }
        }
        System.out.println(max);

    }
}
