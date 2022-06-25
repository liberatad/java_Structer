package sort;

import java.util.Arrays;

public class ShellSort {
	public static void main(String[] args) {
		int[] arr = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		shellSort2(arr);
		System.out.println("排序后为：" + Arrays.toString(arr));
	}

	public static void shellSort(int[] arr) {
		int temp = 0;
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {
				for (int j = i - gap; j >= 0; j -= gap) {
					if (arr[j] > arr[j + gap]) {
						temp = arr[j];
						arr[j] = arr[j + gap];
						arr[j + gap] = temp;
					}
				}
			}
		}
	}

	// 对希尔排序进行优化；
	public static void shellSort2(int[] arr) {
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < arr.length; i++) {
				// 这是对分的组进行插入排序；
				int j = i;
				int temp = arr[j];

				if (arr[j] < arr[j - gap]) {
					while (j - gap >= 0 && temp < arr[j - gap]) {
						arr[j] = arr[j - gap];
						j -= gap;
					}
					arr[j] = temp;
				}
			}
		}
	}

}
