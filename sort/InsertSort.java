package sort;

import java.util.Arrays;

public class InsertSort {
	public static void main(String[] args) {
		int[] arr = { 34, 70, 40, 5, 1 };
		insertSort(arr);
		System.out.println("排序后"+Arrays.toString(arr));
	}

	public static void insertSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int insertValue = arr[i];// 这里对数据进行了保存；
			int insertIndex = i - 1;
			while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
				arr[insertIndex + 1] = arr[insertIndex];
				insertIndex--;
			}
			if (insertIndex + 1 != i) {
				arr[insertIndex + 1] = insertValue;
			}
//			System.out.println("第"+(i)+"趟循环结果为");
//			System.out.println(Arrays.toString(arr));
		}
	}

}
