import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static final int col = 100;
    static final int row = 100;
    static int[][] ladder;

    static int arrivalRow;
    static int arrivalCol;

    // 현 위치에서 양 옆으로 이동 가능한지 확인
    // col-1, col+1 위치 사다리 안에서 안 벗어났는지 / 값이 1인지 체크
    // int dir로 왼쪽 / 오른쪽 결정
    static boolean isTurn(int r, int c, int dir){
        c += dir;
        if(c < 0 || c >= col){
            return false;
        }
        if(ladder[r][c] == 1){
            return true;
        }
        return false;
    }
    // 해당 방향으로 이동할 수 있는 최종 위치
    static int findCol(int r, int c, int dir){
        while(true){
            if(ladder[r][c+dir] == 1){
                c += dir;
            }
            else{
                return c;
            }
            if(c <= 0 || c >= 99){
                return c;
            }
        }
    }

    // 도착지에서 시작해 출발지점 찾을 때까지 탐색
    static int followLadder(){
        int curRow = arrivalRow;
        int curCol = arrivalCol;
        
        // 현위치에서 출발해 좌우 확인 -> 이동할 수 있는 위치로 이동
        // 이동 불가인 경우 위로
        while(curRow > 0){
            int dir = 0;
            if(isTurn(curRow, curCol, -1)){ dir = -1; }
            if(isTurn(curRow, curCol, 1)){ dir = 1;}
            // 옆 이동 가능한 경우 갈 수 있는 범위만큼 이동
            if(dir != 0){
                curCol = findCol(curRow, curCol, dir);
            }
            // 직진 
            curRow--;
        }
        // row가 0일 때의 col(시작점 col) 반환
        return curCol;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int t = Integer.parseInt(br.readLine());
            sb.append("#").append(t).append(" ");
            ladder = new int[row][col];

            // 사다리 입력 및 도착 지점 row / col 찾기
            for (int r = 0; r < row; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int c = 0; c < col; c++){
                    int num = st.nextToken().charAt(0) - '0';
                    ladder[r][c] = num;
                    if(num == 2){
                        arrivalRow = r;
                        arrivalCol = c;
                    }
                }
            }

            sb.append(followLadder()).append("\n");
        }

        System.out.println(sb.toString());
    }
}