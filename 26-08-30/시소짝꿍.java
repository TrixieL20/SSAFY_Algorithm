import java.util.*;

class Solution {

  // 균형이 맞는지 확인 메서드
  public boolean canBalance(int a, int b) {
    int[] ratio = { 2, 3, 4 };

    for (int r1 : ratio) {
      for (int r2 : ratio) {
        if (b * r1 == a * r2) {
          return true;
        }
      }
    }
    return false;
  }

  public long solution(int[] weights) {
    long answer = 0;

    Map<Integer, Integer> numCounter = new HashMap<>();

    // 같은 몸무게가진 사람 카운트
    for (int w : weights) {
      // getOrDefault(a,b) -> Map 자료구조에서 a키 값이 있으면 반환 없으면 b삽입
      numCounter.put(w, numCounter.getOrDefault(w, 0) + 1);
    }

    // set에 넣어서 중복 제거
    Set<Integer> set = new HashSet<>();
    for (int w : weights)
      set.add(w);

    // 중복 제거한 리스트
    List<Integer> list = new ArrayList<>(set);

    // 같은 무게들 쌍 계산 nC2
    for (int w : list) {
      if (numCounter.get(w) > 1) {
        answer += (long) numCounter.get(w) * (numCounter.get(w) - 1) / 2;
      }
    }

    for (int i = 0; i < list.size() - 1; i++) {
      for (int j = i + 1; j < list.size(); j++) {
        if (!(canBalance(list.get(i), list.get(j))))
          continue;

        // 같은 몸무게를 가진 요소들의 쌍 계산 / (100, 100, 100, 200, 200 ,200) 이 주어졌으면 만들어지는 (100,200)쌍은 3*3 = 9개
        answer += (long) numCounter.get(list.get(i)) * numCounter.get(list.get(j));
      }
    }

    return answer;
  }
}