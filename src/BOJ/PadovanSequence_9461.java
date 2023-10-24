package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PadovanSequence_9461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        long[] p = new long[101];
        p[1] = 1;
        p[2] = 1;
        p[3] = 1;
        p[4] = 2;
        p[5] = 2;

        for (int i = 6; i <= 100; i++) {
            p[i] = p[i - 1] + p[i - 5];
        }

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            sb.append(p[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }
}
