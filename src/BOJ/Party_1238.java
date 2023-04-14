package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 참고 : https://steady-coding.tistory.com/106
public class Party_1238 {

    static int N, M, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // 연결리스트 생성
        List<Point>[] adjList = new ArrayList[N + 1];
        List<Point>[] reversedAdjList = new ArrayList[N + 1];

        int[] time;
        int[] reversedTime;

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
            reversedAdjList[i] = new ArrayList<>();
        }

        // 연결리스트 데이터 넣기
        for (int m = 1; m <= M; m++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            adjList[start].add(new Point(end, t));
            // X로 가는 길 == X로 오는 길
            // 다익스트라 쓰기위해서 그래프 방향 반대로 생성
            reversedAdjList[end].add(new Point(start, t));
        }

        // X -> 모든 지점으로 가는 비용 구하기
        time = findXToAllPoint(adjList);
        // 모든 지점에서 X로 가는 비용 구하기
        reversedTime = findXToAllPoint(reversedAdjList);

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, time[i] + reversedTime[i]);
        }
        System.out.println(ans);
    }

    private static int[] findXToAllPoint(List<Point>[] adjList) {

        boolean[] visited = new boolean[N + 1];
        int[] time = new int[N + 1];
        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));

        pq.offer(new Point(X, 0));
        int cnt = N;

        while (true) {
            Point p = pq.poll();

            // ** 이미 최소 거리를 구한 지점이면 패스
            while (visited[p.loc]) {
                p = pq.poll();
            }

            visited[p.loc] = true;
            time[p.loc] = p.time;
            cnt--;

            // 전부 방문 끝
            if (cnt == 0) {
                return time;
            }

            // 현재 마을과 연결된 마을 중 방문하지 않은 지점들 큐에 넣기
            for (Point cur : adjList[p.loc]) {
                if (visited[cur.loc]) {
                    continue;
                }
                cur.time += p.time;
                pq.offer(cur);
            }

        }
    }

    static private class Point {
        int loc;
        int time;

        public Point(int loc, int time) {
            this.loc = loc;
            this.time = time;
        }

    }
}
