package Programmers;

public class 뒤에_있는_큰_수_찾기 {
    public int[] solution(int[] numbers) {
        int N = numbers.length;
        int[] answer = new int[N];

        answer[N - 1] = -1;
        for (int i = N - 2; i >= 0; i--) {
            if (numbers[i] < numbers[i + 1]) {
                answer[i] = numbers[i + 1];
            } else if (numbers[i] < answer[i + 1]) {
                answer[i] = answer[i + 1];
            } else {
                answer[i] = -1;
            }
        }

        return answer;
    }
}