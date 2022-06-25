package sort;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
 * 冒泡排序
 * 
 */
public class BubbleSort {
	public static void main(String[] args) {
//		int[] arr = { 3, 5, -1, 7, 2 };
//		System.out.println("测试前的数组");
//		System.out.println(Arrays.toString(arr));
		int[] arr = new int[80000];
		for(int i = 0;i < 80000;i++) {
			arr[i] = (int)(Math.random()*800000);
		}
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format1 = simpleDateFormat.format(date1);
		System.out.println("排序前的时间为："+format1);
		bubble(arr);
		Date date2 = new Date();
		String format2 = simpleDateFormat.format(date2);
		System.out.println("排序后的时间为："+format2);
//		System.out.println("测试后的数组");
//		System.out.println(Arrays.toString(arr));
	}

	// 将冒泡排序封装成一个方法；
	public static void bubble(int[] arr) {
		int temp = 0;
		boolean flag = false;
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					flag = true;
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
			if (!flag) {// 表示没有发生交换；
				break;
			} else {
				flag = false;
			}

		}
	}

}
