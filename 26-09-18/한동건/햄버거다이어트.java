import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int[] scores;
	static int[] cals ;
	
	static int N;
	static int L;
	
	static int MAXSCORE;
	
	static public void dfs(int idx, int totalScore, int totalCal) {
		
		if (totalCal>L) {
			return;
		}
		
		
		if( idx==N) {
			MAXSCORE = Math.max(MAXSCORE, totalScore);
			return;
		}
		
		
		
		// 이번 인덱스 재료 포함
		dfs(idx+1, totalScore+scores[idx], totalCal+cals[idx]);
		
		// 이번 인덱스 재료 미포함
		dfs(idx+1, totalScore, totalCal);
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc =1; tc<=T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			scores = new int[N];
			cals = new int[N];
			
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				
				scores[i] = Integer.parseInt(st.nextToken());
				cals[i] = Integer.parseInt(st.nextToken());

			}
			MAXSCORE = Integer.MIN_VALUE;
			dfs(0,0,0);
			
			System.out.println("#"+tc+" "+MAXSCORE);
		}
		
		
	}
}
