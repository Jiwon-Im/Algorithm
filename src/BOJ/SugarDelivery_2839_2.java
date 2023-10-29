package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SugarDelivery_2839_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[Math.max(11, N + 1)];

        // 3kg, 5kg
        dp[3] = 1;
        dp[4] = -1;
        dp[5] = 1;
        dp[6] = 2;
        dp[7] = -1;
        dp[8] = 2;
        dp[9] = 3;

        for (int i = 10; i <= N; i++) {
            dp[i] = 5000;

            if (dp[i - 3] > 0) dp[i] = Math.min(dp[i - 3] + 1, dp[i]);
            if (dp[i - 5] > 0) dp[i] = Math.min(dp[i - 5] + 1, dp[i]);

            if (dp[i] == 5000) dp[i] = -1;
        }

        System.out.println(dp[N]);
    }
}
