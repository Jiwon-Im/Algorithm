package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GoDown_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] dp = new int[2][3];

        dp[0][0] = dp[1][0] = map[0][0];
        dp[0][1] = dp[1][1] = map[0][1];
        dp[0][2] = dp[1][2] = map[0][2];

        for (int i = 1; i < N; i++) {
            // 최댓값
            int temp2 = dp[0][2];
            int temp0 = dp[0][0];
            dp[0][0] = Math.max(dp[0][0], dp[0][1]) + map[i][0];
            dp[0][2] = Math.max(dp[0][1], dp[0][2]) + map[i][2];
            dp[0][1] = Math.max(Math.max(temp0, dp[0][1]), temp2) + map[i][1];

            temp2 = dp[1][2];
            temp0 = dp[1][0];
            // 최솟값
            dp[1][0] = Math.min(dp[1][0], dp[1][1]) + map[i][0];
            dp[1][2] = Math.min(dp[1][1], dp[1][2]) + map[i][2];
            dp[1][1] = Math.min(Math.min(temp0, dp[1][1]), temp2) + map[i][1];
        }

        System.out.println(Math.max(Math.max(dp[0][0], dp[0][1]), dp[0][2]) + " " + Math.min(Math.min(dp[1][0], dp[1][1]), dp[1][2]));
    }
}
