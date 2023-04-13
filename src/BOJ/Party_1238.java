package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Party_1238 {
    static int N, M, X;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];

        Node[] nodes = new Node[N + 1];

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            Node node = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        }


    }

    static private class Node {
        int start;
        int end;
        int time;

        public Node(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }

}
