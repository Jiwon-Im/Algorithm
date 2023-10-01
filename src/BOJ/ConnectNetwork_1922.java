package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1922
public class ConnectNetwork_1922 {
    static int N;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // parent 초기화
        initParent();

        // 가중치 순 정렬
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

        // M만큼 입력
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // 답
        int cnt = 0;
        // 선택해야 할 간선 개수
        int left = N - 1;
        // 간선을 모두 선택할 때까지
        while (left > 0) {
            Node node = pq.poll();
            int a = find(node.x);
            int b = find(node.y);

            // 이미 연결됨
            if (a == b) {
                continue;
            }

            // 연결x -> 연결
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
