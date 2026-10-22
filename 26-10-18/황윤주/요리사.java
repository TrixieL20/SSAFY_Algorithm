package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// swea 요리사
public class Solution3 {

    static int answer, N;
    static int[][] foods;
    static boolean[] selected;



    static void comb(int start, int depth) {
        if(depth == N/2) {
            // 조합 더하기
            sum();
            return;
        }

        // 조합으로 인덱스 뽑기
        for(int i=start; i<N; i++) {
            selected[i] = true; // 재료의 인덱스 저장
            comb(i+1, depth+1);
            selected[i] = false;
        }


    }

    static void sum() {
        int sumA = 0;
        int sumB = 0;

        for(int i=0; i<N; i++) {
            for(int j=i+1; j<N; j++) {

                if(selected[i]&&selected[j]) {
                    sumA += foods[i][j] + foods[j][i];
                }

                else if(!selected[i]&&!selected[j]) {
                    sumB += foods[i][j] + foods[j][i];
                }
            }
        }

        answer = Math.min(answer, Math.abs(sumA-sumB));
    }



    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {

            N = Integer.parseInt(br.readLine());

            foods = new int[N][N];
            StringTokenizer st;

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    // 음식 궁합
                    foods[i][j] = Integer.parseInt(st.nextToken());

                }
            }




            answer = Integer.MAX_VALUE;
            selected = new boolean[N];

            comb(0, 0);


            System.out.println("#"+tc+" "+answer);

        }


    }

}
