package Search;

import java.util.Arrays;

/*
 * 斐波那契查找算法
 * 利用F(k) = F(k-1) + F(k-2)->F(k)-1 = (F(k-1)-1) + (F(k-2)-1)+1;
 * 对于长度为F(k)-1的列表,由上式总是刻意将其分成两部分；
 * mid = low + F(k-1)-1
 */
public class FibonacciSearch {
	public static int maxsize = 20;

	public static void main(String[] args) {
		int[] arr = { 1, 8, 10, 45, 678, 1234 };
		int index = fibSearch(arr, 1234);
		System.out.println("index=" + index);

	}

	// 得到一个长度为20的斐波那契数列；
	public static int[] fib() {
		int[] f = new int[maxsize];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < maxsize; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f;
	}

	// 斐波那契查找算法；
	/**
	 * 
	 * @param a   原始数组
	 * @param key 需要查找的键值
	 * @return 返回要查找数的下标
	 */
	public static int fibSearch(int[] a, int key) {
		int low = 0;
		int high = a.length - 1;
		int k = 0;// 表示斐波那契分割数的下标；
		int mid = 0;// 存放mid
		int[] f = fib();// 得到的斐波那契数列；
		// 获取斐波那契分割值的下标；
		while (high > f[k] - 1) {
			k++;
		}
		// 由于f[k]的值可能大于high(即a的长度)，需要利用Arrays类创建一个新的数组并指向a[];
		int[] temp = Arrays.copyOf(a, f[k]);// f[k]指数组的长度，比a[]的长度大；
		// 由于默认temp数组high以后的部分使用0填充，这里需要改用a[high]填充；
		for (int i = high + 1; i < temp.length; i++) {
			temp[i] = a[high];
		}
		// 用while循环代替前面的递归
		while (low <= high) {
			mid = low + f[k - 1] - 1;
			if (key < temp[mid]) {// 注意这里使用的是temp数组；
				high = mid - 1;
				// 说明：由f[k] = f[k-1] + f[k-2]知mid的前面有f[k-1]-1个数
				// 我们应该在这f[k-1]个数当中取前面的f[k-1]个；
				// 此时high已经改变，mid应变为low+f[k-1-1]-1;
				k -= 1;
			} else if (key > temp[mid]) {
				low = mid + 1;
				// 同样mid的后面有f[k-2] 个数，我们应该在这f[k-2]个数中取前面的f[k-1]个；
				k -= 2;
			} else {// 表示已经找到；
				if (mid >= high) {
					return high;
				} else {
					return mid;
				}
			}
		}
		return -1;
	}

}
