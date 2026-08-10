import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();

            switch(n) {
                case 0:
                    System.out.println(1);
                    break;
                case 1:
                    System.out.println(0);
                    break;
                case 2:
                    System.out.println(8);
                    break;
                default:
                    String answer = "";
                    if (n % 2 == 0) {
                        for (int i = 0; i < n / 2; i++) {
                            answer += "8";
                        }
                    }
                    if (n % 2 != 0) {
                        answer += "48";
                        for (int i = 0; i < (n - 3) / 2; i++) {
                            answer += "8";
                        }
                    }

                    System.out.println(answer);
            }
        }
    }
}