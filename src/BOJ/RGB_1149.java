package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB_1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] rgb = new int[3];
        int[][] dp = new int[N][3];

        StringTokenizer st = new StringTokenizer(br.readLine());

        // dp[0] 초기화
        for (int i = 0; i < 3; i++) {
            dp[0][i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                rgb[j] = Integer.parseInt(st.nextToken());
            }

            dp[i][0] = rgb[0] + Math.min(dp[i - 1][1], dp[i - 1][2]); // r
            dp[i][1] = rgb[1] + Math.min(dp[i - 1][0], dp[i - 1][2]); // g
            dp[i][2] = rgb[2] + Math.min(dp[i - 1][0], dp[i - 1][1]); // b
        }

        System.out.println(Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2])));
    }
}
