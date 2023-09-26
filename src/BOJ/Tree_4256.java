package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tree_4256 {
    static StringBuilder sb;
    static BufferedReader br;
    static int N;
    static int[] pre, in, index;
    static int rootIdx;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            // 입력
            init();

            // 트리
            findTree(0, 0, N - 1);

            // 한 줄 띄우기
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void findTree(int rootIdxInPre, int left, int right) {

        // 이번 트리 루트 값
        int rootValue = pre[rootIdxInPre];

        // 루트가 inorder 배열에서 위치하는 idx
        int rootIdxInInorder = index[rootValue];

        // 왼쪽 트리(left ~ rootIdxInInorder)
        if (left < rootIdxInInorder) {
            findTree(++rootIdx, left, rootIdxInInorder - 1);
        }

        // 오른쪽 트리(rootIdxInInorder ~ right)
        if (rootIdxInInorder < right) {
            findTree(++rootIdx, rootIdxInInorder + 1, right);
        }

        // 후위 순회 출력
        sb.append(rootValue).append(" ");
    }


    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());
        pre = new int[N];
        in = new int[N];
        index = new int[N + 1]; // inorder 값 위치 저장

        // preorder 배열 값 순서대로 root
        rootIdx = 0;

        // 전위 순회
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pre[i] = Integer.parseInt(st.nextToken());
        }

        // 중위 순회
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            in[i] = Integer.parseInt(st.nextToken());
            index[in[i]] = i;
        }
    }
}
