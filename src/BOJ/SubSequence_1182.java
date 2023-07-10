package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SubSequence_1182 {
    static int N, S;
    static int[] input;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        input = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        subSet(0, 0, false);

        System.out.println(cnt);
    }

    private static void subSet(int idx, int sum, boolean check) {

        if (idx == N) {
            // check로 공집합 거르기
            if (check && sum == S) {
                cnt++;
            }
            return;
        }

        subSet(idx + 1, sum, check);
        subSet(idx + 1, sum + input[idx], true);
    }
}
