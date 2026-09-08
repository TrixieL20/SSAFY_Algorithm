import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        // 장르별 재생수 저장
        Map<String, Integer> genresCount = new HashMap<>();
        // 장르별 [포함 곡의 index, 재생수]를 리스트로 저장
        Map<String, List<int[]>> genresElements = new HashMap<>();
        
        // 곡 전체 순회
        for(int i = 0; i < genres.length; i++){
            // 장르별 재생수 누적 저장
            genresCount.put(genres[i], genresCount.getOrDefault(genres[i], 0) + plays[i]);

            // [곡 인덱스, 재생수]를 장르별로 저장
            // 아직 MAP에 없는 장르인 경우 리스트부터 만들고 추가
            genresElements.computeIfAbsent(genres[i], k -> new ArrayList<>())
                .add(new int[] {i, plays[i]});
        }

        // 장르를 계산한 전체 재생수 합대로 내림차순 정렬하기 위해 entry 만듦 -> sort
        List<Map.Entry<String, Integer>> genresEntry = new ArrayList<>(genresCount.entrySet());
        genresEntry.sort((o1, o2) -> o2.getValue() - o1.getValue());

        List<Integer> answerList = new ArrayList<>();

        // 내림차순 정렬된 장르 순으로 genresElements에 저장된 해당 장르의 곡들 접근
        // 장르에 속한 곡들의 list에서 재생수 순으로 나열 -> 재생수 같다면 index 작은 것(0번째 요소 오름차순), 아니라면 재생수 높은 것(1번째 요소 오름차순)대로 정렬
        for(Map.Entry<String, Integer> g : genresEntry){
            String genre = g.getKey();
            List<int[]> l = genresElements.get(genre);
            l.sort((o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o2[1] - o1[1]);
            // 정렬된 결과에서 장르에 속한 곡들이 2개 이하면 그만큼, 2개 이상이면 2개까지만 answer에 추가
            for(int i = 0; i < Math.min(2, l.size()); i++){
                answerList.add(l.get(i)[0]);
            }
        }
        answer = answerList.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}