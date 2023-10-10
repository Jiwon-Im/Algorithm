package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CityConstruction_21924 {
    static int N;
    static int[] parent;
    static PriorityQueue<Point> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long total = 0L;
        pq = new PriorityQueue<>(Comparator.comparing(o -> o.weight));
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            total += weight;
            pq.add(new Point(x, y, weight));
        }

        // parent 배열 초기화
        parentInit();

        // 선택할 간선 수
        int left = N - 1;
        // 답
        long selectedWeight = 0;
        while (!pq.isEmpty()) {
            Point p = pq.poll();

            int a = find(p.x);
            int b = find(p.y);

            // 이미 연결되어 있음
            if (a == b) continue;

            // 작은 부모로
            parent[b] = a;

            // 경로 길이 더하기
            selectedWeight += p.weight;
            if (--left == 0) {
                break;
            }
        }
        if (left > 0) {
            System.out.println(-1);
            return;
        }
        System.out.println(total - selectedWeight);
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void parentInit() {
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    private static class Point {
        int x;
        int y;
        int weight;

        public Point(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }
}
