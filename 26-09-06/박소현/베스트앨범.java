import java.util.*;

class Solution {
    static final int MAX_GENRE = 100;
    public List solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        List<String> genreArr = new ArrayList<String>();
        int genreLength;
        int[][] playsByGenre = new int[MAX_GENRE][2];
        Map<Integer, List<int[]>> songByGenre = new HashMap<>();

        for(int i = 0; i < genres.length; i++)
        {
            int genreIdx = genreArr.indexOf(genres[i]);
            if(genreIdx == -1)
            {
                genreArr.add(genres[i]);
                genreIdx = genreArr.size() - 1;
                playsByGenre[genreIdx][0] = genreIdx;
            }
            playsByGenre[genreIdx][1] += plays[i];

            List<int[]> temp = songByGenre.getOrDefault(genreIdx, new ArrayList<int[]>());
            temp.add(new int[]{i, plays[i]});
            songByGenre.put(genreIdx, temp);
        }
        
        genreLength = genreArr.size();
        
        Arrays.sort(playsByGenre, 0, genreLength, (a, b) -> b[1] - a[1]);
        

        for(Map.Entry<Integer, List<int[]>> entry : songByGenre.entrySet())
        {
            List<int[]> songs = entry.getValue();
            Collections.sort(songs, (a, b)->b[1] - a[1]);
        }
        
        for(int i = 0; i < genreLength; i++)
        {
            int genre = playsByGenre[i][0];
            List<int[]> songs = songByGenre.get(genre);
            for(int j = 0; j < songs.size() && j < 2; j++)
            {
                answer.add(songs.get(j)[0]);
            }
        }
        return answer;
    }
}
