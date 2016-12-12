public class NextPermutation {
    public void nextPermutation(int[] nums) {
        // input checking
        if (nums == null) return;

        // start form the 2nd to last item, going backwords, find the first descreasing item
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) { // use while instead of for loop, need to refer to i later
            i--;
        }

        // if i is not -1, we need to find the the one to be swapped
        if (i != -1) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) { // use while instead of for loop, need to refer to j later
                j--;
            }
            swap(nums, i, j);
        }

        // then reverse everything between i until the end
        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        for (int i = start, j = nums.length - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }
}