package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RecursionFunction_17478 {
    static int N;
    static StringBuilder sb;
    static String quest = "\"재귀함수가 뭔가요?\"\n";
    static String ment1 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
    static String ment2 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
    static String ment3 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n";
    static String ans = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n";
    static String end = "라고 답변하였지.\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");

        recursive("", 0);

        System.out.println(sb);
    }

    private static void recursive(String bar, int cnt) {

        sb.append(bar).append(quest);

        // 재귀 끝에 도달했다면 정답 출력
        if (cnt == N) {
            sb.append(bar).append(ans);
            sb.append(bar).append(end);
            return;
        }

        // 끝이 아니라면 말 돌리기
        sb.append(bar).append(ment1);
        sb.append(bar).append(ment2);
        sb.append(bar).append(ment3);

        // dfs
        recursive(bar.concat("____"), cnt + 1);

        // 마지막 문장
        sb.append(bar).append(end);
    }
}
