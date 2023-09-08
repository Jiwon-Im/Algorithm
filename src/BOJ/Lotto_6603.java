package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Lotto_6603 {
    // https://www.acmicpc.net/problem/6603
    static StringBuilder sb;
    static int[] num;
    static int k;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        do {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());

            num = new int[k];   // 입력받을 배열
            result = new int[k];
            for (int i = 0; i < k; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }
            combi(0, 0);
            sb.append("\n");
        } while (k > 0);
        System.out.println(sb);
    }

    private static void combi(int idx, int cnt) {

        if (cnt == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = idx; i < k; i++) {
            result[cnt] = num[i];
            combi(i + 1, cnt + 1);
        }
    }
}
