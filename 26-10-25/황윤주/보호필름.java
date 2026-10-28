import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int answer, D, W, K;
	static int[][] film;
	
	// 행 돌면서 확인 
	static void dfs(int row, int count) {
		// 현재까지 찾은 최소 투입 횟수 이상이면 중단
		if(count >= answer) return;
		
		// 모든 행에 대한 선택 완료
		if(row==D) {
			if(check()) {
				answer = Math.min(answer, count);
			}
			
			return;
		}
		
		// 1. 현재 행 변경 X
		dfs(row+1, count);
		
		// 원상복구를 위해 현재 행 저장
		int[] backup = film[row].clone();
		
		// 2. A 약품 투입 (0)
		Arrays.fill(film[row], 0);
		dfs(row+1, count+1);
		
		// 3. B 약품 투입 (1)
		Arrays.fill(film[row], 1);
		dfs(row+1, count+1);
		
		// 현재 행 원상복구
		film[row] = backup;
		
	}
	
	// k 이상 연속하는지 확인
	static boolean check() {
		// 모든 열 검사
		for(int c = 0; c<W; c++) {
			int count = 1;
			boolean pass = false;
			
			// 위에서 아래로 행 이동하며 비교
			for(int r=1; r<D; r++) {
				if(film[r][c]==film[r-1][c]) {
					count++;
				}else {
					count = 1;
				}
				
				// k개 이상이면 열 통과
				if(count>=K) {
					pass = true;
					break;
				}
				
			}
			
			// 하나의 열이라도 실패하면 전체 실패
			if(!pass & K>1) {
				return false;
			}
		}
		
		return true;
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			D = Integer.parseInt(st.nextToken()); // 가로
			W = Integer.parseInt(st.nextToken()); // 세로
			K = Integer.parseInt(st.nextToken()); // 연속으로 세로로 연속해야 하는 글자수
			
			film = new int[D][W];
			
			// 필름 입력값
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			answer = K;
			
			// dfs로 row마다 하나하나 변경해 보기
			dfs(0,0);
			
			
			

			
			System.out.println("#"+tc+" "+answer);
		}
		
	}

}
