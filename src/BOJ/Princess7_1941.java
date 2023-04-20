package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Princess7_1941 {

    static char[][] map;
    static boolean[][] visited;
    static int cnt;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        cnt = 0;
        map = new char[5][5];
        visited = new boolean[5][5];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 7개 선택
        dfs(0, 0, 0);

        System.out.println(cnt);
    }

    static private void dfs(int i, int sNum, int yNum) {

        // 배열 벗어났을 때 or 임도연파가 더 많을 때 리턴
        if (i == 25 || yNum == 4) {
            return;
        } else if ((sNum + yNum) == 7) {
            if (isConnected()) {
                cnt++;
            }
            return;
        }

        int r = i / 5;
        int c = i % 5;

        visited[r][c] = true;
        if (map[r][c] == 'S') {
            dfs(i + 1, sNum + 1, yNum);
        } else {
            dfs(i + 1, sNum, yNum + 1);
        }
        visited[r][c] = false;
        dfs(i + 1, sNum, yNum);

    }

    private static boolean isConnected() {

        boolean[][] v = new boolean[5][5];

        Queue<Point> que = new LinkedList<>();
        int left = 7;

        loop:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (visited[i][j]) {
                    // 방문처리
                    v[i][j] = true;
                    que.add(new Point(i, j));
                    left--;
                    break loop;
                }
            }
        }

        while (!que.isEmpty()) {
            Point p = que.poll();

            for (int d = 0; d < 4; d++) {
                int dr = p.x + dx[d];
                int dc = p.y + dy[d];

                if (dr < 0 || dr >= 5 || dc < 0 || dc >= 5 || v[dr][dc]) {
                    continue;
                }

                // 칠공주 발견
                if (!visited[dr][dc]) {
                    continue;
                }

                v[dr][dc] = true;
                que.offer(new Point(dr, dc));
                left--;
            }

        }

        return left == 0;
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
