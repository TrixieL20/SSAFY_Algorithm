import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, List<Integer>> map = new HashMap<>();
        Map<String, Integer> genreSum = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            // 장르에 따른 노래 리스트로 분류
            map.computeIfAbsent(genres[i], k -> new ArrayList<>()).add(i);

            // 장르별 재생 횟수
            genreSum.put(genres[i], genreSum.getOrDefault(genres[i], 0) + plays[i]);
        }

        // 장르별 재생 횟수에 따른 정렬
        List<String> sortedGenres = new ArrayList<>(genreSum.keySet());
        sortedGenres.sort((a, b) -> Integer.compare(genreSum.get(b), genreSum.get(a)));

        // 장르별 노래 정렬
        for (String genre : map.keySet()) {
            List<Integer> songs = map.get(genre);
            songs.sort((a, b) -> {
                if (plays[a] == plays[b]) {
                    return a - b; // 재생 횟수가 같을 경우 고유번호 오름차순
                }
                return plays[b] - plays[a]; // 재생 횟수에 따른 내림차순 정렬
            });
        }

        List<Integer> result = new ArrayList<>();

        // 각 장르에서 높은 우선순위를 가진 2개의 곡 선정
        for (String genre : sortedGenres) {
            List<Integer> songs = map.get(genre);

            for (int i = 0; i < Math.min(2, songs.size()); i++) {
                result.add(songs.get(i));
            }
        }

        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}