
/* 접근 
 * 
 * 음 그냥 조합인듯? 재료 N개 중에 N/2개 뽑고 이걸로 A음식, 나머지로 B음식 만들어서 
 * 매 번 점수 구해서 최소 저장해서 리턴 
 * 
 * N=3일때
 * 1,2,3
 * 
 * 인영이규영이 문제같음
 * 1,2/ 1,3/ 2,1/ 2,3/ 3,1/ 3,2/
 * 
 */

import java.io.*;
import java.util.*;


public class SWEA_4012_요리사_Dx {
	private static int N;
	private static boolean[] selectedIgreds;
	private static int[][] ingreds;
	private static int res;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());

			ingreds = new int[N][N];
			for (int j = 0; j < N; j++) {
				String[] line = br.readLine().split(" ");
				for (int i = 0; i < N; i++) {
					ingreds[j][i] = Integer.parseInt(line[i]);
				}
			}

			selectedIgreds = new boolean[N];

			res = Integer.MAX_VALUE;
			combi(0, 0);
			
			System.out.println("#"+tc+" "+res);
		
		} // end of tc
	}// end of main

	public static void combi(int cnt, int idx) {

		// A음식 재료선택 완료
		if (cnt == N / 2) {
			res = Math.min(res,curDiff());
			return;
		}

		// 인덱스 넘으면 리턴
		if (idx == N) {
			return;
		}

		selectedIgreds[idx] = true;
		combi(cnt + 1, idx + 1);
		selectedIgreds[idx] = false;

		combi(cnt, idx + 1);

	}

	public static int curDiff() {
		int[] ingredsA = new int[N / 2];
		int[] ingredsB = new int[N / 2];

		int sumA = 0;
		int sumB = 0;

		int idxA = 0;
		int idxB = 0;

		// A,B 재료저장
		for (int i = 0; i < N; i++) {
			if (selectedIgreds[i]) {
				ingredsA[idxA++] = i;
				continue;
			}
			ingredsB[idxB++] = i;

		}


		for (int e1 : ingredsA) {
			for (int e2 : ingredsA) {
				if(e1==e2)continue;
				sumA += ingreds[e1][e2];
			}
		}
		
		for (int e1 : ingredsB) {
			for (int e2 : ingredsB) {
				if(e1==e2)continue;
				sumB += ingreds[e1][e2];
			}
		}
		
		return Math.abs(sumA-sumB);
	}

}// end of class
