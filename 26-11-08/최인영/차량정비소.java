import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Solution
{
    // 접수 창구 개수
  private static int n;
  // 수리 창구 개수
  private static int m;

  // 고객 처리 과정 중 분실 고객과 동일 reception desk 이용한 고객 list
  static List<Integer> targetRecept;
  // 접수 창구 -> 초기값 0 / 고객들 접수 끝나는 시각 저장
  private static int[] receptDesk;
  // 수리 창구 -> 초기값 0 / 고객들 수리 끝나는 시각 저장
  private static int[] repairDesk;
  // 창구별 접수 처리 시간
  private static int[] receptTime;
  // 창구별 정비 처리 시간
  private static int[] repairTime;
  // 손님 리스트
  private static List<Customer> customers;
  // 지갑 두고 간 손님과 같은 창구 이용한 손님 idx 저장
  private static List<Integer> targets;

  static class Customer {
    int idx;
    int receptDesk;
    int repairDesk;
    int visitTime;
    int repairTime;

    Customer(int visitTime){
      this.visitTime = visitTime;
    }
  }
  
  // 고객들 서비스 받은 과정 따라 가며 타겟과 모두 일치하는 창구 이용한 고객들 찾아 합산
  static void countTarget(int recept, int repair){
    for(Customer customer : customers){
      boolean isRecepted = false;
      for(int i = 0; i < n; i++){
        if(receptDesk[i] <= customer.visitTime){
          receptDesk[i] = customer.visitTime + receptTime[i];
          customer.repairDesk = i;
          customer.repairTime = receptDesk[i];
          isRecepted = true;
          break;
        }
      }
      if(isRecepted == false){
        int minIdx = 0;
        for(int i = 1; i < n; i++){
          if(receptDesk[minIdx] > receptDesk[i]){
            minIdx = i;
          }
        }
        receptDesk[minIdx] += receptTime[minIdx];
        customer.receptDesk = minIdx;
        customer.repairTime = receptDesk[minIdx];
      }
    }

    Collections.sort(customers, new Comparator<Customer>() {

      @Override
      public int compare(Solution.Customer arg0, Solution.Customer arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compare'");
      }
      
    });;
  }
	public static void main(String args[]) throws Exception
	{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuffer sb = new StringBuffer();
    int T = Integer.parseInt(br.readLine());

    for (int testCase = 1; testCase <= T; testCase++) {
      sb.append("#").append(testCase).append(" ");
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = st.nextToken().charAt(0) - '0';
      m = st.nextToken().charAt(0) - '0';
      int k = Integer.parseInt(st.nextToken());
      int a = st.nextToken().charAt(0) - '0'-1;
      int b = st.nextToken().charAt(0) - '0'-1;

      st = new StringTokenizer(br.readLine());
      receptTime = new int[n];
      repairTime = new int[m];
      customers = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        receptTime[i] = Integer.parseInt(st.nextToken());
      }
      for (int i = 0; i < m; i++) {
        repairTime[i] = Integer.parseInt(st.nextToken());
      }
      for (int i = 0; i < k; i++) {
        int visitTime = Integer.parseInt(st.nextToken());
        customers.add(new Customer(visitTime));
      }
      targetRecept = new ArrayList<>();
      receptDesk = new int[n];
      repairDesk = new int[m];
    }
	}
}