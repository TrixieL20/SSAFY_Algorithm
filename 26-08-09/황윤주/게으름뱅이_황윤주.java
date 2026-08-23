import java.io.*;
import java.util.*;

public class Solution {

    static class Work {
        int d; // 걸리는 시간
        int t; // 마감일

        public Work(int d, int t) {
            this.d = d;
            this.t = t;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= TC; tc++) {

            int N = Integer.parseInt(br.readLine());

            Work[] works = new Work[N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int d = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                works[i] = new Work(d, t);
            }

            // 마감일 내림차순 정렬
            Arrays.sort(works, (a, b) -> Integer.compare(b.t, a.t));

            // 가장 늦게 시작 가능한(마감일 느린) 시간 저장
            int time = works[0].t;

            for (int i = 0; i < N; i++) {
                // min 하는이유 : time-day 한 값이 뒤에 있는 마감일을 넘기면 안되니까
                time = Math.min(time, works[i].t);
                time -= works[i].d;
            }

            sb.append(time).append("\n");
        }

        System.out.print(sb);
    }
}