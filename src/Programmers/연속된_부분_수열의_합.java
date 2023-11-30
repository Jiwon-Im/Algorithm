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
        int[] answer = {-1, -1};

        int left = 0, right = 0;
        int sum = sequence[0];

        while (left < N || right < N) {

            if (sum == k) {
                if (answer[0] == -1 || right - left < answer[1] - answer[0]) {
                    answer[0] = left;
                    answer[1] = right;
                }
            }

            if (sum <= k && right < N) {
                right++;
                if (right < N) {
                    sum += sequence[right];
                }
            } else {
                if (left < N) {
                    sum -= sequence[left];
                }
                left++;
            }

        }

        return answer;
    }
}
