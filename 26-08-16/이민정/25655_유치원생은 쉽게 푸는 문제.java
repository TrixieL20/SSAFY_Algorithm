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
            
            if (n == 1)
                System.out.println(0);
            else {
                String answer = "";
                if (n % 2 == 1) {
                    answer += "4";
                }
                
                for (int i = 0; i < n / 2; i++) {
                    answer += "8";
                }
                
                System.out.println(answer);
            }
        }
	}
}
