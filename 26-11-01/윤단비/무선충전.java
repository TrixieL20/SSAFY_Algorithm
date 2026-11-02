package swea_5644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	private static int M; //총 이동 시간
	private static int[] userA_dir; //사용자 A의 이동 정보 배열
	private static int[] userB_dir; //사용자 B의 이동 정보 배열
	
	private static int A; // BC의 개수
	private static int[][] bc; //BC의 정보를 가진 배열
	
	private static List<Integer> listA;
	private static List<Integer> listB;

	private static int MAP_SIZE=11;
	//private static int[][] map=new int[MAP_SIZE][MAP_SIZE]; //맵
	
	//이동x,상,우,하,좌
	private static int[] dr=new int[] {0,-1,0,1,0};
	private static int[] dc=new int[] {0,0,1,0,-1};
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			M=Integer.parseInt(st.nextToken()); //총 이동 시간
			A=Integer.parseInt(st.nextToken()); // BC의 개수
			
			//사용자 A,B의 이동 정보
			userA_dir=new int[M];
			userB_dir=new int[M];
			st=new StringTokenizer(br.readLine()," ");
			for(int i=0;i<M;i++) {
				userA_dir[i]=Integer.parseInt(st.nextToken());
			}
			
			st=new StringTokenizer(br.readLine()," ");
			for(int i=0;i<M;i++) {
				userB_dir[i]=Integer.parseInt(st.nextToken());
			}
			
			
			//BC의 정보를 가진 배열
			bc=new int[A][4]; 
			for(int i=0;i<A;i++) {
				st=new StringTokenizer(br.readLine()," ");
				for(int j=0;j<4;j++) {
					//좌표(X, Y), 충전 범위(C), 처리량(P)
					bc[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			System.out.println("#"+tc+" "+play());
		}
		br.close();
	}
	
	public static int play() {
		int userA_r=1, userA_c=1;
		int userB_r=10, userB_c=10;
		
		int sum=0;
		
		//초기 위치에서의 충전 가능성
		sum+=getMaxCharge(userA_r,userA_c,userB_r,userB_c);
		
		//주어진 시간동안 총합 구하기
		for(int time=0;time<M;time++) {
			//각 사용자가 주어진 방향으로 이동
			int userA_nr=userA_r+dr[userA_dir[time]];
			int userA_nc=userA_c+dc[userA_dir[time]];
			
			int userB_nr=userB_r+dr[userB_dir[time]];
			int userB_nc=userB_c+dc[userB_dir[time]];
			
			//선택 조합 판별하여 다음 시점에서 최대 합 구하기
			int max=getMaxCharge(userA_nr,userA_nc,userB_nr,userB_nc);
			sum+=max; //최대합을 누적
			
			//다음 위치를 현재 위치로 변경
			userA_r = userA_nr;
			userA_c = userA_nc;

			userB_r = userB_nr;
			userB_c = userB_nc;
		}
		return sum;
	}
	
	//충전 가능 여부 리턴 메서드
	public static boolean canCharge(int ar,int ac,int br,int bc, int coverage) {
		int dist=(int)(Math.abs(ar-br) + Math.abs(ac-bc));
		
		if(dist<=coverage) return true;
		
		return false;
	}
	
	//현재 A,B 위치에서 최대 중전합 리턴 메서드
	public static int getMaxCharge(int a_r,int a_c,int b_r,int b_c){
		
		// 각 시점에서 리스트 초기화			
		listA=new ArrayList<>();
		listB=new ArrayList<>();
		
		//다음 시점에서 A와 B 각각 충전 가능한 충전소 모두 구하기
		for(int i=0;i<A;i++) {
			//x,y좌표가 r,c와 순서 다르다는 점 주의
			int bc_r=bc[i][1];
			int bc_c=bc[i][0];
			int bc_coverage=bc[i][2];
			
			//A와 B가 다음 충전소에서 충전 가능한지 검사
			if(canCharge(a_r,a_c,bc_r,bc_c,bc_coverage)) {
				listA.add(i);
			}
			if(canCharge(b_r,b_c,bc_r,bc_c,bc_coverage)) {
				listB.add(i);
			}
		}
					
		//각 시점의 최대 충전 합
		int max=0;
		//둘 다 충전 가능한 시점인 경우
		if(listA.size()>0 && listB.size()>0) {
			for(int a:listA) {
				for(int b:listB) {
					int charge=0; //조합별 충전량
					
					//같은 충전소 선택한 경우
					if(a==b) {
						//충전량을 나눠 가짐
						charge=bc[a][3];
					}
					
					//서로 다른 충전소를 선택한 경우
					else {
						charge+=(bc[a][3]+bc[b][3]);
					}
					
					if(charge>max) max=charge;
				}
			}
		}
		//A만 충전 가능
		else if(listA.size()>0) {
			for(int a:listA) {
				if(bc[a][3]>max) max=bc[a][3];
			}
		}
		//B만 충전 가능
		else {
			for(int b:listB) {
				if(bc[b][3]>max) max=bc[b][3];
			}
		}
		return max;
	}
}
