package recursion;

/*
 * 递归机制的调用以及体会
 * 每当一个方法调用时就会在栈空间中开辟一个新的空间；
 */
public class RecursionTest {
	public static void main(String[] args) {
		test(4);
		int res = getNum(5);
		System.out.println("res="+res);
	}
	//输出问题：
	public static void test(int n) {
		if (n > 2) {
			test(n - 1);
		} else {
			System.out.println("n=" + n);
		}
	}
	//阶乘问题
	public static int getNum(int n) {
		if(n == 1) {
			return n;
		}
		else {
			return getNum(n-1) * n;
		}
	}
}