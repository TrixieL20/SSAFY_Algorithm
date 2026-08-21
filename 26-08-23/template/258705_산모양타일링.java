class Solution {
    public int solution(int n, int[] tops) {
        int[] lowerMountain = new int[200004];
        lowerMountain[0] = 1;
        lowerMountain[1] = 1;

        for (int i = 2; i <= 2 * n + 1; i++) {
            if (i % 2 == 0 && tops[i / 2 - 1] == 1) {
                lowerMountain[i] = (lowerMountain[i - 2] + lowerMountain[i - 1] * 2) % 10007;
            }
            else {
                lowerMountain[i] = (lowerMountain[i - 2] + lowerMountain[i - 1])% 10007;
            }
        }

        return lowerMountain[2 * n + 1];
    }
}