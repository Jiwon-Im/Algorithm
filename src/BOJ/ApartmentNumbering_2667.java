package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class ApartmentNumbering_2667 {
    static int N;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int houseNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int apartmentNum = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    apartmentNum++;
                    houseNum = 0;
                    dfs(i, j);
                    list.add(houseNum);
                }
            }
        }
        Collections.sort(list);

        sb.append(apartmentNum).append("\n");
        for (int v : list) {
            sb.append(v).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int x, int y) {
        map[x][y] = 0;
        houseNum++;

        for (int d = 0; d < 4; d++) {
            int dr = x + dx[d];
            int dc = y + dy[d];

            if (dr < 0 || dr >= N || dc < 0 || dc >= N) {
                continue;
            }

            if (map[dr][dc] > 0) {
                dfs(dr, dc);
            }
        }
    }
}
