import java.util.*;

class Solution {

    static class Song {
        int index;
        int play;

        Song(int index, int play) {
            this.index = index;
            this.play = play;
        }
    }

    public int[] solution(String[] genres, int[] plays) {

        // 장르별 총 재생 수
        Map<String, Integer> totalPlay = new HashMap<>();

        // 장르별 노래 목록
        Map<String, List<Song>> songs = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {

            String genre = genres[i];

            // 장르 총 재생 수
            totalPlay.put(
                    genre,
                    totalPlay.getOrDefault(genre, 0) + plays[i]
            );

            // 해당 장르가 처음 등장했다면 리스트 생성
            songs.putIfAbsent(genre, new ArrayList<>());

            // 노래 저장
            songs.get(genre).add(new Song(i, plays[i]));
        }

        // 장르 목록
        List<String> genreList =
                new ArrayList<>(totalPlay.keySet());

        // 총 재생 수가 많은 장르부터
        genreList.sort((a, b) ->
                totalPlay.get(b) - totalPlay.get(a)
        );

        List<Integer> answer = new ArrayList<>();

        for (String genre : genreList) {

            List<Song> list = songs.get(genre);

            // 장르 내부 노래 정렬
            list.sort((a, b) -> {

                // 재생 수가 같으면 고유번호가 작은 것부터
                if (a.play == b.play) {
                    return a.index - b.index;
                }

                // 재생 수가 큰 것부터
                return b.play - a.play;
            });

            // 최대 2곡 선택
            answer.add(list.get(0).index);

            if (list.size() >= 2) {
                answer.add(list.get(1).index);
            }
        }

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}