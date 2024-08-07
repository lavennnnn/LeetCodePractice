package Methods;

import java.util.*;

public class StringMethod {

    //49. 字母异位词分组

    /**
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     *字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
     *
     * 示例 1:
     *
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     *
     * 示例 2:
     *
     * 输入: strs = [""]
     * 输出: [[""]]
     *
     * 示例 3:
     *
     * 输入: strs = ["a"]
     * 输出: [["a"]]
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs){
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    //3.无重复字符的最长子串  滑动窗口1
    public int lengthOfLongestSubstring1(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int length = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = left; i < chars.length; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
            while (map.get(chars[i]) > 1) {
                map.put(chars[left], map.get(chars[left]) - 1);
                left++;
            }
            length = Math.max(length, i - left + 1);
        }
        return length;
    }

    //3.无重复字符的最长子串  滑动窗口2

    /**
     * 效率比上一个高，因为只用比较一次，不用循环
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        char[] chars = s.toCharArray();
        int left = -1;
        int length = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                left = Math.max(left, map.get(chars[i]));
            }
            map.put(chars[i], i);
            length = Math.max(length, i - left );
        }
        return length;
    }

    //128. 最长连续序列
    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> num_set = new HashSet<>();
        for (int num : nums) {
            num_set.add(num);
        }
        int longestStreak = 0;
        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }

        }
        return longestStreak;
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();

        int sLen = s.length();
        int pLen = p.length();

        if (sLen < pLen) return ans;

        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < sLen; i++) {
            sCount[s.charAt(i) - 'a']++;
            pCount[p.charAt(i) - 'a']++;
        }

        if (Arrays.equals(sCount, pCount)) {
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; i++) {
            sCount[s.charAt(i) - 'a']--;
            sCount[s.charAt(i + pLen) - 'a']++;

            if (Arrays.equals(sCount, pCount)) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
