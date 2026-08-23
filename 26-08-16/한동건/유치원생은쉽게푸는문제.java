
import java.util.Scanner;
import java.io.FileInputStream;

class Solution {
  public static void main(String args[]) throws Exception {

    Scanner sc = new Scanner(System.in);
    int T;
    T = sc.nextInt();

    for (int test_case = 1; test_case <= T; test_case++) {
      int X = sc.nextInt();

      // 엣지케이스들 X=0,1
      if(X==0){
        System.out.println('1');
        continue;
      }
      if(X==1){
        System.out.println('0');
        continue;
      }

      // X가 짝수일때 8로만 배치하기
      if (X % 2 == 0) {
        for (int i = 0; i < X / 2; i++) {
          System.out.print('8');
        }

      // X가 홀수일때 닫힌 구간이 1인 수 중 가장 작은 수 4를 앞에 두고 8로 배치
      } else {
        System.out.print('4');
        for (int i = 0; i < (X - 1) / 2; i++) {
          System.out.print('8');
        }
      }
      System.out.println();

    }
    sc.close();

  }
}
