
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	static int n;
	static int[][] materials;
	static boolean[] selected;
	static int min;
	
	
	public static void dfs(int depth, int start) {
		if(depth == n / 2) {
			int tasteA = 0;
			int tasteB = 0;
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(selected[i] && selected[j]) {
						tasteA += materials[i][j];
					} else if(!selected[i] && !selected[j]) {
						tasteB += materials[i][j];
					}
				}
			}
			
			min = Math.min(min, Math.abs(tasteA - tasteB));
		}
		
		for(int i = start; i < n; i++) {
			selected[i] = true;

            // 재료 조합
			dfs(depth + 1, i + 1);
			
			selected[i] = false;
		}
	}
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			n = Integer.parseInt(br.readLine());
			materials = new int[n][n];
			selected = new boolean[n];
			min = Integer.MAX_VALUE;
			
			for(int i = 0; i < n; i++) {
				String[] input = br.readLine().split(" ");
				
				for(int j = 0; j < n; j++) {
					materials[i][j] = Integer.parseInt(input[j]);
				}
			}
			
			dfs(0, 0);
			
			sb.append("#" + test_case + " " + min + "\n");
		}
		System.out.println(sb);
	}
}
