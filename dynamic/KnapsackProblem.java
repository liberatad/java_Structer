package dynamic;

/**
 * 动态规划问题之背包问题；
 * 
 * @author Bad
 *
 */

public class KnapsackProblem {
	public static void main(String[] args) {
		int[] w = { 1, 4, 3 };// 物品的重量
		int[] val = { 1500, 3000, 2000 };// 物品的价值
		int m = 4;// 背包的容量
		int n = val.length;// 物品的个数
		int[][] v = new int[n + 1][m + 1];// 由于第一行和第一列初始化为零
		int[][] path = new int[n + 1][m + 1];// 用于记录哪些商品放到背包中
		// v[i][j] 代表装入前i个物品重量为j的物品价值最大值
		for (int i = 0; i < v.length; i++) {
			v[i][0] = 0;// 第一列为零
		}
		for (int i = 0; i < v[0].length; i++) {
			v[0][i] = 0;// 第一行初始化为零
		}
		// 动态规划处理背包问题
		for (int i = 1; i < v.length; i++) {// 注意：这两个循环都是从1开始的
			for (int j = 1; j < v[i].length; j++) {
				if (w[i - 1] > j) {// 由于i从1开始，与w数组对应，所以为w[i-1]
					v[i][j] = v[i - 1][j];
				} else {// 注意i,j都是从1开始的；
					// v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
					// 为了记录哪些商品放到背包中，用if-else语句改写上面的语句
					if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
						path[i][j] = 1;
						v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
					} else {
						v[i][j] = v[i - 1][j];
					}
				}
			}
		}
		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j < v[i].length; j++) {
				System.out.print(v[i][j] + "\t");
			}
			System.out.println();
		}
//		for(int i = 0;i < path.length;i++) {
//			for(int j = 0;j < path[i].length;j++) {
//				if(path[i][j] == 1) {
//					System.out.printf("第%d个商品放入到背包中\n",i);
//				}
//			}
//		}
		// 从后往前进行遍历
		int i = path.length - 1;// 行的最大下标
		int j = path[0].length - 1;// 列的最大下标
		while (i > 0 && j > 0) {
			if (path[i][j] == 1) {
				System.out.printf("第%d个商品放入到背包中\n", i);
				j -= w[i - 1];
			}
			i--;
		}
	}

}
