package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Party_1238_2 {
    static int[][] d;
    static int N;
    static int MAX = 10_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 마을 N개
        int M = Integer.parseInt(st.nextToken()); // 단방향 도로 개수
        int X = Integer.parseInt(st.nextToken()); // 파티를 벌이는 마을 번호

        // 거리 행렬
        d = new int[N + 1][N + 1];
        initD();

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            d[u][v] = t;
        }

        findDistance();

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, d[i][X] + d[X][i]);
        }
        System.out.println(ans);
    }

    private static void findDistance() {

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (d[i][k] == MAX) continue;
                for (int j = 1; j <= N; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }
    }

    private static void initD() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                d[i][j] = MAX;
            }
            d[i][i] = 0;
        }
    }
}
