package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StartAndLink_14889_2 {
    static int N;
    static int[][] map;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[] v = new boolean[N];
        combi(0, v, 0);
        System.out.println(min);

    }

    private static void combi(int idx, boolean[] v, int cnt) {
        if (cnt == N / 2) {
            int groupA = cal(v, true);
            int groupB = cal(v, false);
            min = Math.min(Math.abs(groupA - groupB), min);
            return;
        }
        for (int i = idx; i < N; i++) {
            v[i] = true;
            combi(i + 1, v, cnt + 1);
            v[i] = false;
        }
    }

    private static int cal(boolean[] v, boolean flag) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (v[i] == flag) {
                for (int j = i + 1; j < N; j++) {
                    if (v[j] == flag) {
                        sum += map[i][j] + map[j][i];
                    }
                }
            }
        }
        return sum;
    }
}
