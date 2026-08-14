import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++)
		{
			int N = Integer.parseInt(br.readLine());
			int repeat = N / 2;
			StringBuilder sb = new StringBuilder();
            if(N == 1)
            {
				sb.append(0).append('\n');
				System.out.print(sb);
                continue;
            }
            if(N % 2 == 1)
			{
				sb.append(4);
			}
			for(int i = 0; i < repeat; i++)
			{
				sb.append(8);
			}
			
			sb.append('\n');
			System.out.print(sb);
		}
	}
}
