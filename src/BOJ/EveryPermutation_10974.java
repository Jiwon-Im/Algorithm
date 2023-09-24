package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EveryPermutation_10974 {
    static StringBuilder sb;
    static int N;
    static boolean[] v;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        v = new boolean[N + 1];
        num = new int[N + 1];

        perm(1);
        System.out.println(sb);
    }

    private static void perm(int idx) {

        // N개 순서 정해졌을 때 출력
        if (idx > N) {
            for (int i = 1; i <= N; i++) {
                sb.append(num[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            // 이미 뽑힌 숫자면 pass
            if (v[i]) {
                continue;
            }
            // 백트래킹
            v[i] = true;
            num[idx] = i;
            perm(idx + 1);
            v[i] = false;
        }

    }
}
