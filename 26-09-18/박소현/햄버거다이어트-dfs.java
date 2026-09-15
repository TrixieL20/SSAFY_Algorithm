package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N = 0;
	static int L = 0;
	private static int[] scores;
	private static int[] calories;
	static int totalScores = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int testCase = 1; testCase <= T; testCase++)
		{
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			scores = new int[N];
			calories = new int[N];
			totalScores = 0;
			
			for(int i = 0; i < N; i++)
			{
				st = new StringTokenizer(br.readLine(), " ");
				scores[i] = Integer.parseInt(st.nextToken());
				calories[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, 0, 0);
			sb.append("#").append(testCase).append(" ").append(totalScores).append("\n");
		}//end of testCase
		
		System.out.print(sb);
	}
	
	static void dfs(int start, int cal, int score)
	{
		if(cal > L) return;
		if(start >= N)
		{
			totalScores = Math.max(totalScores, score);
			return;
		}
		dfs(start + 1, cal + calories[start], score + scores[start]);
		dfs(start + 1, cal, score);
	}
}
