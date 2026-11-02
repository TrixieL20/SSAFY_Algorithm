import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	// 정지, 상, 우 ,하, 좌
	static int[] dys = { 0, -1, 0, 1, 0 };
	static int[] dxs = { 0, 0, 1, 0, -1 };
	static int[][] BCInfos;
	static boolean[] AinBC;
	static boolean[] BinBC;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			String[] infos = br.readLine().split(" ");
			int M = Integer.parseInt(infos[0]);
			int A = Integer.parseInt(infos[1]);

			

			int[] AMove = new int[M + 1];
			int[] BMove = new int[M + 1];
			
			int res =0;
			// 맨 처음 움직임 0
			AMove[0] = 0;
			BMove[0] = 0;

			// 사용자 무빙 저장
			{
				String[] move1 = br.readLine().split(" ");

				for (int i = 0; i < M; i++) {
					AMove[i + 1] = Integer.parseInt(move1[i]);
				}

				String[] move2 = br.readLine().split(" ");

				for (int i = 0; i < M; i++) {
					BMove[i + 1] = Integer.parseInt(move2[i]);
				}
			}

			// 충전기 정보 저장
			{
				BCInfos = new int[A][4];

				for (int j = 0; j < A; j++) {
					String[] line = br.readLine().split(" ");
					for (int i = 0; i < 4; i++) {
						BCInfos[j][i] = Integer.parseInt(line[i]);
					}
				}

			}

			int[] aPos = new int[] { 1, 1 };
			int[] bPos = new int[] { 10, 10 };

			

			for (int i = 0; i <= M; i++) {
				// 이번턴 a,b 좌표들

				aPos[0] = aPos[0] + dxs[AMove[i]];
				aPos[1] = aPos[1] + dys[AMove[i]];

				bPos[0] = bPos[0] + dxs[BMove[i]];
				bPos[1] = bPos[1] + dys[BMove[i]];
					
				AinBC = new boolean[A];
				BinBC = new boolean[A];
				cntInBc(aPos, bPos);
				int best = 0;
				for (int a = -1; a < A; a++) {              // -1 = A는 아무것도 안 고름 (공집합)
				    if (a >= 0 && !AinBC[a]) continue;
				    for (int b = -1; b < A; b++) {          // -1 = B는 아무것도 안 고름 (공집합)
				        if (b >= 0 && !BinBC[b]) continue;

				        int sum;
				        if (a == b && a >= 0) sum = BCInfos[a][3];   // 같은 BC -> 반씩, 합은 P
				        else sum = (a >= 0 ? BCInfos[a][3] : 0) + (b >= 0 ? BCInfos[b][3] : 0);

				        best = Math.max(best, sum);
				    }
				}
				res += best;
			}
			
			System.out.println("#"+tc+" "+res);
		} // end of tc

	}// end of main

	public static void cntInBc(int[] aPos, int[] bPos) {
		for (int i = 0; i < BCInfos.length; i++) {
			int BCx = BCInfos[i][0];
			int BCy = BCInfos[i][1];
			int BCr = BCInfos[i][2];

			// 충전기 범위 안일때
			if (Math.abs(aPos[0] - BCx) + Math.abs(aPos[1] - BCy) <= BCr) {
				AinBC[i]=true;
			}

			// 충전기 범위 안일때
			if (Math.abs(bPos[0] - BCx) + Math.abs(bPos[1] - BCy) <= BCr) {
				BinBC[i]=true;
			}

		}
	}
  
}// end of class
