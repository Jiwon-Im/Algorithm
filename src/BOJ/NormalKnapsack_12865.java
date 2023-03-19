package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NormalKnapsack_12865 {

    static int N, K;
    static int[] w;
    static int[] v;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        w = new int[N];
        v = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }
        find(0, 0, 0);
        System.out.println(max);
    }

    static public void find(int idx, int now_value, int now_weight) {
        if (idx == N) {
            // 끝
            max = Math.max(max, now_value);
            return;
        }
        // 담아도 무게가 초과하지 않는다면 현재 물건 담기
        if (now_weight + w[idx] <= K) {
            find(idx + 1, now_value + v[idx], now_weight + w[idx]);
        }
        // 현재 물건 담지 않기
        find(idx + 1, now_value, now_weight);
    }


}
