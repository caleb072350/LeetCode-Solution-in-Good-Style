public class Solution {

    public String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        if (sLen == 0 || tLen == 0 || sLen < tLen) {
            return "";
        }

        char[] charArrayS = s.toCharArray();
        char[] charArrayT = t.toCharArray();

        int[] tFreq = new int[128];
        int[] winFreq = new int[128];

        for (char c : charArrayT) {
            tFreq[c]++;
        }

        // 滑动窗口内部包含多少 T 中的字符，对应字符频数超过不重复计算
        int distance = 0;
        int minLen = sLen + 1;
        int begin = 0;

        int left = 0;
        int right = 0;
        // [left..right)
        while (right < sLen) {
            int charRight = charArrayS[right];
            if (tFreq[charRight] == 0) {
                right++;
                continue;
            }

            if (winFreq[charRight] < tFreq[charRight]) {
                distance++;
            }
            winFreq[charRight]++;
            right++;

            while (distance == tLen) {
                if (right - left < minLen) {
                    minLen = right - left;
                    begin = left;
                }

                int charLeft = charArrayS[left];
                if (tFreq[charLeft] == 0) {
                    left++;
                    continue;
                }

                if (winFreq[charLeft] == tFreq[charLeft]) {
                    distance--;
                }
                winFreq[charLeft]--;
                left++;
            }
        }

        if (minLen == sLen + 1) {
            return "";
        }
        return s.substring(begin, begin + minLen);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "DBADBECCODEBANCC";
        String t = "ABCC";
        String res = solution.minWindow(s, t);
        System.out.println("结果：" + res);

        System.out.println((int) 'a');
        System.out.println((int) 'A');
    }
}


