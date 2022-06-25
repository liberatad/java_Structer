package tree;

import java.util.Arrays;

public class HeapSort {
	public static void main(String[] args) {
		int[] arr = { 4, 6, 8, 5, 9,10,-3,-6,16,98 };
		heapSort(arr);
	}

	public static void heapSort(int[] arr) {
		System.out.println("堆排序");
		int temp = 0;
		// 分布完成
//		adjust(arr,1,arr.length);//4,9,8,5,6
//		System.out.println("第一次调整为："+Arrays.toString(arr));
//		adjust(arr,0,arr.length);//调整0时，1的位置会根据循环进行调整
//		//9,6,8,5,4
//		System.out.println("第二次调整为："+Arrays.toString(arr));
		// 最终代码
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjust(arr, i, arr.length);
		}
		for (int j = arr.length - 1; j > 0; j--) {
			// 将数组首尾交换,此时首部最大；
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjust(arr, 0, j);// 此时调整的数目减少
		}
		System.out.println("数组=" + Arrays.toString(arr));//4,5,6,8,9
	}

	/**
	 * 将数组调整成一个大顶堆
	 * 
	 * @param arr需要调整的数组
	 * @param i          需要调整的非叶子节点的索引
	 * @param length     每次需要调整的数组元素个数，每次都在减少；
	 */
	public static void adjust(int[] arr, int i, int length) {
		int temp = arr[i];// 将当前元素取出作为临时变量
		for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {// 此时k指向左子节点
			// 这里之所以k = k*2+1,是因为调整上面的i时，后面的会改变，需要重新调整
			if (k + 1 < length && arr[k + 1] > arr[k]) {// 判断左右子节点的大小
				k++;
			}
			if (arr[k] > temp) {// 子节点比父节点大
				arr[i] = arr[k];// 将父节点与子节点交换
				i = k;// 将父节点指向子节点
			} else {
				break;
			}
		}
		// 当for循环结束时以i为父节点已经放在了最顶部（局部），此时i的位置也已经发生了变化
		arr[i] = temp;// 再次交换；
	}

}
