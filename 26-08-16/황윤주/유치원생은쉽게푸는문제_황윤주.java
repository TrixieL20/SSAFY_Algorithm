import java.util.*;

class Solution {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            int n = sc.nextInt();
            StringBuilder sb = new StringBuilder();

            // n이 1이면 0 출력 (예외 케이스 중 가장 작은 값)
            if (n == 1) {
                sb.append("0");

            }
            // 홀수인 경우 앞에 4 넣어주기
            else {
                if (n % 2 == 1) {
                    sb.append("4");
                    n--;
                }
                // 나머지는 8로 채우기 (짝수인 경우도 동일)
                while (n > 0) {
                    sb.append("8");
                    n -= 2;
                }
            }

            System.out.println(sb);
        }
    }
}