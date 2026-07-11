class Solution {
    public int[] sortArray(int[] nums) {
        int minVal = nums[0], maxVal = nums[0];

        for (int num : nums) {
            if (num < minVal) minVal = num;
            if (num > maxVal) maxVal = num;
        }

        int[] frequency = new int[maxVal - minVal + 1];

        for (int num : nums) {
            frequency[num - minVal]++;
        }

        int index = 0;
        for (int value = 0; value < frequency.length; value++) {
            while (frequency[value]-- > 0) {
                nums[index++] = value + minVal;
            }
        }

        return nums;
    }
}