package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Virus_2606 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Integer>[] adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b);
            adjList[b].add(a);
        }

        boolean[] visited = new boolean[N + 1];
        visited[1] = true;
        int cnt = 0;
        Queue<Integer> que = new LinkedList<>();
        que.add(1);

        while (!que.isEmpty()) {
            int p = que.poll();
            for (int cur : adjList[p]) {
                if (!visited[cur]) {
                    que.add(cur);
                    visited[cur] = true;
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
