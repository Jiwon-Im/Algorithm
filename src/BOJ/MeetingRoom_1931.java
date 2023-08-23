package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MeetingRoom_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<Time> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.end == o2.end) {
                return o1.start - o2.start;
            }
            return o1.end - o2.end;
        });

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int cnt = 0;
        int now = 0;
        while (!pq.isEmpty()) {
            Time t = pq.poll();
            if (t.start >= now) {
                cnt += 1;
                now = t.end;
            }
        }
        System.out.println(cnt);
    }

    private static class Time {
        int start;
        int end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
