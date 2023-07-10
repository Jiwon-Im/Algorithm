package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NM5_15654 {
    static int N, M;
    static int[] input;
    static int[] word;
    static boolean[] v;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        input = new int[N];
        word = new int[M];
        v = new boolean[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);

        perm(0);
        System.out.println(sb);
    }

    private static void perm(int cnt) {
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(word[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!v[i]) {
                word[cnt] = input[i];
                v[i] = true;
                perm(cnt + 1);
                v[i] = false;
            }

        }

    }
}
