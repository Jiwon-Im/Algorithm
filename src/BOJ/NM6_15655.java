package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NM6_15655 {
    static int N, M;
    static int[] input;
    static int[] word;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        input = new int[N];
        word = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);

        combi(0, 0);
        System.out.println(sb);
    }

    private static void combi(int idx, int cnt) {
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(word[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        if (idx == N) {
            return;
        }

        word[cnt] = input[idx];
        combi(idx + 1, cnt + 1);
        combi(idx + 1, cnt);
    }
}
