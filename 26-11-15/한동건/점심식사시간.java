import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 * 접근
 * .. 계단 입구가 2개다
 * 또 계단을 내려가는 시간이 있다. 
 * 가장 가까운 계단을 가더라도 계단 내려가는 시간이 더 길다면? 혹은 대기가 너무 많다면?
 * 멀리잇는 계단에 가는것보다 비효율적이다.
 * 
 * 어케함?
 * 사람 수 최대 10명이니까 각각 계단을 고르는 경우의수 다구하기?
 * -> 1024개 경우의수 이거 미리 다구해서 시뮬돌리기?
 * 
 * 
 */


public class SWEA_2383_점심식사시간_Dx {
	
	
	static class Stair{
		int x,y,time;
		
		
		public Stair() {
			super();
		}


		Stair(int x, int y, int time){
			this.x =x;
			this.y =y;
			this.time = time;
			
		}


		@Override
		public String toString() {
			return "Stair [x=" + x + ", y=" + y + ", time=" + time + "]";
		}
		
		
	}
	
	static int[][] grid;
	static List<Stair> stairsInfos;
	static List<int[]> personPos;
	
	static List<Integer> stairAList;
	static List<Integer> stairBList;
	
	static int res;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine()) ;
		
		for(int tc = 1; tc<=TC; tc++) {
			int N = Integer.parseInt(br.readLine()) ;
			
			grid = new int[N][N];
			
			
			stairsInfos = new ArrayList<>();
			
			
			personPos = new ArrayList<>();
			
			res = Integer.MAX_VALUE;
			
			for(int i=0; i<N;i++) {
				String[] line = br.readLine().split(" ");
				
				for(int j=0; j<N; j++) {
					grid[i][j] = Integer.parseInt(line[j]);
					
					// 사람 위치 정보 (x,y)
					if(grid[i][j]==1) {
						personPos.add(new int[] {j,i});
						
					}
					// 계단 입구 정보
					else if(grid[i][j] > 1) {
						stairsInfos.add(new Stair(j,i,grid[i][j]));
					}
									
				}
				
			}
			
			stairAList = new ArrayList<>();
			stairBList = new ArrayList<>();

			
			makeSubset(0);
			
			System.out.println("#"+tc+" "+res);
			
//			for(int[] e : personPos) {
//				System.out.println(Arrays.toString(e));
//			}
//			
//			for(Stair e : stairsInfos) {
//				System.out.println(e);
//			}
			
		
			
		}// end of tc
	}// end of main
	

	private static void makeSubset(int idx) {
		
		// 사람들 계단 위치 확정
		if(idx == personPos.size()) {
			
			res = Math.min(res, func());
			return;
		}
		
		int timeToA = Math.abs(personPos.get(idx)[0] - stairsInfos.get(0).x) +  Math.abs(personPos.get(idx)[1] - stairsInfos.get(0).y);
		int timeToB = Math.abs(personPos.get(idx)[0] - stairsInfos.get(1).x) +  Math.abs(personPos.get(idx)[1] - stairsInfos.get(1).y);
		
		
		// A 계단으로 가는 사람들 리스트
		stairAList.add(timeToA);
		makeSubset(idx+1);
		stairAList.remove(stairAList.size()-1);
		
		// B 계단으로 가는 사람들 리스트
		stairBList.add(timeToB);
		makeSubset(idx+1);
		stairBList.remove(stairBList.size()-1);

		
		
	}
	
	private static int func() {
		
		// 계단 내려가는데 소요시간
		int aTime = stairsInfos.get(0).time;
		int bTime = stairsInfos.get(1).time;
		
		int time = 0;
		
		// 다 내려간 인원 체크
		int cntPerson =0;
		
		// 사용중인 계단, 데이터에는 끝나는 시간 기입, 0이면 미사용중
		int[] usedStairA = new int[3];
		int[] usedStairB = new int[3];

		Deque<Integer> aQue = new ArrayDeque<>();
		Deque<Integer> bQue = new ArrayDeque<>();

		
		// 다 내려갈때까지 순환
		while(cntPerson <personPos.size()) {
			
			time ++;
			// A계단 도착했으면 일단 큐에 넣기
			for(int t : stairAList) {
				if(time == t) {
					aQue.add(t);
				}
			}
			
			// B계단 도착했으면 일단 큐에 넣기
			for(int t : stairBList) {
				if(time == t) {
					bQue.add(t);
				}
			}

			// A계단 완료처리
			for(int i=0; i<3; i++) {
				// 계단 다내려 왔으면 비움처리
				if( time==usedStairA[i]) {
					usedStairA[i] = 0;
					
					// 다 내려간사람 카운트
					cntPerson++;
				}
			}
			
			// B계단 완료처리
			for(int i=0; i<3; i++) {
				// 계단 다내려 왔으면 비움처리
				if( time==usedStairB[i]) {
					usedStairB[i] = 0;
					
					// 다 내려간사람 카운트
					cntPerson++;
				}
			}
		
			
			// A계단 처리
			for(int i=0; i<3; i++) {
				
				// 큐에 아무것도 없으면 스킵
				if(aQue.isEmpty()) break;
				
				// 계단 빈자리 있으면, 큐에 기다리는 애로 채우기
				if(usedStairA[i]==0) {
					
					// 지금 계단에 도착했으면 1분 기다려야함
					if(time == aQue.peekFirst()) {	
						break;
					}
					
					aQue.pollFirst();
					usedStairA[i] = time + aTime;
				}
				
			}
			
			// B계단 처리
			for(int i=0; i<3; i++) {
				
				// 큐에 아무것도 없으면 스킵
				if(bQue.isEmpty()) break;
				
				// 계단 빈자리 있으면, 큐에 기다리는 애로 채우기
				if(usedStairB[i]==0) {
					
					// 지금 계단에 도착했으면 1분 기다려야함
					if(time == bQue.peekFirst()) {	
						break;
					}
					bQue.pollFirst();
					usedStairB[i] = time + bTime;
				}
				
			}
			
		}
		
		
		return time;
	}
	
}// end of class
