package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NM4_15652 {
    static int N, M;
    static int[] word;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // loc 이동하면서 위치에 값 수정
        word = new int[M];

        for (int i = 1; i <= N; i++) {
            find(i, 0);
        }
        System.out.println(sb.toString());
    }

    private static void find(int num, int loc) {

        // loc 0 부터
        word[loc] = num;
        if (loc == M - 1) {
            for (int i = 0; i < M; i++) {
                sb.append(word[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        // 비 내림차순, 다음 자리 이번 위치와 같은 값부터
        for (int i = num; i <= N; i++) {
            find(i, loc + 1);
        }

    }
}
