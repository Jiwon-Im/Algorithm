package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// https://www.acmicpc.net/problem/2263
public class CircuitOfTree_2263 {
    static int N;
    static int[] inorder, postorder, idxInorder, idxPostorder;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        init(); // 입력 초기화

        findTree(postorder[N - 1], 0, N - 1); // root, left, right
        System.out.println(sb);
    }

    private static void findTree(int rootValue, int left, int right) {

        // 루트부터 출력
        sb.append(rootValue).append(" ");

        if (left == right) {
            return;
        }

        // post : 왼쪽 트리 전부 방문 -> "왼쪽 자식 루트 방문" -> 오른쪽 트리 자식 모두 방문 -> 오른쪽 트리 루트 방문 -> 루트
        // 왼쪽 자식 루트 : 루트 - (오른쪽 자식트리 크기) -1
        if (left < idxInorder[rootValue]) {
            int rightChildTreeSize = right - idxInorder[rootValue]; // 오른쪽 자식트리 크기
            // 왼쪽 자식 루트 : 현재 루트를 중심으로 좌 우로 나누었을 때 바로 앞자리의 postorder 값
            // left : left
            // right : 중위순회 기준 루트의 왼쪽  //  왼쪽 자식트리/루트/오른쪽 자식트리
            findTree(postorder[idxPostorder[rootValue] - rightChildTreeSize - 1], left, idxInorder[rootValue] - 1);
        }

        // post : 왼쪽 트리 전부 방문 -> 왼쪽 자식 루트 방문 -> 오른쪽 트리 자식 모두 방문 -> "오른쪽 트리 루트 방문" -> 루트
        // 오른쪽 자식 루트 : postOrder 에서 부모루트 바로 앞
        if (idxInorder[rootValue] < right) {
            // left : 중위순회 기준 루트의 오른쪽
            // right : right
            findTree(postorder[idxPostorder[rootValue] - 1], idxInorder[rootValue] + 1, right);
        }

    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        inorder = new int[N];
        postorder = new int[N];
        idxInorder = new int[N + 1]; // inorder 노드의 idx
        idxPostorder = new int[N + 1]; // postorder 노드의 idx

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
            idxInorder[inorder[i]] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
            idxPostorder[postorder[i]] = i;
        }
    }
}
