package sort;

import java.util.Arrays;

public class MergetSort {
	public static void main(String[] args) {
		int[] arr = {3,4,7,1,8,9,5,6,2};
		int[] temp = new int[arr.length];
		mergeSort(arr, 0, arr.length-1, temp);
		System.out.println("arr="+Arrays.toString(arr));

	}

	public static void mergeSort(int[] arr, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2;
			//想做传递分解；
			mergeSort(arr, left, mid, temp);
			//向右传递分解；
			mergeSort(arr, mid + 1, right, temp);
			//合并；
			merge(arr,left,mid,right,temp);
		}
	}

	/**
	 * @param arr   原始数组
	 * @param left  左边索引
	 * @param mid   中间索引
	 * @param right 右边索引
	 * @param temp  储存的数组
	 */
	public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int i = left;// 初始化i,i可能会移动
		int j = mid + 1;// 右边的初始索引
		int t = 0;// 临时数组的索引
		// 1.把左右有序数组按照规则放入到临时数组中，知道有一方放完
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				// 当前的arr[i] <= arr[j],则将arr[i]填充到temp中
				temp[t] = arr[i];
				t++;
				i++;
			} else {
				temp[t] = arr[j];
				t++;
				j++;
			}
		}

		// 2.将有剩余的一方一次填充到temp中；
		while (i <= mid) {
			temp[t] = arr[i];
			i += 1;
			t += 1;
		}
		while (j <= right) {
			temp[t] = arr[j];
			j += 1;
			t += 1;
		}
		// 3.将temp赋给arr;
		t = 0;
		int templeft = left;
		while (templeft <= right) {
			// 注意每次将temp填充到arr中的数组都不一样；
			// 依次是0,1 2,3 0,3 0,7;
			arr[templeft] = temp[t];
			templeft++;
			t++;
		}
	}

}
