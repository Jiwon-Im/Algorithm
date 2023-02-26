package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BreakingWall_2206 {

    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if(N==1 && M==1){
            System.out.println(1);
            return;
        }

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        System.out.println(bfs());
    }

    private static int bfs() {

        Queue<Point> que = new LinkedList<>();
        que.add(new Point(0, 0, 1, 0));
        boolean[][][] visited = new boolean[N][M][2];

        while (!que.isEmpty()) {

            Point p = que.poll();

            for (int d = 0; d < 4; d++) {
                int dr = p.x + dx[d];
                int dc = p.y + dy[d];
                int broken = p.broken;

                if (dr < 0 || dr >= N || dc < 0 || dc >= M || visited[dr][dc][broken]) {
                    continue;
                }

                // 벽일 때
                if (map[dr][dc] == 1) {
                    if (broken == 1) {
                        // 이미 부쉈다
                        continue;
                    }
                    // 부수고 지나가자
                    broken = 1;
                }

                visited[dr][dc][broken] = true;
                que.offer(new Point(dr, dc, p.cnt + 1, broken));

                if (dr == N - 1 && dc == M - 1) {
                    return p.cnt + 1;
                }
            }

        }
        return -1;
    }

    private static class Point {
        int x, y, cnt, broken;

        public Point(int x, int y, int cnt, int broken) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.broken = broken;
        }
    }

}
