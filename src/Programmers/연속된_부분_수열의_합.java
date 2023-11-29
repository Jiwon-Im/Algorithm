package Programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/178870
public class 연속된_부분_수열의_합 {
    public static void main(String[] args) {
        int[] sequence = {1, 2, 3, 4, 5};
        int k = 7;
        solution(sequence, k);
    }

    public static int[] solution(int[] sequence, int k) {
        int N = sequence.length;
        int[] pSum = new int[N + 1];


        for (int i = 1; i <= N; i++) {
            pSum[i] = pSum[i - 1] + sequence[i - 1];
        }

        // 길이
        for (int i = 0; i <= N; i++) {
            // 인덱스
            for (int j = 1; j <= N; j++) {
                if (j + i <= N && pSum[j + i] - pSum[j - 1] == k) {
                    System.out.println(pSum[j + i] + " " + pSum[j]);
                    return new int[]{j - 1, j + i - 1};
                }
            }
        }
        return null;
    }
}
