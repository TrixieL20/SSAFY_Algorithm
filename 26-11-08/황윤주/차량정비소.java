package sec02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;


public class Solution {
    private static int N, M, K, A, B;
    static int[] a1;
    static int[] b1;
    static int[] CArriveTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); // 접수 창구 개수
            M = Integer.parseInt(st.nextToken()); // 정비 창구 개수
            K = Integer.parseInt(st.nextToken()); // 고객의 수
            A = Integer.parseInt(st.nextToken()); // 지갑 두고간 고객의 접수 창구 번호
            B = Integer.parseInt(st.nextToken()); // 지갑 두고간 고객의 정비 창구 번호


            // 접수 창구가 고장 접수하는 데 걸리는 시간
            st = new StringTokenizer(br.readLine());
            a1 = new int[N];
            for(int i=0; i<N; i++){
                a1[i] = Integer.parseInt(st.nextToken());
            }

            // 정비 창구가 차량을 정비하는 데 걸리는 시간
            st = new StringTokenizer(br.readLine());
            b1 = new int[M];
            for(int i=0; i<M; i++){
                b1[i] = Integer.parseInt(st.nextToken());
            }

            // 고객이 도착한 시간
            CArriveTime = new int[K+1];
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=K; i++){
                CArriveTime[i] = Integer.parseInt(st.nextToken());
            }




            // 기다리는 고객 Queue로 관리 (먼저 들어온 사람부터 처리해야 하니까)
            Queue<Integer> receptionWait = new ArrayDeque<>();
            Queue<Integer> repairWait = new ArrayDeque<>();

            int[] reception = new int[N];    // 접수 창구에 있는 고객 번호
            int[] repair = new int[M];       // 정비 창구에 있는 고객 번호

            int[] receptionEnd = new int[N]; // 접수 종료 시간
            int[] repairEnd = new int[M];    // 정비 종료 시간


            int[] usedReception = new int[K+1]; // 고객별 이용한 창구 접수 번호

            int time = 0; // 총 시간
            int nextCustomer = 1; // 다음 고객
            int completeed = 0; // 완료한 고객
            int answer = 0;

            while(completeed < K){
                // 접수 완료 고객 처리
                for(int i=0; i<N; i++){
                    if(reception[i] !=0 && receptionEnd[i] == time){
                        int customer = reception[i];
                        repairWait.offer(customer);

                        // 접수 창구 비우기
                        reception[i] = 0;
                    }
                }

                // 정비 완료 고객 처리
                for(int i=0; i<M; i++){
                    if(repair[i]!=0 && repairEnd[i] == time){
                        repair[i] = 0;
                        completeed++;
                    }
                }

                // 고객 추가
                while(nextCustomer <= K && CArriveTime[nextCustomer] <= time){
                    receptionWait.offer(nextCustomer);
                    nextCustomer++;
                }

                // 빈 접수 창구에 고객 배정
                for(int i=0; i<N; i++){
                    if(reception[i] == 0 && !receptionWait.isEmpty()){
                        int customer = receptionWait.poll();

                        reception[i] = customer;
                        // 현재 시간 + 처리 시간
                        receptionEnd[i] = time + a1[i];

                        // 고객이 이용한 접수 창구 넘버
                        usedReception[customer] = i+1;
                    }

                }

                // 빈 정비 창구에 고객 배정
                for(int i=0; i<M; i++){
                    if(repair[i] == 0 && !repairWait.isEmpty()){
                        int customer = repairWait.poll();

                        repair[i] = customer;
                        repairEnd[i] = time + b1[i];

                        // 찾는 고객이 방문한 창구를 모두 이용한 경우
                        if(usedReception[customer] == A && i+1 == B){
                            // 고객 넘버 더하기
                            answer += customer;
                        }
                    }
                }


                time++;



            }

            // 조건에 맞는 고객 없으면 -1 리턴
            if(answer == 0){
                answer = -1;
            }

            System.out.println("#"+ tc+ " "+ answer);

        }
    }
}
