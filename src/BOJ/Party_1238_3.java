package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Party_1238_3 {
    static int N;
    static ArrayList<ArrayList<Point>> list;
    static int MAX = 10_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 마을 N개
        int M = Integer.parseInt(st.nextToken()); // 단방향 도로 개수
        int X = Integer.parseInt(st.nextToken()); // 파티를 벌이는 마을 번호
        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            list.get(u).add(new Point(v, t));
        }

        int[] D = findXtoAll(X);
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, D[i] + findOneToX(i, X));
        }
        System.out.println(ans);
    }

    private static int findOneToX(int start, int end) {
        boolean[] v = new boolean[N + 1];
        int[] D = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            D[i] = MAX;
        }
        D[start] = 0;

        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(start, 0));

        while (!pq.isEmpty()) {
            Point p = pq.poll();

            // 찾았다면 리턴
            if (p.dest == end) {
                return D[end];
            }
            // 이미 해당 지점에서 갈 수 있는 지점을 pq에 넣었다면 패스
            if (v[p.dest]) {
                continue;
            }

            v[p.dest] = true;
            for (Point trev : list.get(p.dest)) {
                int temp = D[p.dest] + trev.weight;
                if (D[trev.dest] > temp) {
                    D[trev.dest] = temp;
                    pq.offer(new Point(trev.dest, temp));
                }
            }
        }
        return -1;
    }

    private static int[] findXtoAll(int end) {
        boolean[] v = new boolean[N + 1];
        int[] D = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            D[i] = MAX;
        }
        D[end] = 0;

        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(end, 0));

        while (!pq.isEmpty()) {
            Point p = pq.poll();

            // 이미 해당 지점에서 갈 수 있는 지점을 pq에 넣었다면 패스
            if (v[p.dest]) {
                continue;
            }

            v[p.dest] = true;
            for (Point trev : list.get(p.dest)) {
                int temp = D[p.dest] + trev.weight;
                if (D[trev.dest] > temp) {
                    D[trev.dest] = temp;
                    pq.offer(new Point(trev.dest, temp));
                }
            }
        }
        return D;
    }

    private static class Point implements Comparable<Point> {
        int dest;
        int weight;

        public Point(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Point o) {
            return this.weight - o.weight;
        }
    }
}
