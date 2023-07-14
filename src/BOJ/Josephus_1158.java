package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Josephus_1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder("<");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N == 1) {
            sb.append("1>");
            System.out.println(sb);
            return;
        }

        Queue<Integer> que = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            que.offer(i);
        }

        while (que.size() > 1) {
            for (int i = 0; i < K - 1; i++) {
                que.offer(que.poll());
            }
            sb.append(que.poll()).append(", ");
        }

        sb.append(que.poll()).append(">");
        System.out.println(sb);
    }
}
