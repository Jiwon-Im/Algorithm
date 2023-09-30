package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// https://www.acmicpc.net/problem/1197
public class MST_1197 {
    static int V, E;
    static int[] parent;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight)); // 가중치 오름차순

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parentInit();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // answer
        int cnt = 0;

        // 남은 간선 개수
        int left = V - 1;
        // 간선 V-1개를 선택할 때까지
        while (left > 0) {
            Node n = pq.poll();

            // 같은 그룹인지 확인
            int pA = find(n.nodeA);
            int pB = find(n.nodeB);

            // 같은 그룹일 경우 continue
            if (pA == pB) {
                continue;
            }

            // 같은 그룹이 아닐 경우 union
            parent[pB] = pA;

            // 남은 간선 개수 --
            left--;

            // 가중치 더하기
            cnt += n.weight;
        }

        System.out.println(cnt);
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    private static void parentInit() {
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }
    }

    private static class Node {
        int nodeA;
        int nodeB;
        int weight;

        public Node(int a, int b, int w) {
            this.nodeA = a;
            this.nodeB = b;
            this.weight = w;
        }
    }
}
