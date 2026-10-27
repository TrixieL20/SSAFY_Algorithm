import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Solution {

	static int D;
	static int W;
	static int K;
	static int[][] matrix;
	static int[][] originalMatrix;
	static int[] injectionRows;

	static int minInjection ;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			matrix = new int[D][W];

			// 매트릭스 초기화
			for (int j = 0; j < D; j++) {
				st = new StringTokenizer(br.readLine());
				for (int i = 0; i < W; i++) {
					matrix[j][i] = Integer.parseInt(st.nextToken());
				}
			}

			originalMatrix = new int[D][W];

			for (int i = 0; i < matrix.length; i++) {
				originalMatrix[i] = matrix[i].clone();
			}

			injectionRows = new int[D];
			Arrays.fill(injectionRows, -1);
			minInjection = Integer.MAX_VALUE;
			selectInjectionRow(0);

			System.out.println("#"+tc+" "+minInjection);

		} // end of testcase

	}// end of main

	public static void selectInjectionRow(int idx) {

		if (idx == D) {
			int injectionCnt = 0;
			// 이번 경우의수에서 투여 횟수
			for (int i = 0; i < injectionRows.length; i++) {
				if (injectionRows[i] != -1) {
					injectionCnt++;
				}
			}
			
			// 가지치기 : 이미 최소보다 투입수가 크면 isOkfilm 호출 x
			if(minInjection <= injectionCnt) return;
			
			if (isOkFilm()) {
				

				minInjection = Math.min(minInjection, injectionCnt);
			}

			for (int i = 0; i < matrix.length; i++) {
				matrix[i] = originalMatrix[i].clone();
			}
			return;
		}

		// 선택 x
		injectionRows[idx] = -1;
		selectInjectionRow(idx + 1);

		// A약물
		injectionRows[idx] = 0;

		selectInjectionRow(idx + 1);

		// B약물
		injectionRows[idx] = 1;

		selectInjectionRow(idx + 1);

		// 복구
		injectionRows[idx] = -1;

	}

	// 약물 투여하고 ok인지 확인
	public static boolean isOkFilm() {
		int cnt = 0;
		// 약물 투여
		injection();

		for (int i = 0; i < W; i++) {
			cnt = 1;
			for (int j = 1; j < D; j++) {
				if (matrix[j][i] == matrix[j - 1][i]) {
					cnt++;
					// 특성 바뀌면
				} else {
					// K겹이상 있었으면 다음 열로(break)
					if (cnt >= K)
						break;

					cnt = 1;

				}
			}
			// K겹 못넘겼으면 실패
			if (cnt < K) {
				return false;
			}

		}

		return true;

	}

	// 약물 투여
	public static void injection() {

		// 약물 투여
		for (int j = 0; j <D; j++) {
			if (injectionRows[j] != -1) {
				for (int i = 0; i < W; i++) {
					matrix[j][i] = injectionRows[j];
				}
			}
		}

	}
}// end of class
