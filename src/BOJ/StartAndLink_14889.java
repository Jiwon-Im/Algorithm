package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StartAndLink_14889 {

    static int N;
    static int[][] map;
    static boolean[] v;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        v = new boolean[N];
        min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combi(0, 0);
        System.out.println(min);
    }

    private static void combi(int s, int cnt) {

        if (cnt == N / 2) {

            int sum1 = 0, sum2 = 0;
            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (v[i] && v[j]) {
                        sum1 += (map[i][j] + map[j][i]);
                    } else if (!v[i] && !v[j]) {
                        sum2 += (map[i][j] + map[j][i]);
                    }
                }
            }

            min = Math.min(min, Math.abs(sum1 - sum2));
            return;
        }

        for (int i = s; i < N; i++) {
            v[i] = true;
            combi(i + 1, cnt + 1);
            v[i] = false;
        }
    }
}
