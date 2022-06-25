package recursion;
/*
 * 八皇后问题
 * 用一维数组实现；
 * arr[i] = value表示第i+1个皇后放在第i+1行第value+1列的位置；
 * 
 */
public class Queue8 {
	//定义一个变量说明皇后的数量；
	int max = 8;
	//定义一个数组记录皇后的摆放位置；
	int[] array = new int[max];
	static int count = 0;
	public static void main(String[] args) {
		Queue8 queue8 = new Queue8();
		queue8.check(0);
		System.out.printf("一共有%d种摆放的方法",count);
	}
	//定义一个方法打印数组；
	private void print() {
		count++;
		for(int i = 0;i<array.length;i++) {
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
	//查看当我们放置第n个皇后时，就去和前面的n-1个皇后是否冲突；
	/**
	 * 
	 * @param n 表示放置的第n+1个皇后
	 * @return 表示是否冲突；
	 */
	private boolean judge(int n) {
		//array[i] == array[n] 表示在同一列；
		//Math.abs(n-i)==Math.abs(array[n]-array[i])表示在同一斜线；
		//没有必要判断是否在同一行；
		for(int i = 0;i<n;i++) {
			if(array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] -array[i])) {
				return false;
			}
		}
		return true;
	}
	//编写写一个方法，放置第n个皇后；
	private void check(int n) {
		if(n == max) {//这时8个皇后已经放完；
			print();
			return;
		}
		//依次放入n个皇后，并判断是否冲突；
		for(int i = 0;i < max;i++) {
			array[n] = i;
			if(judge(n)) {
				//如果不冲突就放入第n+1个皇后；
				check(n+1);
			}
			//如果冲突；就会设置成check(n) = i+1;
			//注解：
			//当n=8时最顶层的栈就会出栈；由于下面的栈的for循环没有走完，这是下面一层的皇后的列就会发生变化
			//然后会再调用check()方法，这就是会产生回溯的原因；
			//直至前面的都摆放完会回到最底层的栈，这是同样由于最底层的栈的for循环没有走完，i会变成2，再重复原来的动作；
		}
	}

}
