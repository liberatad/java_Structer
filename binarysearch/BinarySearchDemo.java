package binarysearch;

public class BinarySearchDemo {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9};
		int binarySearch = binarySearch(arr, 7);
		System.out.println(binarySearch);
	}

	/**
	 * 
	 * @param arr    升序的数组
	 * @param target 待查找的目标
	 * @return 返回的查找到的下标，未找到则返回-1
	 */
	public static int binarySearch(int[] arr, int target) {
		int l = 0;
		int r = arr.length - 1;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (arr[mid] == target) {
				return mid;
			} else if (arr[mid] < target) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return -1;
	}

}
