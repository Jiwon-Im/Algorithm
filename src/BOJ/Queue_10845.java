package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Queue_10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        Deque<Integer> que = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());

        for (int i = N; i > 0; i--) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "front":
                    if (que.isEmpty()) {
                        sb.append("-1").append("\n");
                    } else {
                        sb.append(que.getFirst()).append("\n");
                    }
                    break;
                case "back":
                    if (que.isEmpty()) {
                        sb.append("-1").append("\n");
                    } else {
                        sb.append(que.getLast()).append("\n");
                    }
                    break;
                case "pop":
                    if (que.isEmpty()) {
                        sb.append("-1").append("\n");
                    } else {
                        sb.append(que.poll()).append("\n");
                    }
                    break;
                case "size":
                    sb.append(que.size()).append("\n");
                    break;
                case "empty":
                    if (que.isEmpty()) {
                        sb.append("1").append("\n");
                    } else {
                        sb.append(0).append("\n");
                    }
                    break;
                default:
                    que.offer(Integer.parseInt(st.nextToken()));
            }
        }
        System.out.println(sb);
    }
}
