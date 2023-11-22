package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RectangleAndQuery_14846 {
    public static void main(String[] args) throws IOException {

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[][][] map = new int[N + 1][N + 1][11];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                // 누적합
                for (int k = 1; k < 11; k++) {
                    map[i][j][k] = map[i - 1][j][k] + map[i][j - 1][k] - map[i - 1][j - 1][k];
                }
                map[i][j][Integer.parseInt(st.nextToken())]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int ans = 0;
            for (int k = 1; k < 11; k++) {
                if ((map[x2][y2][k] - map[x1-1][y2][k] - map[x2][y1-1][k] + map[x1-1][y1-1][k]) != 0) {
                    ans++;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
