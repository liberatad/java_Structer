package dac;

/**
 * 汉诺塔问题的求解
 * 
 * @author Bad
 *
 */
public class NanoiTowerDemo {
	public static void main(String[] args) {
		hanoiTower(6, 'A', 'B', 'C');
	}

	// 汉诺塔的移动方法
	// 使用分治算法
	/**
	 * 
	 * @param num 需要移动的塔的数量
	 * @param a   第一根柱子
	 * @param b   第二根柱子
	 * @param c   地三根柱子
	 */
	public static void hanoiTower(int num, char a, char b, char c) {
		if (num == 1) {
			System.out.println("第1个盘从 " + a + "->" + c);
		}else {
			//如果num >= 2，我们可以看成最下面的一个盘子和上面的所有的盘子
			//先将最上面的盘子从a->b,中间会使用到C
			hanoiTower(num-1, a, c, b);
			//把最下面的盘子移动a->c
			System.out.println("第"+num+"个盘从 "+a+"->"+c);
			//将b塔中的所有盘子从b->c
			hanoiTower(num-1, b, a, c);
		}
	}

}
