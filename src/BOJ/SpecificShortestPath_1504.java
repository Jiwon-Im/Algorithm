package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1504
public class SpecificShortestPath_1504 {
    static int V;
    static ArrayList<ArrayList<Node>> adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjList.get(a).add(new Node(b, c));
            adjList.get(b).add(new Node(a, c));
        }

        // 반드시 거쳐야 하는 두 개의 정점
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        // 1->a->b->V or 1->b->a->V
        int ans = Math.min(findAnswer(a, b), findAnswer(b, a));

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static int findAnswer(int a, int b) {

        int ans = findShortestPath(1, a);
        if (ans == Integer.MAX_VALUE) return Integer.MAX_VALUE;

        int temp1 = findShortestPath(a, b);
        if (temp1 == Integer.MAX_VALUE) return Integer.MAX_VALUE;

        int temp2 = findShortestPath(b, V);
        if (temp2 == Integer.MAX_VALUE) return Integer.MAX_VALUE;

        return ans + temp1 + temp2;
    }

    private static int findShortestPath(int start, int end) {

        // 초기화
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] v = new boolean[V + 1];
        int[] distance = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        // 출발지
        pq.add(new Node(start, 0));
        distance[start] = 0;

        // end 지점 도착할때까지 반복
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            // end 지점 찾음
            if (node.vertex == end) {
                break;
            }

            // 해당 지점에서 거리 업데이트는 이미 했음
            if (v[node.vertex]) {
                continue;
            }

            // 방문체크
            v[node.vertex] = true;

            // 거리 업데이트
            for (Node trev : adjList.get(node.vertex)) {
                int temp = distance[node.vertex] + trev.d;
                // 새로운 거리가 더 작다면 업데이트
                if (distance[trev.vertex] > temp) {
                    distance[trev.vertex] = temp;
                    pq.add(new Node(trev.vertex, distance[trev.vertex]));
                }
            }
        }
        return distance[end];
    }

    private static class Node implements Comparable<Node> {
        int vertex;
        int d;

        public Node(int vertex, int d) {
            this.vertex = vertex;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return this.d - o.d;
        }
    }
}
