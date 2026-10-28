import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 보호필름
 */
class Solution {

    static int[][] film;
    static int D;
    static int W;
    static int K;
    static int minCount;

    static void dfs(int rowIdx, int medicine){
        // 이미 중간의 약물사용개수가 최소 개수 넘었으면 끝내기
        if ( medicine >= minCount){
            return;
        }

        // 모든 행 DFS 완료
        if(rowIdx == D){
            // 검사 진행
            for (int col = 0; col < W; col++) {
                if(! checkCols(col)){
                    return;
                }
            }
            
            minCount = Math.min(minCount, medicine);
            return;
        }

        // dfs + 백트레킹 
         // 원본 배열 미리 저장
        int[] temp = film[rowIdx].clone();

        // 이번 행에 약물 투여 안 하는 경우
        dfs(rowIdx+1, medicine);

        // 이번 행에 약물 A 투여하는 경우
        // 배열 변경
        Arrays.fill(film[rowIdx], 0);
        dfs(rowIdx+1, medicine+1);

        // 이번 행에 약물 B 투여하는 경우
        // 배열 변경
        Arrays.fill(film[rowIdx], 1);
        dfs(rowIdx+1, medicine+1);

        // 원본으로 배열 되돌리기
        film[rowIdx] = temp;
    }

    // colIdx열에 대해 검사 진행
    static boolean checkCols(int colIdx){
        if (K == 1) {
            return true;
        }

        // 현재 위치의 특성(A, B)
        int curr = film[0][colIdx];
        int count = 1;

        for (int r = 1; r < D; r++) {
            // 다른 특성된 경우 curr 바꾸고 count 1로 초기화
            if(curr != film[r][colIdx]){
                curr = film[r][colIdx];
                count = 1;
            }

            // 같은 특성인 경우 count 1 추가
            else{
                count += 1;
            }
            
            // count K 되면 검사 통과이므로 true 반환
            if(count == K){
                return true;
            }

            // 만약 지금 count에서 뒤에 남은 칸수(r+1...row-1) 다 합쳐도 k개 미만인 경우 false
            if ((count + D - r -1) < K){
                return false;
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int T = Integer.parseInt(br.readLine());
        
        for (int testCase = 1; testCase <= T; testCase++) {

            sb.append("#").append(testCase).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken()); 
            W = Integer.parseInt(st.nextToken()); 
            K = Integer.parseInt(st.nextToken());
            minCount = Integer.MAX_VALUE;

            film = new int[D][W];

            for (int row = 0; row < D; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < W; col++) {
                    film[row][col] = st.nextToken().charAt(0) - '0'; 
                }
            }
            dfs(0, 0);
            sb.append(minCount).append("\n");
        }
        System.out.println(sb.toString());
    }
}