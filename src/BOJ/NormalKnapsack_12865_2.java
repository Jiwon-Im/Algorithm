package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NormalKnapsack_12865_2 {

    static int N, K;
    static int[] w;
    static int[] v;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][K + 1];

        w = new int[N + 1];
        v = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                // 이번 물건이 배낭에 들어갈 수 있다면
                if (w[i] <= j)
                    // max [이전값], [현재 물건을 넣었을 때의 최댓값(dp[i-1][j-무게]+이번 물건 가치)]
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }

        System.out.println(dp[N][K]);
    }


}
