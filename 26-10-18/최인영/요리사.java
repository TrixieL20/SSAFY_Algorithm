import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	private static int n;
	private static int[][] synergy;
	private static int diff;
	
	// 재료 합치기 
	private static void combine(int idx, int[] A, int[] B, int countA, int countB) {
		// n개 조합 완성한 경우 A, B의 시너지 구해 차이 계산
		if(idx == n) {
			int synA = sumSynergy(A);
			int synB = sumSynergy(B);
			diff = Math.min(diff, Math.abs(synA - synB));
			return;
		}
		
		// idx 번째 재료 A, B 중 어디에 넣을지 결정
		// A 조합 n/2개 안 채워진 경우 
		if(countA < n/2) {
			A[countA] = idx;
			combine(idx+1, A, B, countA+1, countB);
		}
		// B 조합 n/2개 안 채워진 경우 
		if(countB < n/2) {
			B[countB] = idx;
			combine(idx+1, A, B, countA, countB+1);
		}
	}
    
	// 재료들 시너지 계산 
	private static int sumSynergy(int[] food) {
		int sum = 0;
		for (int i = 0; i < food.length-1; i++) {
			for (int j = i+1; j < food.length; j++) {
				int first = food[i];
				int second = food[j];
				sum += synergy[first][second] + synergy[second][first];
			}
		}
		return sum;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			n = Integer.parseInt(br.readLine());
			
			synergy = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			diff = Integer.MAX_VALUE;
			
			int[] A = new int[n/2];
			A[0] = 0;
			combine(1, A, new int[n/2], 1, 0);
			sb.append(diff).append("\n");
		}
		System.out.println(sb.toString());
	}
}