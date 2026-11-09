import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;



public class Solution {
	public static class AP {
		int r;
		int c;
		int charge; // 충전 범위
		int p; // 충전 성능
		
		public AP(int r, int c, int charge, int p) {
			this.r = r;
			this.c = c;
			this.charge = charge;
			this.p = p;
		}
	}
	
	static int M, A, answer;
	static int[][] map;
	static int[] userA;
	static int[] userB;
	
	// 0 :이동x  1:위   2: 오른쪽  3: 아래  4: 왼쪽
	static int[] dr = {0,-1,0,1,0};
	static int[] dc = {0,0,1,0,-1};
	
	static AP[] aps;
	
	
	static void play() {
		// 사용자 시작 위치
		int rA = 0, cA = 0;
		int rB = 9, cB = 9;
		
		// 0초 : 이동 전 충전 
		answer += getMaxCharge(rA, cA, rB, cB);
		
		// 1초부터 M초까지 이동
		for(int i=0; i<M; i++) {
			int dirA = userA[i];
			int dirB = userB[i];
			
			// A 이동
			rA += dr[dirA];
			cA += dc[dirA];
			
			// B 이동
			rB += dr[dirB];
			cB += dc[dirB];
			
			// 이동 후 충전량 계산
			answer += getMaxCharge(rA, cA, rB, cB);
			
			
		}
		
	}
	
	static int getMaxCharge(int rA, int cA, int rB, int cB) {
		int max = 0;
		
		// -1은 선택하지 않는 경우
		for(int a=-1; a<A; a++) {
			for(int b=-1; b<A; b++) {
				
				int sum = 0;
				
				// A가 선택한 BC를 사용할 수 있는지
				int powerA = 0;
				if(a!=-1 && canCharge(rA, cA, aps[a])) {
					powerA = aps[a].p;
				}
				
				// B가 선택한 BC를 사용할 수 있는지
				int powerB = 0;
				if(b!=-1 && canCharge(rB, cB, aps[b])) {
					powerB = aps[b].p;
				}
				
				// 같은 BC를 선택하고 둘 다 충전이 가능한 경우
				if(a==b && a!=-1 && powerA>0 && powerB>0) {
					sum = aps[a].p;
				}else {
					sum = powerA + powerB;
				}
				
				max = Math.max(max, sum);
				
			}
		}
		
		return max;
	}
	
	static boolean isInRange(int r, int c) {
		return r>=0 && r<10 && c>=0 && c<10;
	}
	
	

	// 맨해튼 거리 공식 이용해서 충전 가능한지 계속 확인하기 
	static boolean canCharge(int r, int c, AP ap) {
		int dist = Math.abs(r-ap.r) + Math.abs(c-ap.c);
		return dist<=ap.charge;
	}
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 총 이동 시간 
			A = Integer.parseInt(st.nextToken()); // BC개수
			
			
			map = new int[10][10];
			userA = new int[M];
			userB = new int[M];
			
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				userA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				userB[i] = Integer.parseInt(st.nextToken());
			}
			
			aps = new AP[A];
			for(int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int charge = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				
				aps[i] = new AP(y-1, x-1, charge, p);
			}
			
			
			// -------- 입력
			
			answer = 0;
			play();
			

			
			System.out.println("#"+tc+" "+answer);
		}
		
	}

}
