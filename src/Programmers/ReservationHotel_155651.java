package Programmers;

import java.util.StringTokenizer;

public class ReservationHotel_155651 {

    static int MAX_TIME = 60 * 24 + 10;
    static String[][] book_time = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};

    public static void main(String[] args) {
        int[] sum = new int[MAX_TIME];
        for (String time[] : book_time) {
            int start = cal(time[0]);
            int end = cal(time[1]) + 10;
            sum[start] += 1;    // 시작
            sum[end] -= 1;      // 끝
        }

        int max = 0; // 정답
        
        // 누적합
        for (int i = 1; i < MAX_TIME; i++) {
            sum[i] += sum[i - 1];
            max = Math.max(max, sum[i]);
        }
        System.out.println(max);
    }

    private static int cal(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        return 60 * Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
    }
}
