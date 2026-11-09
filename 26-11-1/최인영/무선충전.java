import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
  static int A;
  static int aPosX, aPosY;
  static int bPosX, bPosY;
  static int maxCharge;
  private static List<int[]> chargers;

  // 좌표 이동 메소드
  static void move(int aIndex, int bIndex){
    int[] dirX = {0, 0, 1, 0, -1};
    int[] dirY = {0, -1, 0, 1, 0};

    aPosX += dirX[aIndex];
    aPosY += dirY[aIndex];
    bPosX += dirX[bIndex];
    bPosY += dirY[bIndex];
  }

  // 1초마다의 A, B 최대충전량 구하는 메소드
  static void charge(){
    // 충전기 별로 a, b가 충전 가능한지 여부 저장
    boolean[] aChargable = new boolean[A];
    boolean[] bChargable = new boolean[A];

    // 충전기마다 a, b 좌표 바탕으로 거리 구해 충전 범위에 있는지 확인
    for(int i = 0 ; i < chargers.size(); i++){
      int[] charger = chargers.get(i);
      int distA = Math.abs(charger[0] - aPosX) + Math.abs(charger[1] - aPosY);
      int distB = Math.abs(charger[0] - bPosX) + Math.abs(charger[1] - bPosY);
      
      if(distA <= charger[2]){
        aChargable[i] = true;
      }
      if(distB <= charger[2]){
        bChargable[i] = true;
      }
    }
    // 해당 초에 충전할 수 있는 양의 max
    int max = 0;
    // A, B 충전기 선택 모든 case마다 계산 후 최댓값 갱신
    for (int i = 0; i < A; i++) {
      for (int j = 0; j < A; j++) {
        int chargeAmount = 0;
        // 둘다 충전기 있는데 같은 충전기인 경우
        if(i == j && (aChargable[i] && bChargable[j])){
          chargeAmount = chargers.get(i)[3];
        }
        else{
          // A 충전 가능
          if(aChargable[i]){
            chargeAmount += chargers.get(i)[3];
          }
          // B 충전 가능
          if(bChargable[j]){
            chargeAmount += chargers.get(j)[3];
          }
        }
        max = Math.max(max, chargeAmount);
      }
    }
    maxCharge += max;
  }

	public static void main(String args[]) throws Exception
	{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuffer sb = new StringBuffer();
    int T = Integer.parseInt(br.readLine());
    

		for(int test_case = 1; test_case <= T; test_case++)
		{
      sb.append("#").append(test_case).append(" ");
      StringTokenizer st = new StringTokenizer(br.readLine());
      int M = Integer.parseInt(st.nextToken());
      A = Integer.parseInt(st.nextToken());

      int[] aRoute = new int[M+1];
      int[] bRoute = new int[M+1];

      // A, B의 이동 경로 입력
      st = new StringTokenizer(br.readLine());

      for(int i = 0; i < M; i++){
        aRoute[i] = st.nextToken().charAt(0) - '0';
      }

      st = new StringTokenizer(br.readLine());

      for(int i = 0; i < M; i++){
        bRoute[i] = st.nextToken().charAt(0) - '0';
      }

      // 충전소 정보 입력
      chargers = new ArrayList<>();
      for (int i = 0; i < A; i++) {
        int[] charger = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int j = 0; j < 4; j++){
          charger[j] = Integer.parseInt(st.nextToken());
        }
        chargers.add(charger);
      }
      
      // 시간마다 좌표 이동 -> 초마다의 최대 충전량 계산해 sum에 더하는 것 반복
      aPosX = 1; aPosY = 1;
      bPosX = 10; bPosY = 10;
      maxCharge = 0;
      // 0 초일 때 충전
      charge();
      for (int time = 0; time < M; time++) {
        move(aRoute[time], bRoute[time]);
        charge();
      }
      sb.append(maxCharge).append("\n");
		}
    System.out.println(sb.toString());
	}
}