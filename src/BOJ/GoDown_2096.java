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
        int[][] dp = new int[2][3];

        st = new StringTokenizer(br.readLine());
        dp[0][0] = dp[1][0] = Integer.parseInt(st.nextToken());
        dp[0][1] = dp[1][1] = Integer.parseInt(st.nextToken());
        dp[0][2] = dp[1][2] = Integer.parseInt(st.nextToken());

        int[] map = new int[3];

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[0] = Integer.parseInt(st.nextToken());
            map[1] = Integer.parseInt(st.nextToken());
            map[2] = Integer.parseInt(st.nextToken());

            // 최댓값
            int temp2 = dp[0][2];
            int temp0 = dp[0][0];
            dp[0][0] = Math.max(dp[0][0], dp[0][1]) + map[0];
            dp[0][2] = Math.max(dp[0][1], dp[0][2]) + map[2];
            dp[0][1] = Math.max(Math.max(temp0, dp[0][1]), temp2) + map[1];

            temp2 = dp[1][2];
            temp0 = dp[1][0];
            // 최솟값
            dp[1][0] = Math.min(dp[1][0], dp[1][1]) + map[0];
            dp[1][2] = Math.min(dp[1][1], dp[1][2]) + map[2];
            dp[1][1] = Math.min(Math.min(temp0, dp[1][1]), temp2) + map[1];
        }

        System.out.println(Math.max(Math.max(dp[0][0], dp[0][1]), dp[0][2]) + " " + Math.min(Math.min(dp[1][0], dp[1][1]), dp[1][2]));
    }
}
