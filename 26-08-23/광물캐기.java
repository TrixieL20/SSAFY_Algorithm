import java.util.*;

class Solution {

  public int requiredEnergy(int picksIdx, List<Integer> minerals) {

    int total = 0;

    for (Integer mineral : minerals) {
      switch (picksIdx) {
        case 0:
          // 25은 diamond
          if (mineral == (25)) {
            total += 1;
            System.out.println(0 + "," + 0);
          }
          // 5는 iron
          else if (mineral == (5)) {
            total += 1;
            System.out.println(0 + "," + 1);
          } else {
            total += 1;
            System.out.println(0 + "," + 2);
          }

          break;
        case 1:
          if (mineral == (25)) {
            total += 5;
            System.out.println(1 + "," + 0);
          } else if (mineral == (5)) {
            total += 1;
            System.out.println(1 + "," + 1);
          } else {
            total += 1;
            System.out.println(1 + "," + 2);
          }
          break;
        case 2:
          if (mineral == (25)) {
            total += 25;
            System.out.println(2 + "," + 0);
          } else if (mineral == (5)) {
            total += 5;
            System.out.println(2 + "," + 1);
          } else {
            total += 1;
            System.out.println(2 + "," + 2);
          }
          break;

      }
    }
    System.out.println(total);
    return total;
  }

  public int mineralScore(String mineral) {

    if (mineral.equals("diamond")) {
      return 25;
    } else if (mineral.equals("iron")) {
      return 5;
    } else {
      return 1;
    }

  }

  public int solution(int[] picks, String[] minerals) {
    // 곡괭이 내구도
    int totalDurability = 0;
    // 피로도
    int usedEnergy = 0;
    // 광물 인덱스
    int mineralsIdx = 0;

    for (int i = 0; i < picks.length; i++) {
      for (int j = 0; j < picks[i]; j++) {
        totalDurability += 5;
      }
    }

    List<List<Integer>> diggableMinerals = new ArrayList<>();

    // 캘 수 있는 광물들 5개씩 묶음
    while (true) {
      List<Integer> tmpList = new ArrayList<>();

      for (int i = 0; i < 5; i++) {
        if (mineralsIdx >= minerals.length || mineralsIdx >= totalDurability)
          break;
        tmpList.add(mineralScore(minerals[mineralsIdx++]));
      }

      diggableMinerals.add(tmpList);

      if (mineralsIdx >= minerals.length || mineralsIdx >= totalDurability)
        break;

    }

    // 피로도 큰 그룹 순으로 오름차순
    diggableMinerals.sort((a, b) -> {
      int sumA = 0, sumB = 0;
      for (int x : a)
        sumA += x;
      for (int x : b)
        sumB += x;
      return Integer.compare(sumB, sumA);
    });


    // 곡괭이 인덱스
    int pickIdx = 0;
    
    for (int i = 0; i < diggableMinerals.size(); i++) {
      while (picks[pickIdx] == 0)
        pickIdx++;

      if (pickIdx >= picks.length)
        return usedEnergy;

      usedEnergy += requiredEnergy(pickIdx, diggableMinerals.get(i));
      picks[pickIdx]--;

    }

    return usedEnergy;
  }
}