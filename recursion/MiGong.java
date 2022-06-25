package recursion;

/*
 * 迷宫溯源问题；
 * 
 */
public class MiGong {
	public static void main(String[] args) {
		// 创建一个二维数组；
		int[][] map = new int[8][7];
		// 1代表墙；
		for (int i = 0; i < 7; i++) {
			map[0][i] = 1;
			map[7][i] = 1;
		}
		for (int i = 0; i < 8; i++) {
			map[i][0] = 1;
			map[i][6] = 1;
		}
		// 设置挡板；
		map[3][1] = 1;
		map[3][2] = 1;
		System.out.println("原始的地图为：");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		getWay(map,1,1);
		System.out.println("小球的路径");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	// 使用递归回溯给小球找路；
	// 当走到map[6][5]表示成功；
	// 约定：1表示墙，2表示通路，可以走，3表示走过但走不通；
	// 策略：下>右>上>左；
	/**
	 * 
	 * @param map 表示地图
	 * @param i   表示开始的位置
	 * @param j   表示开始的位置；
	 * @return 找到通路就返回true,否则返回false
	 */
	public static boolean getWay(int[][] map, int i, int j) {
		if (map[6][5] == 2) {// 表示找到路
			return true;
		} else {
			if (map[i][j] == 0) {// 如果当前的点还没有走过
				map[i][j] = 2;// 假设可以走通；在按照规则走
				if (getWay(map, i + 1, j)) {// 向下走
					return true;
				} else if (getWay(map, i, j + 1)) {// 向右走
					return true;
				} else if (getWay(map, i - 1, j)) {// 向下走
					return true;
				} else if (getWay(map, i, j - 1)) {// 向左走
					return true;
				} else {
					map[i][j] = 3;
					return false;
				}
			}
			else {//表示可能是1,2,3
				return false;
			}
		}

	}

}
