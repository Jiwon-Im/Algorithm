package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// https://www.acmicpc.net/problem/1541
public class MissingParenthesis_1541 {
    static ArrayList<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        makeInputArrayList(br.readLine());
        calculate();
    }

    private static void calculate() {
        int ans = 0;
        int sign = 1;
        for (Integer num : list) {
            if (sign > 0 && num < 0) {
                sign = -1;
            }
            ans += sign * Math.abs(num);
        }
        System.out.println(ans);
    }

    private static void makeInputArrayList(String line) {
        list = new ArrayList<>();
        char c;
        int num = 0, sign = 1;
        for (int i = 0; i < line.length(); i++) {
            c = line.charAt(i);

            if (c == '+') {
                list.add(sign * num);
                num = 0;
                sign = 1;
            } else if (c == '-') {
                list.add(sign * num);
                num = 0;
                sign = -1;
            } else { // 숫자
                num *= 10;
                num += c - '0';
            }
        }
        list.add(sign * num);
    }

}
