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
            int count_2 = 0;
            count_2 += num / 2;
            num %= 2; 
            
            for(int i = 0; i < num; i++){
                if(count_2 == 0){
                    sb.append("0");
                }
                else{
                    sb.append("4");
                }
            }

            for(int i = 0; i < count_2; i++){
                sb.append("8");
            }
            
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    
}