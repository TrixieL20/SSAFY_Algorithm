import java.util.*;

class Solution {

  static int N;
  static int SCORERANGE = 11;

  static int[] apichi;
  static int[] lion = new int[SCORERANGE];

  // 최종res넣는 배열
  static int[] res = new int[SCORERANGE];

  static int maxDiff = Integer.MIN_VALUE;
  static boolean flag = false;

  static public void scoreCompare() {

    int apichiSc = 0;
    int lionSc = 0;

    for (int i = 0; i < SCORERANGE; i++) {

      if (apichi[i] == 0 && lion[i] == 0)
        continue;

      if (apichi[i] >= lion[i]) {
        apichiSc += 10 - i;
      } else
        lionSc += 10 - i;
    }

    int diff = lionSc - apichiSc;
    // 라이언이 이기면 (diff가 양수)
    if (diff > 0) {

      if (diff > maxDiff) { // 기존 차이보다 커질때 갱신
        maxDiff = diff;
        res = lion.clone();
        flag = true;
      }

      // 최대 차이 갱신
      if (maxDiff == diff) {// 차이가 같을 때
        maxDiff = diff;

        // 작은 인덱스가 더 많은 배열을 선택
        for (int i = 10; i >= 0; i--) {

          // 먼저 작은 점수가 많은게 잡히면 break
          if (lion[i] > res[i]) {
            res = lion.clone();
            break;
          } else if (lion[i] < res[i]) { // 작은 점수부터 탐색햇을떄 기존 답이 작은 점수가 더 많을때 break
            break;
          }
        }

        // 한 번이라도 이기면 flag = true
        flag = true;
      }
    }

  }

  // 라이언이 가질 수 있는 모든 조합 (중복조합 생성)
  static public void multicombination(int idx, int start) {

    if (idx == N) {
      // 화살 다 쐇으면 그 경우의 수 가지고 어피치랑 비교
      scoreCompare();
      return;
    }

    for (int i = start; i < SCORERANGE; i++) {
      lion[i] += 1;

      multicombination(idx + 1, i);
      lion[i] -= 1;

    }

  }

  public int[] solution(int n, int[] info) {

    apichi = info.clone();
    N = n;

    multicombination(0, 0);

    // 한 번이라도 못 이겼으면 [-1] 리턴
    if (flag == false) {
      return new int[] { -1 };
    }

    return res;
  }
}
