package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Coin_9084 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] coins = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int price = Integer.parseInt(br.readLine());

            // TSP
            int[] dp = new int[price + 1];
            dp[0] = 1;
            for (int coin : coins) {
                for (int i = coin; i <= price; i++) {
                    dp[i] += dp[i - coin];
                }
            }
            sb.append(dp[price]).append("\n");
        }
        System.out.println(sb);
    }

}
