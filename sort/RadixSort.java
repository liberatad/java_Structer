package sort;

import java.util.Arrays;

/*
 * 基数排序
 * 是一种以空间换时间的典型算法；速度非常快，比快排还要快，数据量过大时可能会造成内存不足；
 */
public class RadixSort {
	public static void main(String[] args) {
		int[] arr = { 10, 3, 55, 216, 78, 97, 123, 456, 78, 9, 1254 };
		radixSort(arr);
		System.out.println("arr=" + Arrays.toString(arr));

	}

	public static void radixSort(int[] arr) {
		// 开始创建10个桶，且定义成最大的长度；
		int[][] bucket = new int[10][arr.length];
		// 由于在取数据时不知道个数，所以先对这10个桶分别定义各自的指针，记录数量；
		int[] bucketElement = new int[10];
		// 根据规律推导出下面的代码；
		// 先求出最大值；
		int max = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		// 求出要循环的次数；
		int maxLength = (max + "").length();

		for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
			for (int j = 0; j < arr.length; j++) {
				// 依次取出每个基数
				int digitOfElement = arr[j] / n % 10;
				bucket[digitOfElement][bucketElement[digitOfElement]] = arr[j];
				// 每添加一个数，指针+1;
				bucketElement[digitOfElement]++;

			}
			// 开始对每个桶取数据；
			int index = 0;// 记录新的arr的索引；
			for (int l = 0; l < bucketElement.length; l++) {
				if (bucketElement[l] != 0) {// 表示第l个桶内有数据
					for (int k = 0; k < bucketElement[l]; k++) {
						arr[index++] = bucket[l][k];
					}
				}
				// 退出循环后需要将指针再次指向0；
				bucketElement[l] = 0;
			}
		}

//		for (int j = 0; j < arr.length; j++) {
//			// 取出每个元素的个位数的值
//			int digitOfElement = arr[j] % 10;
//			bucket[digitOfElement][bucketElement[digitOfElement]] = arr[j];
//			// 每添加一个数，指针+1;
//			bucketElement[digitOfElement]++;
//
//		}
//		// 开始对每个桶取数据；
//		int index = 0;// 记录新的arr的索引；
//		for (int l = 0; l < bucketElement.length; l++) {
//			if (bucketElement[l] != 0) {// 表示第l个桶内有数据
//				for (int k = 0; k < bucketElement[l]; k++) {
//					arr[index++] = bucket[l][k];
//				}
//			}
//		}
//		System.out.println("第一轮arr="+Arrays.toString(arr));
	}

}
