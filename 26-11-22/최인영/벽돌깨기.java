import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Solution {
  private static final int[] dirRow = { -1, 1, 0, 0 };
  private static final int[] dirCol = { 0, 0, -1, 1 };
  private static int w;
  private static int h;
  private static int n;
  private static int[][] currBricks;
  private static int minCount;

  // dfs
  static void dfs(int idx){
    // N 번 다 쏘면 남은 벽돌 개수 세서 최소인지 구하기
    if(idx == n){
      int count = 0;
      for(int r = 0; r < h; r++){
        for(int c = 0; c < w; c++){
          if(currBricks[r][c] != 0){
            count++;
          }
        }
      }
      minCount = Math.min(count, minCount);
      return;
    }
    
    // 구슬 쏠 열 선택
    for(int col = 0; col < w; col++){
      // 이전 상태 깊은 복사
      int[][] backUp = new int[h][w];
      for(int r = 0; r < h; r++){
        backUp[r] = currBricks[r].clone();
      }

      shoot(col);
      dfs(idx + 1);

      // 벽돌 쏜 상태로 탐색 끝내면 이전 상태로 다시 돌리기(백트레킹)
      currBricks = backUp;
    }
  }

  // col 열에 맨 위의 벽돌에 구슬 쏘는 경우 simulation
  static void shoot(int col) {
    // 벽돌 남은 첫 행 찾기
    int firstRow = -1;
    for (int row = 0; row < h; row++) {
      if (currBricks[row][col] == 0) {
        continue;
      }
      firstRow = row;
      break;
    }

    // 해당 열에 남은 벽돌 없음 -> continue
    if (firstRow == -1) {
      return;
    }

    // 해당 열의 제일 위에 있는 벽돌 쏘았을 때 영향 받는 블록들 queue에 넣음
    // 방향마다 범위 내부 벽돌들 처리
    // 범위 1 이상인 벽돌들만 queue에 넣고, 없어진 벽돌들 0으로 바꿈
    ArrayDeque<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[] { firstRow, col, currBricks[firstRow][col] });
    currBricks[firstRow][col] = 0;

    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      currBricks[curr[0]][curr[1]] = 0;
      if (curr[2] == 1) {
        continue;
      }
      for (int i = 0; i < 4; i++) {
        for (int dist = 1; dist < curr[2]; dist++) {
          int nextRow = curr[0] + dirRow[i] * dist;
          int nextCol = curr[1] + dirCol[i] * dist;

          if (nextRow < 0 || nextCol < 0 || nextRow >= h || nextCol >= w) {
            break;
          }

          int power = currBricks[nextRow][nextCol];

          if (power == 0) {
            continue;
          }

          if (power == 1) {
            currBricks[nextRow][nextCol] = 0;
          }

          else if (power >= 2) {
            queue.offer(new int[] { nextRow, nextCol, currBricks[nextRow][nextCol] });
            currBricks[nextRow][nextCol] = 0;
          }

        }
      }
    }

    // 벽돌 아래로 다 떨구기(열별로 개수 세고 0인 것 삭제)
    for (int c = 0; c < w; c++) {
      int idx = h - 1;
      for (int r = h - 1; r >= 0; r--) {
        if (currBricks[r][c] != 0) {
          currBricks[idx][c] = currBricks[r][c];
          idx--;
        }
      }

      for (int r = idx; r >= 0; r--) {
        currBricks[r][c] = 0;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    StringBuffer sb = new StringBuffer();

    for (int testCase = 1; testCase <= T; testCase++) {
      sb.append("#").append(testCase).append(" ");

      StringTokenizer st = new StringTokenizer(br.readLine());
      n = st.nextToken().charAt(0) - '0';
      w = Integer.parseInt(st.nextToken());
      h = Integer.parseInt(st.nextToken());

      int[][] bricks = new int[h][w];

      for (int row = 0; row < h; row++) {
        st = new StringTokenizer(br.readLine());
        for (int col = 0; col < w; col++) {
          bricks[row][col] = st.nextToken().charAt(0) - '0';
        }
      }

      currBricks = bricks;
      minCount = Integer.MAX_VALUE;
      dfs(0);

      sb.append(minCount).append("\n");
    }

    System.out.println(sb.toString());
  }
}