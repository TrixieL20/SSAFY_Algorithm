class Solution {
  public int minCount;
  public String[] words;
  public boolean[] isUsed;

  // 시작점에서 시작해 end와 같아질 때까지 가능한 변환 경로 전부 만들어 보는 method
  // curr 에서 갈 수 있는 단어(알파벳 하나 다름)로 변환 후 count + 1
  // -> 변환한 값이 end와 다르면 계속 변환
  // 만약 사용되지 않은 모든 단어들 중 curr과 알파벳 하나만 다른 게 없으면 더 진행할 수 없으므로 종료
  void findNext(String curr, String end, int count){
    if(curr.equals(end)){
      minCount = Math.min(count, minCount);
      return;
    }
    boolean canChange = false;
    for(int i = 0; i < words.length; i++){
      if(isUsed[i]){
        continue;
      }
      if(checkOneDiff(curr, words[i])){
        isUsed[i] = true;
        canChange = true;
        findNext(words[i], end, count+1);
        isUsed[i] = false;
      }
    }
    if(!canChange){
      return;
    }
  }

  // 알파벳 하나만 다른지 아닌지 판별
  boolean checkOneDiff(String s1, String s2){
    int diff = 0;

    for(int i = 0; i < s1.length(); i++){
      if(s1.charAt(i) != s2.charAt(i)){
        diff++;
      }
      if(diff > 1){
        break;
      }
    }

    if(diff == 1){
      return true;
    }
    return false;
  }

  public int solution(String begin, String target, String[] words) {
    minCount = Integer.MAX_VALUE;
    this.words = words;
    isUsed = new boolean[words.length];

    findNext(begin, target, 0);
    // 만약 begin => target 으로 변환 가능한 경우가 하나도 없어 minCount 갱신된 적이 없이 초기값 그대로면 0 반환
    if(minCount == Integer.MAX_VALUE){
      minCount = 0;
    }
    return minCount;
  }
}