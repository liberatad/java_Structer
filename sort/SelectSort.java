package sort;

/*
 * 选择排序；
 * 
 */
public class SelectSort {
	public static void main(String[] args) {

	}

	public static void selectSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int min = arr[i];
			int minIndex = i;

			for (int j = i + 1; j < arr.length; j++) {
				if (min > arr[j]) {
					min = arr[j];
					minIndex = j;

				}
			}
			if(minIndex != i) {
				arr[minIndex] = arr[i];
				arr[i] = min;
			}
		}
	}

}
