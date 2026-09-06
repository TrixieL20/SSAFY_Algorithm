import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int n = genres.length;

        Map<String, Long> totalPlays = new HashMap<>();       // 장르별 총 재생수
        Map<String, List<int[]>> genreSongs = new HashMap<>(); // 장르별 [재생수, 인덱스] 목록

        // 곡을 순회하며 장르별 총합/목록 구성
        for (int i = 0; i < n; i++) {
            String g = genres[i];
            totalPlays.put(g, totalPlays.getOrDefault(g, 0L) + plays[i]);
            genreSongs.computeIfAbsent(g, k -> new ArrayList<>()).add(new int[]{plays[i], i});
        }

        // 장르 순서 정렬: 총 재생수 내림차순
        List<String> genreOrder = new ArrayList<>(totalPlays.keySet());
        genreOrder.sort((a, b) -> Long.compare(totalPlays.get(b), totalPlays.get(a)));

        List<Integer> answer = new ArrayList<>();
        for (String g : genreOrder) {
            List<int[]> songs = genreSongs.get(g);

            // 장르 내 곡 정렬: 재생수 내림차순, 동점 시 인덱스 오름차순
            songs.sort((a, b) -> a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]);

            // 장르당 최대 2곡 채택
            int count = Math.min(2, songs.size());
            for (int i = 0; i < count; i++) answer.add(songs.get(i)[1]);
        }

        // 결과를 int[]로 변환
        int[] result = new int[answer.size()];
        for (int i = 0; i < result.length; i++) result[i] = answer.get(i);
        return result;
    }
}