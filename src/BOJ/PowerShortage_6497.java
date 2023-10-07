package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PowerShortage_6497 {
    static int M, N;
    static PriorityQueue<Point> pq;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (true) {

            M = Integer.parseInt(st.nextToken()); // 집의 수
            N = Integer.parseInt(st.nextToken()); // 길의 수

            // 종료
            if (M == 0 && N == 0) {
                break;
            }

            // 초기 거리 합
            int sum = 0;

            // union-find parent 배열 초기화
            parentInit();

            // 거리 순으로 pq에 넣기
            pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                sum += z;
                pq.add(new Point(x, y, z));
            }

            // 비용계산
            int cnt = calculate();

            // 테스트케이스 답
            sb.append(sum - cnt).append("\n");

            // next 입력
            st = new StringTokenizer(br.readLine());
        }
        System.out.println(sb);
    }


    private static int calculate() {
        int cnt = 0;

        int left = M - 1; // 간선 M-1개 선택

        while (left > 0) {
            Point p = pq.poll();
            int a = find(p.x);
            int b = find(p.y);

            if (a == b) continue;

            // union
            parent[b] = a;

            cnt += p.weight;
            left--;
        }

        return cnt;
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void parentInit() {
        parent = new int[M + 1];
        for (int i = 1; i <= M; i++) {
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
