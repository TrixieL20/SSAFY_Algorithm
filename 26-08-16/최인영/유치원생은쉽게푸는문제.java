import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 유치원생은쉽게푸는문제
 * 숫자 0, 1, 2, 3, 4, 5, 6, 7, 8, 9에 있는 닫힌 공간의 수가 각각 1, 0, 0, 0, 1, 0, 1, 0, 2, 1개
 * 0 -> 1, 2, 3, 5, 7
 * 1 -> 0, 4, 6, 9
 * 2 -> 8
 */
class Solution{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int test_case = 0; test_case < T; test_case++){
            int num = Integer.parseInt(br.readLine());
            // 필요한 닫힌 공간 2개인 숫자의 수
            int count_2 = 0;
            // 최대한 작은 숫자 만들려면 자리수 줄여야 함!
            // -> 닫힌 공간 1개짜리 숫자 2개인 것보다 닫힌 공간 2개짜리 숫자 1개인 게 나음!
            // -> count_2를 최대로 만들기 위해 num을 2로 나눈 몫으로 설정
            count_2 += num / 2;
            // -> 나머지를 닫힌 공간 1개짜리로 채울 것!
            num %= 2; 
            
            // 닫힌 공간 2개인 숫자는 8밖에 없으므로 닫힌 공간 1개인 숫자 중 가장 작은 값(0 or 4)보다 큼
            // 따라서 닫힌 공간 1개인 숫자 -> 닫힌 공간 2개인 숫자 순으로 배치해야 ! 

            // 닫힌 공간 1개 숫자부터 sb에 append
            for(int i = 0; i < num; i++){
                // 만약 필요한 닫힌 공간 2인 숫자의 개수가 0인 경우 -> 닫힌 공간 1개만 필요한 것 !
                // 이 경우 자리 수가 1이므로 0도 가능 ! 
                if(count_2 == 0){
                    sb.append("0");
                }
                // 2 자리 이상인 경우 숫자가 0으로 시작하면 안 되므로 4 넣을 것 ! 
                else{
                    sb.append("4");
                }
            }

            // 닫힌 공간 2개인 숫자(8) 개수만큼 나열
            for(int i = 0; i < count_2; i++){
                sb.append("8");
            }
            
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    
}