package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FindNextNumber_2697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            sb.append(find(br.readLine())).append("\n");
        }

        System.out.println(sb);
    }

    private static String find(String line) {
        int size = line.length();
        int last = line.charAt(size - 1) - '0';

        int j, cur;
        StringBuilder ans = new StringBuilder();
        StringBuilder left = new StringBuilder();
        for (j = size - 2; j >= 0; j--) {
            cur = line.charAt(j) - '0';

            if (cur < last) {
                ans.append(line, 0, j);
                // cur 보다 큰 수.
                int k;
                for (k = size - 1; k > j; k--) {
                    if (line.charAt(k) - '0' > cur)
                        break;
                }

                ans.append(line.charAt(k));

                left.append(line.charAt(j));
                for (int l = j + 1; l < size; l++) {
                    if (l != k) {
                        left.append(line.charAt(l));
                    }
                }
                char[] temp = left.toString().toCharArray();
                Arrays.sort(temp);
                ans.append(temp);

                break;
            }
            last = cur;

        }
        if (j < 0) return "BIGGEST";
        else return ans.toString();
    }
}
