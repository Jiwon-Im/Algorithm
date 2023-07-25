package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFSBFS_1260 {
    static int N, M, V;
    static ArrayList<Integer>[] adjList;
    static StringBuilder sb;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList[u].add(v);
            adjList[v].add(u);
        }

        // 정렬
        for (int i = 1; i <= N; i++) {
            adjList[i].sort(Comparator.comparingInt(o -> o));
        }

        dfs(V);
        sb.append("\n");
        bfs();

        System.out.println(sb);
    }

    private static void dfs(int n) {
        // V부터 출발
        sb.append(n).append(" ");
        visited[n] = true;

        for (int v : adjList[n]) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    private static void bfs() {
        visited = new boolean[N + 1];

        Queue<Integer> que = new LinkedList<>();
        que.offer(V);
        visited[V] = true;

        while (!que.isEmpty()) {
            int n = que.poll();
            sb.append(n).append(" ");

            for (int v : adjList[n]) {
                if (!visited[v]) {
                    visited[v] = true;
                    que.offer(v);
                }
            }

        }

    }

}
