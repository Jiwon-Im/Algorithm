package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SortingCard_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> que = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            que.add(Integer.parseInt(br.readLine()));
        }

        int ans = 0;
        while (que.size() > 1) {
            int cnt = que.poll() + que.poll();
            que.add(cnt);
            ans += cnt;
        }
        System.out.println(ans);
    }
}
