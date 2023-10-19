package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class NormalKnapsack_12865_3 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<Knapsack> list = new ArrayList<>();

        int K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Knapsack(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int[] dp = new int[K + 1];

        for (Knapsack knapsack : list) {
            // i를 앞에서부터 하면 갱신된 값을 사용해서 무게가 계속 증가
            // 뒤에서부터 갱신하기
            for (int i = K; i >= knapsack.weight; i--) {
                dp[i] = Math.max(dp[i - knapsack.weight] + knapsack.value, dp[i]);
            }
        }
        System.out.println(dp[K]);
    }

    private static class Knapsack {
        int weight;
        int value;

        public Knapsack(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
