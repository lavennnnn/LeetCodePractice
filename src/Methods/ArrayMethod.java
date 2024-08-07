package Methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayMethod {


    //283.移动零 暴力解法

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     *
     * 示例 1:
     *
     * 输入: nums = [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     *
     * 示例 2:
     *
     * 输入: nums = [0]
     * 输出: [0]
     * @param nums
     */
    public static void moveZeroes1(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    //283.移动零 用交换替代覆盖
    public static void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                 int temp = nums[i];
                 nums[i] = nums[j];
                 nums[j++] = temp;
            }
        }
    }

    //560. 和为 K 的子数组  hashmap + 前缀和
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        //关于mp.put(0, 1); 这一行的作用就是为了应对 nums[0] +nums[1] + ... + nums[i] == k 的情况的, 也就是从下标 0 累加到下标 i
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    //912.排序数组 冒泡排序
    public static int[] sortArray_BubbleSort(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                if (nums[j - 1] > nums[j]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                }
            }
        }
        return nums;
    }

    //912.排序数组 选择排序
    public static int[] sortArray_SelectionSort(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            int max = 0;
            for (int j = 0; j <= i; j++) {
                if (nums[j] > nums[max]) {
                    max = j;
                }
            }
            int temp = nums[i];
            nums[i] = nums[max];
            nums[max] = temp;
        }
    return nums;
    }

    //912.排序数组 插入排序
    public static int[] sortArray_InsertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int j = i;
            while (j > 0 && nums[j - 1] > nums[j]) {
                int temp = nums[j - 1];
                nums[j - 1] = nums[j];
                nums[j] = temp;
                j--;
            }
        }
        return nums;
    }

    //912.排序数组 归并排序
    public static int[] sortArray_MergeSort(int[] nums, int left, int right) {
        if (left >= right) return null;
        int mid = (left + right) / 2;

        sortArray_MergeSort(nums, left, mid);
        sortArray_MergeSort(nums, mid + 1, right);

        int i = left;
        int j = mid + 1;
        int cur = 0;
        int [] temp = new int[right - left + 1];
        while (i <= mid && j <= right) {
            if (nums[i] > nums[j]) temp[cur] = nums[j++];
            else temp[cur] = nums[i++];
            cur++;
        }
        while (i <= mid) temp[cur++] = nums[i++];
        while (j <= right) temp[cur++] = nums[j++];

        for (int k = 0; k < temp.length; k++) {
            nums[left + k] = temp[k];
        }
        return nums;
    }


    //912.排序数组 快速排序 使用分治
    public static void sortArray_QuickSort (int[] nums, int left, int right) {
        if (left >= right) return;
        int pivot = nums[left];
        int l = left;
        int r = right;
        while (l != r) {
            while (nums[r] >= pivot && r > l) r--;
            while (pivot >= nums[l] && l < r) l++;
            if (l < r) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
            }
        }
        nums[left] = nums[l];
        nums[l] = pivot;

        sortArray_QuickSort(nums, left, l - 1);
        sortArray_QuickSort(nums, l + 1, right);
    }

    //写一个函数，给定一个 List<Integer>，返回第一个不重复的 Integer。若都是重复的 Integer，则返回 NULL。

        public static Integer solution1(List<Integer> numbers) {
            if (numbers == null || numbers.isEmpty()) {
                return null;
            }

            Map<Integer, Integer> countMap = new HashMap<>();


            for (Integer number : numbers) {
                countMap.put(number, countMap.getOrDefault(number, 0) + 1);
            }

            for (Integer number : numbers) {
                if (countMap.get(number) == 1) {
                    return number;
                }
            }

            return null;
        }
    //写一个函数，给定一个 List<Integer> 和非负整数 K，
    //返回 List 中第一个 “与 List 中其他任意 Integer 的差值绝对值大于K” 的 Integer。若不存在，则返回NULL。

    public static Integer solution2 (List<Integer> numbers, Integer K) {
        for (int i = 0; i < numbers.size(); i++) {
            int count = 0;
            for (Integer number : numbers) {
                if (Math.abs(numbers.get(i) - number) > K) {
                    count++;
                }
            }
            if (count == (numbers.size() - 1)) {
                return numbers.get(i);
            }
        }

        return null;
    }


}
