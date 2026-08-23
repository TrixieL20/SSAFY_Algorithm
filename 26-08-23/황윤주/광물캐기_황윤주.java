import java.util.*;

class Solution {

    static class Group {
        int diamond;
        int iron;
        int stone;

        public Group(int d, int i, int s) {
            diamond = d;
            iron = i;
            stone = s;
        }
    }

    public int solution(int[] picks, String[] minerals) {

        List<Group> groups = new ArrayList<>();

        int totalPick =
                picks[0] + picks[1] + picks[2];

        int limit =
                Math.min(minerals.length,
                        totalPick * 5);

        for(int i=0; i<limit; i+=5){

            int d=0, ir=0, s=0;

            for(int j=i;
                j<Math.min(i+5, limit);
                j++){

                switch(minerals[j]){
                    case "diamond":
                        d++;
                        break;

                    case "iron":
                        ir++;
                        break;

                    case "stone":
                        s++;
                        break;
                }
            }

            groups.add(new Group(d, ir, s));
        }

        groups.sort((a,b)->{

            int scoreA =
                    a.diamond*25 +
                            a.iron*5 +
                            a.stone;

            int scoreB =
                    b.diamond*25 +
                            b.iron*5 +
                            b.stone;

            return scoreB - scoreA;
        });

        int answer = 0;
        int idx = 0;

        for(Group g : groups){

            if(picks[0] > 0){

                picks[0]--;

                answer +=
                        g.diamond +
                                g.iron +
                                g.stone;
            }

            else if(picks[1] > 0){

                picks[1]--;

                answer +=
                        g.diamond*5 +
                                g.iron +
                                g.stone;
            }

            else{

                picks[2]--;

                answer +=
                        g.diamond*25 +
                                g.iron*5 +
                                g.stone;
            }
        }

        return answer;
    }
}