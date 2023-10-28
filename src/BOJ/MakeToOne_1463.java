package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MakeToOne_1463 {
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        System.out.println(find(N));
    }

    private static int find(int N) {

        if (dp[N] > 0) {
            return dp[N];
        } else if (N == 1) {
            return 0;
        }

        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;

        if (N % 3 == 0) {
            a = find(N / 3);
        }
        if (N % 2 == 0) {
            b = find(N / 2);
        }
        int c = find(N - 1);

        a = Math.min(a, b);
        a = Math.min(a, c);

        return dp[N] = a + 1;
    }
}
