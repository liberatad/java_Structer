package Search;

import java.util.Arrays;

/*
 * 插值查找算法，对于相对连续的数组，采用插值查找效率较高
 * mid = left+1/2 * (right - left)-->
 * mid = left+(findVal-arr[left])/(arr[right]-arr[left])*(right-left);
 */
public class InsertValueSearch {
	public static void main(String[] args) {
		int[] arr = new int[100];
		for (int i = 0; i < 100; i++) {
			arr[i] = i + 1;
		}
		System.out.println(Arrays.toString(arr));
		int insertValueSearch = insertValueSearch(arr, 0, arr.length-1, 7);
		System.out.println(insertValueSearch);
		
	}

	// 插值查找算法，要求数组是有序的；
	/**
	 * 
	 * @param arr     数组
	 * @param left    左边索引
	 * @param right   右边索引
	 * @param findVal 要查找的值；
	 * @return
	 */
	public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
		if(left > right || findVal < arr[0] || findVal > arr[arr.length-1]) {
			return -1;//防止因为findVal过大导致mid过大无法索引；
		}
		int mid = left + (findVal - arr[left]) / (arr[right] - arr[left]) * (right - left);
		int midVal = arr[mid];
		if (findVal > midVal) {// 向右索引；
			return insertValueSearch(arr, mid + 1, right, findVal);
		} else if (findVal < midVal) {// 想做索引
			return insertValueSearch(arr, left, mid - 1, findVal);
		} else {
			return mid;
		}
		
	}

}
