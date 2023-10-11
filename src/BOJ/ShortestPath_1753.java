package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ShortestPath_1753 {
    static int V, E;
    static ArrayList<ArrayList<Node>> list;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 출발지점
        int start = Integer.parseInt(br.readLine());

        // list 초기화
        initList();

        // 입력
        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.get(u).add(new Node(v, w));
        }

        // start에서 출발하는 최단 경로 구하기
        findShortestPath(start);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(distance[i] < Integer.MAX_VALUE ? distance[i] : "INF").append("\n");
        }
        System.out.println(sb);
    }

    private static void findShortestPath(int start) {
        // 방문체크 배열
        boolean[] v = new boolean[V + 1];

        // start -> end 최단 거리 배열
        distance = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        que.offer(new Node(start, 0));
        distance[start] = 0;

        while (!que.isEmpty()) {
            Node node = que.poll();
            // 선택된 노드면 패스
            if (v[node.vertex]) {
                continue;
            }
            v[node.vertex] = true;

            for (Node end : list.get(node.vertex)) {
                // 최단거리 갱신
                int newDistance = distance[node.vertex] + end.weight;
                if (newDistance < distance[end.vertex]) {
                    distance[end.vertex] = newDistance;
                    // 선택된 노드면 패스
                    if (v[end.vertex]) {
                        continue;
                    }
                    // 제일 짧은 지점에서 탐색했을 때 +a가 되어도 짧은 지점
                    que.offer(new Node(end.vertex, distance[end.vertex]));
                }
            }
        }

    }

    private static void initList() {
        list = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }
    }

    private static class Node {
        int vertex;
        int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

    }
}
