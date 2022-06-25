package Search;

import java.util.ArrayList;
import java.util.List;

/*
 * 有序数组中的二分查找
 * 
 */
public class binarySearch {
	public static void main(String[] args) {
		int[] arr = {5,2,3,4,5,5,5,6,7,8,9};
//		int binarysearch = binarysearch(arr, 0, arr.length-1, 5);
//		System.out.println(binarysearch);
		List<Integer> binarysearch2 = binarysearch2(arr, 0, arr.length-1, 5);
		System.out.println(binarysearch2);
		

	}

	public static int binarysearch(int[] arr, int left, int right, int findVal) {
		//如果没有找到就返回-1;
		if(left > right) {
			return -1;
		}
		int mid = (left + right) / 2;
		int midVal = arr[mid];
		if (findVal > midVal) {// 向右递归
			return binarysearch(arr, mid + 1, right, findVal);
		} else if (findVal < midVal) {// 向左递归
			return binarysearch(arr, left, mid - 1, findVal);
		} else {
			return mid;
		}
	}
	//优化：若有多个相同的数
	public static List<Integer> binarysearch2(int[] arr,int left,int right,int findVal){
		if(left > right) {
			return new ArrayList<Integer>();
		}
		int mid = (left + right)/2;
		int midVal = arr[mid];
		if(findVal > midVal) {
			return binarysearch2(arr, mid+1, right, findVal);
		}
		else if(findVal < midVal) {
			return binarysearch2(arr, left, mid-1, findVal);
		}
		else {
			List<Integer> list = new ArrayList<Integer>();
			//向左扫描；
			int temp = mid-1;
			while(true) {
				if(temp < 0 || arr[temp] != arr[mid]) {
					break;
				}
				list.add(temp);
				temp--;
			}
			list.add(mid);
			temp = mid+1;
			//向右扫描；
			while(true) {
				if(temp > right || arr[temp] != arr[mid]) {
					break;
				}
				list.add(temp);
				temp++;
			}
			return list;
		}
	}

}
