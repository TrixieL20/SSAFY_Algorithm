package 한동건;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWAE_2477_차량정비소_Dx {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int TC = 1; TC <= T; TC++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 접수 창구 수
			int M = Integer.parseInt(st.nextToken()); // 정비 창구 수
			int K = Integer.parseInt(st.nextToken()); // 고객 수
			int A = Integer.parseInt(st.nextToken()); // 찾을 접수 창구
			int B = Integer.parseInt(st.nextToken()); // 찾을 정비 창구

			// 전부 1-indexed
			int[] aTimes = new int[N + 1];
			int[] bTimes = new int[M + 1];
			int[] visitTimes = new int[K + 1]; // visitTimes[고객번호] = 도착시간 (정렬 X)

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++)
				aTimes[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++)
				bTimes[j] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int maxVisit = 0;
			for (int k = 1; k <= K; k++) {
				visitTimes[k] = Integer.parseInt(st.nextToken());
				maxVisit = Math.max(maxVisit, visitTimes[k]);
			}

			// 창구 상태: 이용 중인 고객번호(0 = 빈 창구), 끝나는 시각
			int[] aCust = new int[N + 1], aEnd = new int[N + 1];
			int[] bCust = new int[M + 1], bEnd = new int[M + 1];
			// 고객별 이용 창구 기록
			int[] usedA = new int[K + 1], usedB = new int[K + 1];

			PriorityQueue<Integer> aWaiting = new PriorityQueue<>(); // 고객번호 작은 순
			Queue<Integer> bWaiting = new ArrayDeque<>(); // 들어온 순서 = 우선순위

			int time = 0, done = 0;
			while (done < K) {
				// 1. 접수 끝난 고객 -> 정비 대기 (창구번호 순 순회 = 우선순위 충족)
				for (int i = 1; i <= N; i++) {
					if (aCust[i] != 0 && aEnd[i] == time) {
						bWaiting.add(aCust[i]);
						aCust[i] = 0;
					}
				}
				// 2. 정비 끝난 고객 퇴장
				for (int j = 1; j <= M; j++) {
					if (bCust[j] != 0 && bEnd[j] == time) {
						bCust[j] = 0;
						done++;
					}
				}
				// 3. 도착 (도착은 maxVisit까지만 발생하므로 그 이후엔 스킵)
				if (time <= maxVisit) {
					for (int k = 1; k <= K; k++) {
						if (visitTimes[k] == time)
							aWaiting.add(k);
					}
				}
				// 4. 빈 접수 창구 배정 (번호 작은 창구부터)
				for (int i = 1; i <= N && !aWaiting.isEmpty(); i++) {
					if (aCust[i] == 0) {
						int c = aWaiting.poll();
						aCust[i] = c;
						aEnd[i] = time + aTimes[i];
						usedA[c] = i;
					}
				}
				// 5. 빈 정비 창구 배정 (번호 작은 창구부터)
				for (int j = 1; j <= M && !bWaiting.isEmpty(); j++) {
					if (bCust[j] == 0) {
						int c = bWaiting.poll();
						bCust[j] = c;
						bEnd[j] = time + bTimes[j];
						usedB[c] = j;
					}
				}
				time++;
			}

			int res = 0;
			for (int k = 1; k <= K; k++) {
				if (usedA[k] == A && usedB[k] == B)
					res += k;
			}
			sb.append('#').append(TC).append(' ').append(res == 0 ? -1 : res).append('\n');
		}
		System.out.print(sb);
	}

	
}