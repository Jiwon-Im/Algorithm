package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1647
public class DivisionPlanning_1647 {
    static int N;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // parent 초기화
        initParent();

        // 가중치 순 정렬
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

        // 입력
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // 답
        int cnt = 0;
        // N-1번째 간선은 가중치가 제일 큰 간선이므로 빼기 - 해당 간선 기준 마을 분리
        int left = N - 2;
        while (left > 0) {
            Node node = pq.poll();
            int a = find(node.x);
            int b = find(node.y);

            // 이미 연결
            if (a == b) continue;

            // union
            parent[b] = a;
            left--;
            cnt += node.weight;
        }

        System.out.println(cnt);
    }

    private static int find(int x) {
        // 가장 상위 부모까지 찾았음
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void initParent() {
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    private static class Node {
        int x;
        int y;
        int weight;

        public Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }
}
