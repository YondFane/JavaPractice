package 算法排序.快速排序;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = {1, 32, 55, 232311, 55, 123123, 12312, 58785, 77, 89, 0, 660, 1223, 124312, -1, 123, -123, 556, 82, 8,
                1, 32, 55, 11, 51235, 123123, 12312, 4455, 77, 89, 80, 707, 123, -12312, -1, 1243, 123, 556, 8232, 1238, 3,
                1, 32, 5445, 1188, -55, 123123, -12312, 55, 77, 89, 909, 130, 123, 12312, -1, 1123, 123, 556, 82, 8, 387};
        int[] array2 = {1,0,0,1,1,1,-1,-1,0,1,-1,-1,0,0,0,-1};
        long start = System.currentTimeMillis();
        System.out.println("排序前：" + Arrays.toString(array2));
        quickSort(array2, 0, array2.length - 1);
        System.out.println("排序后：" + Arrays.toString(array2));
        long takeTime = System.currentTimeMillis() - start;
        System.out.println("用时：" + takeTime);
    }

    /**
     * 快速排序
     * @param nums
     * @param left
     * @param right
     */
    public static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int X = nums[left];
            while (i < j) {
                //从右往左找数组中小于X的元素
                while (i < j && nums[j] >= X) {
                    j--;
                }
                if (i < j) {
                    //nums[i]去除，num[j]形成下一个坑
                    nums[i] = nums[j];
                    i++;
                }
                //从左往右找数组中小于等于X的元素
                while (i < j && nums[i] < X) {
                    i++;
                }
                if (i < j) {
                    //nums[j]坑去除，nums[i]形成下一个坑
                    nums[j] = nums[i];
                    j--;
                }
            }
            //填坑
            nums[i] = X;
            //递归
            quickSort(nums, left, i - 1);
            quickSort(nums, i + 1, right);
        }

    }
}
