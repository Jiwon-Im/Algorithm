package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NM1_15649 {
    static StringBuilder sb;
    static int N, M;
    static int[] num;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new int[M];
        v = new boolean[N+1];

        sb = new StringBuilder();

        find(0);

        System.out.println(sb);
    }

    private static void find( int idx) {
        if (idx == M) {
            for (int i = 0; i < M; i++) {
                sb.append(num[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (v[i]) continue;
            num[idx] = i;
            v[i] = true;
            find(idx + 1);
            v[i] = false;
        }
    }
}
