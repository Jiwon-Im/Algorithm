package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HardToLove_14621 {
    static int N;
    static char[] list;
    static int[] parent;
    static PriorityQueue<University> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        list = new char[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = st.nextToken().charAt(0); // String char 배열에 넣기
        }

        // 가중치 순 오름차순
        pq = new PriorityQueue<>(Comparator.comparing(o -> o.weight));

        // 입력받으면서 다른 성별 학교면 pq에 넣기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (list[x] != list[y]) {
                pq.add(new University(x, y, weight));
            }
        }

        // parent 배열 초기화
        init();

        // 선택할 간선 수
        int left = N - 1;
        // 답
        int ans = 0;
        while (left > 0 && !pq.isEmpty()) {
            University uni = pq.poll();
            int a = find(uni.x);
            int b = find(uni.y);
            // 이미 연결되어 있음
            if (a == b) continue;

            // 작은 부모로
            if (a < b)
                parent[b] = a;
            else
                parent[a] = b;
            // 경로 길이 더하기
            ans += uni.weight;
            left--;
        }
        if (left > 0) {
            System.out.println(-1);
            return;
        }
        System.out.println(ans);
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void init() {
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    private static class University {
        int x;
        int y;
        int weight;

        public University(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }
}
