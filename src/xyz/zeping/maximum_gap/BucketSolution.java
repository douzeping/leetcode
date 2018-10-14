package xyz.zeping.maximum_gap;


class Bucket {
    int min;
    int max;
    boolean hasNum;

    Bucket() {
        min = Integer.MIN_VALUE;
        max = Integer.MAX_VALUE;
        hasNum = false;
    }
}

public class BucketSolution {

    public static int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(nums[i], max);
            min = Math.min(nums[i], min);
        }
        if (max == min)
            return 0;
        Bucket[] buckets = new Bucket[nums.length + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Bucket();
        }
        int eleNums = nums.length;
        int gap = max - min;
        int bucketEleNum = gap / (eleNums + 1);
        for (int i = 0; i < nums.length; i++) {
            int bid = (nums[i] - min) / (bucketEleNum + 1);
            buckets[bid].min = buckets[bid].hasNum ? Math.min(buckets[bid].min, nums[i]) : nums[i];
            buckets[bid].max = buckets[bid].hasNum ? Math.max(buckets[bid].max, nums[i]) : nums[i];
            buckets[bid].hasNum = true;
        }
        int maxGap = Integer.MIN_VALUE;
        int lastMax = buckets[0].max;
        for (int i = 1; i < buckets.length; i++) {
            if (buckets[i].hasNum) {
                maxGap = Math.max(maxGap, buckets[i].min - lastMax);
                lastMax = buckets[i].max;
            }
        }
        return maxGap;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{3, 6, 9, 1};
        System.out.println(maximumGap(arr));
    }
}