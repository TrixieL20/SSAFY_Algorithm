import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		int answer = 0;
		for(int testCase = 1; testCase <= T; testCase++)
		{
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int[] scores = new int[N];
			int[] calories = new int[N];
      answer = 0;
      
			for(int i = 0; i < N; i++)
			{
				st = new StringTokenizer(br.readLine(), " ");
				scores[i] = Integer.parseInt(st.nextToken());
				calories[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] combi = new int[N];
			int cnt = 0;
			while(++cnt <= N)
			{
				Arrays.sort(combi);
				combi[N - cnt] = 1;
				
				int sumScore = 0;
				int sumCalorie = 0;
				do
				{
					sumScore = 0;
					sumCalorie = 0;
					for(int i = 0; i < N; i++)
					{
						if(combi[i] == 1)
						{
							sumCalorie += calories[i];
							if(sumCalorie > L)
							{
								break;
							}
							sumScore += scores[i];
						}
					}
					answer = Math.max(answer, sumScore);
				}while(np(combi));
			}
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}//end of testCase
		
		System.out.print(sb);
	}
	
	static boolean np(int[] arr)
	{
		int i = N - 1;
		while(i > 0 && arr[i - 1] >= arr[i]) i--;
		if(i == 0)
		{
			return false;
		}
		int j = N - 1;
		while(arr[i - 1] >= arr[j]) j--;
		swap(arr, i - 1, j);
		
		for(int offset = 0; offset < (N - i) / 2; offset++)
		{
			swap(arr, i + offset, N - 1 - offset);
		}
		return true;
	}
	
	static void swap(int[] arr, int a, int b)
	{
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
