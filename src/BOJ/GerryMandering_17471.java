package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GerryMandering_17471 {

    static int N;
    static int min = Integer.MAX_VALUE;
    static boolean[] v;
    static int[] population;
    static List<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        v = new boolean[N + 1];
        adjList = new ArrayList[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
            population[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                int value = Integer.parseInt(st.nextToken());
                adjList[i].add(value);
                adjList[value].add(i);
            }
        }
        subset(1, 0);

        if (min == Integer.MAX_VALUE) {
            min = -1;
        }
        System.out.println(min);
    }

    static private void subset(int s, int cnt) {

        if (cnt > 0 && cnt <= N / 2) {
            // 두 구역 모두 인접한 조건을 만족한다면
            if (check(cnt)) {
                int sum1 = 0, sum2 = 0;
                for (int i = 1; i <= N; i++) {
                    if (v[i]) {
                        sum1 += population[i];
                    } else {
                        sum2 += population[i];
                    }
                }
                min = Math.min(Math.abs(sum1 - sum2), min);
            }
        }
        if (s == N + 1) {
            return;
        }
        for (int i = s; i <= N; i++) {
            v[i] = true;
            subset(i + 1, cnt + 1);
            v[i] = false;
            subset(i + 1, cnt);
        }
    }

    // 인접한지 확인하는 함수
    static private boolean check(int cnt) {
        // true group, false group check
        if (!isGroup(true, cnt) | !isGroup(false, N - cnt)) {
            return false;
        }
        return true;
    }

    private static boolean isGroup(boolean b, int cnt) {
        // v 배열이 b로 체크되어있는 그룹이 인접한지 확인하는 함수
        boolean[] visited = v.clone();

        Queue<Integer> que = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (visited[i] == b) {
                // 시작지점
                que.offer(i);
                // 방문처리
                visited[i] = !b;
                cnt--;
                break;
            }
        }

        while (!que.isEmpty()) {
            // 전부 방문
            if (cnt == 0) {
                return true;
            }
            int cur = que.poll();

            for (int p : adjList[cur]) {
                if (visited[p] == b) {
                    cnt--;
                    visited[p] = !b;
                    que.offer(p);
                }
            }

        }

        return false;
    }


}
