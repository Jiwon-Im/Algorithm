package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SortGame_1327 {
    static int N, K;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        num = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        String word = Arrays.toString(num).replaceAll("[^0-9]", "");
        Arrays.sort(num);
        String target = Arrays.toString(num).replaceAll("[^0-9]", "");

        System.out.println(find(word, target));
    }

    private static int find(String start, String target) {

        if (start.equals(target)) {
            return 0;
        }

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));
        StringBuilder sb;
        Set<String> set = new HashSet<>();
        set.add(start);


        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i <= N - K; i++) {
                sb = new StringBuilder(node.value.substring(i, i + K)).reverse();
                String temp = node.value.substring(0, i) + sb + node.value.substring(i + K, N);

                if (temp.equals(target)) {
                    return node.cnt + 1;
                }

                if (!set.contains(temp)) {
                    set.add(temp);
                    q.offer(new Node(temp, node.cnt + 1));
                }

            }

        }
        return -1;
    }

    private static class Node {
        String value;
        int cnt;

        public Node(String value, int cnt) {
            this.value = value;
            this.cnt = cnt;
        }
    }
}
