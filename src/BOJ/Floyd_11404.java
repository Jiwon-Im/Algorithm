package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Floyd_11404 {
    static int N;
    static int[][] D;
    static int MAX = 10_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        D = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                D[i][j] = MAX;
            }
            D[i][i] = 0;
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // 같은 루트에 길이 여러개일 수 있다.
            D[a][b] = Math.min(D[a][b], c);
        }

        // 플로이드-워셜 거리 찾기
        floyd();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (D[i][j] == MAX) {
                    sb.append(0).append(" ");
                } else {
                    sb.append(D[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void floyd() {
        // K경로를 지나갈 때
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
                }
            }
        }
    }

}
