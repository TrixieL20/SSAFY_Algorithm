import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

	static int n;
	static int[][] map;
	static List<int[]> stairs;
	static List<int[]> people;
	static int minTime;

	public static void dfs(int depth, List<Integer> list) {
		if (depth == people.size()) {
			calculateTime(list);
			return;
		}

		for (int i = 0; i < stairs.size(); i++) {

			list.add(i);

			dfs(depth + 1, list);

			list.remove(list.size() - 1);
		}

	}

	public static void calculateTime(List<Integer> list) {

		Queue<Integer>[] pq = new PriorityQueue[stairs.size()];

		for (int i = 0; i < stairs.size(); i++) {
			pq[i] = new PriorityQueue<>();
		}

		// 각 사람의 계단 도착 시간 계산
		for (int i = 0; i < people.size(); i++) {

			int stairNumber = list.get(i);

			int[] stairIdx = stairs.get(stairNumber);
			int[] personIdx = people.get(i);

			int arrivalTime = Math.abs(stairIdx[0] - personIdx[0]) + Math.abs(stairIdx[1] - personIdx[1]);

			pq[stairNumber].offer(arrivalTime);
		}

		int totalTime = 0;

		// 계단별 소요 시간 계산
		for (int i = 0; i < stairs.size(); i++) {

			int[] stairIdx = stairs.get(i);
			
			// 계단 내려 가는데 걸리는 시간
			int k = map[stairIdx[0]][stairIdx[1]];

			int[] endTime = new int[pq[i].size()];
			int index = 0;

			while (!pq[i].isEmpty()) {

				int arrivalTime = pq[i].poll();

				int startTime = arrivalTime + 1;

				// 이미 3명이 이용 중이라면 대기
				if (index >= 3) {
					startTime = Math.max(startTime, endTime[index - 3]);
				}

				endTime[index] = startTime + k;
				index++;
			}

			// 해당 계단의 마지막 종료 시간
			if (index > 0) {
				totalTime = Math.max(totalTime, endTime[index - 1]);
			}
		}

		// 최소 시간 갱신
		minTime = Math.min(minTime, totalTime);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			stairs = new ArrayList<>();
			people = new ArrayList<>();
			minTime = Integer.MAX_VALUE;

			for (int i = 0; i < n; i++) {
				String[] input = br.readLine().split(" ");

				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(input[j]);

					if (map[i][j] != 0 && map[i][j] != 1) {
						stairs.add(new int[] { i, j });
					} else if (map[i][j] == 1) {
						people.add(new int[] { i, j });
					}
				}
			}
			dfs(0, new ArrayList<>());
			sb.append("#" + testCase + " " + minTime + "\n");
		}
		System.out.println(sb);
	}
}
