package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Watch_15683 {
    static int N, M;
    static int[][][] directions = {{},  // 0 - 남는 idx (입력값에 등대 0번은 없음)
            {{0}, {1}, {2}, {3}},       // 1 - 상하좌우 (4가지 경우의 수)
            {{2, 3}, {1, 0}},           // 2 - 좌우/상하 (2가지 경우의 수)
            {{1, 3}, {3, 0}, {0, 2}, {2, 1}}, // 3 - 상우/우하/하좌/좌상 (4가지 경우의 수)
            {{0, 1, 2}, {0, 1, 3}, {0, 2, 3}, {1, 2, 3}}, // 4 - 상하좌우 제외 (4가지 경우의 수)
            {{0, 1, 2, 3}}};    // 5 - 한가지 경우 (1가지 경우의 수)
    static ArrayList<Point> cctv;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M]; // dfs하면서 마지막 depth까지 가져갈 전역 map
        cctv = new ArrayList<>(); // cctv 위치 저장

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value > 0 && value < 6) {
                    // CCTV 위치 배열에 추가
                    cctv.add(new Point(i, j));
                }
                map[i][j] = value;
            }
        }
        // 순열 백트래킹
        dfs(0, map);
        System.out.println(min);
    }

    private static void dfs(int idx, int[][] lastMap) {
        // 남은 cctv가 없다. dfs 탈출
        if (idx == cctv.size()) {
            count();
            return;
        }

        Point p = cctv.get(idx);
        for (int[] directCase : directions[lastMap[p.x][p.y]]) { // map의 값이 2이면 2번 등대 경우의 수 2 - 좌우/상하 (2가지 경우의 수) 만큼 반복문 돌면서 watching
            copy(lastMap); // 맵 복원 - 백트래킹?
            watching(p, directCase); // 이번 방향 cctv 감시
            dfs(idx + 1, map); // 다음 cctv로 gogo
        }
    }

    private static void copy(int[][] lastMap) {
        // lastMap에 저장해둔 맵으로 복원
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = lastMap[i][j];
            }
        }
    }

    private static void count() {
        // dfs의 끝에서 0인 자리를 세는 함수 (감시당하지 않은 곳)
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        min = Math.min(min, cnt);
    }

    private static void watching(Point p, int[] direct) {
        // 한 방향을 감시하면서 map에 표시하는 함수
        for (int d : direct) {
            int x = p.x;
            int y = p.y;

            while (true) {
                x += dx[d];
                y += dy[d];
                // 맵 벗어나거나 벽을 만나면 stop
                if (x < 0 || x >= N || y < 0 || y >= M || map[x][y] == 6) {
                    break;
                } else if (map[x][y] == 0) {
                    map[x][y] = -1;
                }
            }
        }
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
