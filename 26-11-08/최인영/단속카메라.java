import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int solution(int[][] routes) {
      Arrays.sort(routes, new Comparator<int[]>() {
        @Override
        public int compare(int[] a, int[] b) {
          if(a[0] != b[0]){
            return a[0] - b[0];
          }
          else{
            return a[1] - b[1];
          }
        }
        
      });


      List<int[]>cameras = new ArrayList<>();
      cameras.add(routes[0]);
      for(int i = 1; i < routes.length; i++){
        boolean isExists = false;
        for(int j = 0 ; j < cameras.size(); j++){
          // 범위에 카메라 있는지 검사
          if(routes[i][1] < cameras.get(j)[0] || routes[i][0] > cameras.get(j)[1]){
            continue;
          }
          else{
            isExists = true;
            cameras.get(j)[0] = Math.max(cameras.get(j)[0], routes[i][0]);
            cameras.get(j)[1] = Math.min(cameras.get(j)[1], routes[i][1]);
            break;
          }
        }
        if(!isExists){
          cameras.add(routes[i]);
        }
      }
      return cameras.size();
    }
}