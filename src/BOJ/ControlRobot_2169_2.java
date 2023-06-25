package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ControlRobot_2169_2 {
    static int N, M;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫번째 행
        dp[0][0] = map[0][0];
        for (int j = 1; j < M; j++) {
            dp[0][j] = map[0][j] + dp[0][j - 1];
        }

        // L -> R or R -> L 한 방향만 가능
        for (int i = 1; i < N; i++) {
            // L -> R or U -> D
            int[] leftToRight = new int[M];
            leftToRight[0] = map[i][0] + dp[i - 1][0];
            for (int j = 1; j < M; j++) {
                leftToRight[j] = map[i][j] + Math.max(dp[i - 1][j], leftToRight[j - 1]);
            }
            // R -> L or U-> D
            int[] rightToLeft = new int[M];
            rightToLeft[M - 1] = map[i][M - 1] + dp[i - 1][M - 1];
            for (int j = M - 2; j >= 0; j--) {
                rightToLeft[j] = map[i][j] + Math.max(dp[i - 1][j], rightToLeft[j + 1]);
            }
            // L -> R / R -> L
            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(leftToRight[j], rightToLeft[j]);
            }
        }
        System.out.println(dp[N - 1][M - 1]);
    }

}
