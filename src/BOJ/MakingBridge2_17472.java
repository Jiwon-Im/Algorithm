package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// https://www.acmicpc.net/problem/17472
public class MakingBridge2_17472 {
    static int N, M, L;
    static int[][] map;
    static int[][] distance;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static PriorityQueue<WPoint> pq;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 섬 번호 붙이기
        numberingIsland();

        // 거리 행렬 초기화
        distanceInit();

        // 최단 거리 구하기
        findDistance();

        // 크루스칼
        int ans = kruskal();

        System.out.println(ans);
    }

    private static int kruskal() {
        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));

        // 거리 순서대로 정렬
        orderingDistance();

        // union find 초기화
        parentInit();

        int left = L - 2; // 섬개수 (L-1)- 1
        int ans = 0;
        while (left > 0 && !pq.isEmpty()) {
            WPoint wp = pq.poll();

            int a = find(wp.x);
            int b = find(wp.y);

            if (a == b) continue;

            // union
            parent[b] = a;

            // 더하기
            ans += wp.dist;
            left--;
        }

        if (left > 0) {
            return -1;
        }
        return ans;
    }

    private static void distanceInit() {
        distance = new int[L + 1][L + 1]; // L : 섬 숫자

        for (int i = 2; i <= L; i++) {
            for (int j = 2; j <= L; j++) {
                distance[i][j] = 100;
            }
        }
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void parentInit() {
        parent = new int[L + 1];
        for (int i = 2; i <= L; i++) {
            parent[i] = i;
        }
    }

    private static void orderingDistance() {
        for (int i = 2; i <= L; i++) {
            for (int j = i + 1; j <= L; j++) {
                if (distance[i][j] < 100) {
                    pq.add(new WPoint(i, j, distance[i][j]));
                }
            }
        }
    }


    private static void findDistance() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                // 4방위 거리 계산 시작
                int start = map[i][j];
                for (int d = 0; d < 4; d++) {
                    // 한 방향에 대해 맵 끝이나 섬 만날때까지
                    int dx = i;
                    int dy = j;
                    int cnt = 0;
                    while (true) {
                        dx += dr[d];
                        dy += dc[d];

                        // 탐색중단
                        if (dx < 0 || dx >= N || dy < 0 || dy >= M || map[dx][dy] == start) {
                            break;
                        }

                        // 섬 만남
                        if (map[dx][dy] > 0) {
                            // a<b or b<a 중 한개만
                            if (map[dx][dy] < start) {
                                break;
                            }
                            int end = map[dx][dy];
                            int min = Math.min(distance[start][end], cnt);
                            // 다리의 길이는 2 이상
                            if (min == 1)
                                break;
                            distance[start][end] = min;
                            distance[end][start] = min;
                            break;
                        }
                        // cnt 증가
                        cnt++;
                    }
                }

            }
        }
    }

    private static void numberingIsland() {
        int islandNum = 1; // 섬 번호는 2번부터

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    bfs(i, j, ++islandNum);
                }
            }
        }
        // 섬 숫자
        L = islandNum;
    }

    private static void bfs(int x, int y, int num) {
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(x, y));
        map[x][y] = num;

        while (!que.isEmpty()) {
            Point p = que.poll();

            for (int d = 0; d < 4; d++) {
                int dx = p.x + dr[d];
                int dy = p.y + dc[d];

                if (dx < 0 || dx >= N || dy < 0 || dy >= M || map[dx][dy] != 1) {
                    continue;
                }

                map[dx][dy] = num;
                que.add(new Point(dx, dy));
            }
        }

    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class WPoint extends Point {
        int dist;

        public WPoint(int x, int y, int dist) {
            super(x, y);
            this.dist = dist;
        }
    }
}
