import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Solution{
  private static final int[] dirRow = {-1, 1, 0, 0};
  private static final int[] dirCol = {0, 0, -1, 1};
  private static int w;
  private static int h;
  private static int[][] currBricks;

  // 맨 위의 벽돌 중 하나에 구슬 쏘는 경우 simulation
  static void shoot(){
    for(int col = 0; col < w; col++){
      // 벽돌 남은 첫 행 찾기
      int firstRow = -1;
      for(int row = 0; row < h; h++){
        if(currBricks[row][col] == 0){
          continue;
        }
        firstRow = row;
        break;
      }

      // 해당 열에 남은 벽돌 없음 -> continue
      if(firstRow == -1){
        continue;
      }

      // 해당 열의 제일 위에 있는 벽돌 쏘는 경우
      ArrayDeque<int[]> queue = new ArrayDeque<>();
      queue.offer(new int[]{firstRow, col, bricks[firstRow][col]});

      while(!queue.isEmpty()){
        int[] curr = queue.poll();
        currBricks[curr[0]][curr[1]] = 0;
        if(curr[2] == 1){
          continue;
        }
        for(int i = 0; i < 4; i++){
          for(int dist = 1; dist < curr[2]; dist++){
            int nextRow = curr[0] + dirRow[i] * dist;
            int nextCol = curr[1] + dirCol[i] * dist;

            if(nextRow < 0 || nextCol < 0 || nextRow >= h || nextCol >= w){
              break;
            }

            if(currBricks[nextRow][nextCol] == 1){
              currBricks[nextRow][nextCol] = 0;
            }

            queue.offer(new int[]{nextRow, nextCol, currBricks[nextRow][nextCol]});
          }
        }

        // 벽돌 아래로 다 떨구기 
      }

    }
  }
  public static void main(String[] args) throws Exception{
    BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int testCase = 1; testCase <= T; testCase++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = st.nextToken().charAt(0) - '0';
      w = Integer.parseInt(st.nextToken());
      h = Integer.parseInt(st.nextToken());

      int[][] bricks = new int[h][w];

      for(int row = 0 ; row < h; row++){
        st = new StringTokenizer(br.readLine());
        for (int col = 0; col < w; col++) {
          bricks[row][col] = st.nextToken().charAt(0) - '0';
        }
      }

      currBricks = bricks.clone();
    }
  }
}