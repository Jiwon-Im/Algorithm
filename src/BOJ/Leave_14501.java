package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Leave_14501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        int[] day = new int[N + 1];
        int[] pay = new int[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            day[i] = Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        // 각 input에 대해
        for (int i = 0; i < N; i++) {
            // 전체 일정 안에 끝낼 수 있는지
            if (i + day[i] <= N) {
                // 끝나는 날 기준 계산
                // dp[i]는 최적화 된 상태
                dp[i + day[i]] = Math.max(dp[i + day[i]], dp[i] + pay[i]);
            }
            dp[i + 1] = Math.max(dp[i + 1], dp[i]); // 누적 이익
        }
        System.out.println(dp[N]);
    }
}
