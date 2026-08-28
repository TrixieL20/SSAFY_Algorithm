import java.util.*;

class Solution {

  // 인센티브 받을수있는 사람 리스트 get 메서드
  public List<int[]> getSurvivedList(List<int[]> scoresWithIdx) {
    // 오름차순 정렬
    Collections.sort(scoresWithIdx, (a, b) -> b[0] - a[0]);

    // 인센티브 받을 수 있는 리스트
    List<int[]> survived = new ArrayList<>();
    int maxB = -1;
    int n = scoresWithIdx.size();
    int i = 0;

    while (i < n) {
      // A가 같은 요소 시작 인덱스 i, 끝 인덱스 j
      int j = i;

      // 전체 인덱스를 안넘고, A가 같으면 끝 인덱스 j 증가
      while (j < n && scoresWithIdx.get(j)[0] == scoresWithIdx.get(i)[0]) j++;
      

      int groupMaxB = -1;

      // 같은 A를 가진 그룹안에서 순화
      for (int k = i; k < j; k++) {
        int[] p = scoresWithIdx.get(k);

        // 그룹 중에서 큰 B 저장
        if (p[1] >= maxB)
          survived.add(p); // 그룹 안에서는 오직 "이전 maxB"만 참조

        // 그룹MaxB 갱신
        groupMaxB = Math.max(groupMaxB, p[1]);
      }
      // 역대 maxB와 그룹maxB중 큰거 갱신
      maxB = Math.max(maxB, groupMaxB); // 그룹 끝난 뒤에만 반영
      i = j;
    }
    return survived;
  };


  // 완호 랭킹 리턴 메서드
  public int getRanking(List<int[]> survivedScoreList) {

    int answerIdx = 0;

    // int[] : {totalScore, 순위 default:0, originalIdx};
    List<int[]> totalScoreListWithIdx = new ArrayList<>();

    for (int[] l : survivedScoreList) {

      // {totalScore, 순위 default:0, originalIdx}
      int[] tmpAry = { l[0] + l[1], 0, l[2] };
      totalScoreListWithIdx.add(tmpAry);
    }

    // 내림차순 정렬
    Collections.sort(totalScoreListWithIdx, (a, b) -> {
      return b[0] - a[0];
    });


    // 초기 사람 정보, 인덱스 0인 요소 저장
    int[] prevInfo = totalScoreListWithIdx.get(0);
    int ranking = 1;
    
    // 내림차순 정렬했으니 맨 첫번쨰사람이 1등
    prevInfo[1] = ranking++;

    // 1등 이후 순회
    for (int i = 1; i < totalScoreListWithIdx.size(); i++) {

      // 이전 사람과 점수가 같으면 이전 사람 등수와 같음
      if (prevInfo[0] == totalScoreListWithIdx.get(i)[0]) {
        totalScoreListWithIdx.get(i)[1] = prevInfo[1];
        // 동일 순위면 다음 석차 밀려남
        ranking++;
      } else {
        totalScoreListWithIdx.get(i)[1] = ranking++;
        prevInfo = totalScoreListWithIdx.get(i);
      }

      // 원호 인덱스 나왔으면 break
      if (totalScoreListWithIdx.get(i)[2] == 0) {
        answerIdx = i;
        break;
      }
    }

    return totalScoreListWithIdx.get(answerIdx)[1];
  }

  public int solution(int[][] scores) {
    int answer = 0;

    // 원래 인덱스와 같이 저장
    List<int[]> scoresWithIdx = new ArrayList<>();

    int idx = 0;
    for (int[] scoreInfo : scores) {
      int[] tmpScore = { scoreInfo[0], scoreInfo[1], idx++ };
      scoresWithIdx.add(tmpScore);
    }

    // 인센티브 받을 수 있는 인원리스트
    List<int[]> survivedScoreList = getSurvivedList(scoresWithIdx);

    // 완호가 인센티브 받을 수 있는지 확인
    boolean isInWanho = false;
    for (int[] score : survivedScoreList) {
      if (score[2] == 0) {
        isInWanho = true;
        break;
      }
    }
    // 완호가 survivedScoreList에 없다면 -1 리턴
    if (!isInWanho)
      return -1;

    answer = getRanking(survivedScoreList);

    return answer;
  }
}