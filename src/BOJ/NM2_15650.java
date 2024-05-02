package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NM2_15650 {
    static StringBuilder sb;
    static int[] num;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new int[M];

        sb = new StringBuilder();

        combi(1, 0);

        System.out.println(sb);
    }

    private static void combi(int value, int cnt) {
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(num[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = value; i <= N; i++) {
            num[cnt] = i;
            combi(i + 1, cnt + 1);
        }
    }
}
