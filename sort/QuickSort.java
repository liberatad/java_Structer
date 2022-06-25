package sort;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {
		int[] arr = { 1, -1, 0, 8, -2, 3 };
		quickSort(arr, 0, arr.length - 1);
		System.out.println("arr="+Arrays.toString(arr));
	}

	public static void quickSort(int[] arr, int left, int right) {
		int l = left;
		int r = right;
		int temp = 0;
		int pivot = arr[(l + r) / 2];
		while (l < r) {
			while (arr[l] < pivot) {
				l++;
			}
			while (arr[r] > pivot) {
				r--;
			}
			// 当退出循环时，需要交换；
			if (l >= r) {
				break;
			}
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
			// 此时的arr[l]与arr[r]已经发生里交换，但是由于，arr[l]<=pivot
			// 而且arr[r]>=pivot;在它们相等的节点上仍然需要继续交换，否则
			// 循环永远不能终止
			if (arr[l] == pivot) {
				l++;
			}
			if (arr[r] == pivot) {
				r--;
			}
		}
		if(l == r) {
			l++;
			r--;
		}
		if(left < r) {
			quickSort(arr, left, r);
		}
		if(right > l) {
			quickSort(arr, l, right);
		}
	}

}
