import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Solution{
  static int[] peopleStairs;
  private static List<int[]> people;
  private static int[][] stairs;
  private static int minTime;
  
  static void selectStairs(int idx){
    // 사람마다 어느 계단으로 내려갈지 결정
    if(idx == people.size()){
      int time = Math.max(moveDownStairs(0), moveDownStairs(1));
      minTime = Math.min(time, minTime);
      return;
    }
    // 1번 계단 선택하는 경우
    peopleStairs[idx] = 0;
    selectStairs(idx+1);

    // 2번 계단 선택하는 경우
    peopleStairs[idx] = 1;
    selectStairs(idx+1);
  }

  // 사람마다 계단 지정되면 계단마다 내려가는 시간 계산
  static int moveDownStairs(int stairIdx){
    List<int[]> movingPeople = new ArrayList<>();
    int[] stair = stairs[stairIdx];
    // 해당 계단 내려가는 사람들마다 도착시간 계산
    for(int i = 0; i < peopleStairs.length; i++){
      if(peopleStairs[i] == stairIdx){
        int[] peoplePos = people.get(i);
        int dist = Math.abs(peoplePos[0] - stair[0]) + Math.abs(peoplePos[1] - stair[1]);
        movingPeople.add(new int[]{peoplePos[0], peoplePos[1], dist});
      }
    }
    if(movingPeople.isEmpty()){
      return 0;
    }
    Collections.sort(movingPeople, new Comparator<int[]>(){

      @Override
      public int compare(int[] arg0, int[] arg1) {
        return arg0[2] - arg1[2];
      }

    });

    int[] finish = new int[movingPeople.size()];
    for (int i = 0; i < movingPeople.size(); i++) {
      int start = movingPeople.get(i)[2] + 1;
      if(i >= 3){
        start = Math.max(start, finish[i-3]);
      }
      finish[i] = start + stair[2];
    }

    return finish[finish.length-1];
  }
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
    int T = Integer.parseInt(br.readLine());
    StringBuffer sb = new StringBuffer();

    for (int testCase = 1; testCase <= T; testCase++) {
      sb.append("#").append(testCase).append(" ");
      int n = Integer.parseInt(br.readLine());

      int[][] grid = new int[n][n];
      people = new ArrayList<>();
      stairs = new int[2][3];
      int stairIdx = 0;
      for (int i = 0; i < n; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < n; j++) {
          grid[i][j] = Integer.parseInt(st.nextToken());
          if(grid[i][j] == 1){
            people.add(new int[]{i, j});
          }
          else if(grid[i][j] > 1){
            stairs[stairIdx++] = new int[]{i, j, grid[i][j]};
          }
        }
      }
      peopleStairs = new int[people.size()];
      minTime = Integer.MAX_VALUE;

      selectStairs(0);

      sb.append(minTime).append("\n");
    }
    System.out.println(sb.toString());
  }
}