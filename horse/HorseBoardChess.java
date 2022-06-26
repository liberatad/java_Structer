package horse;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 回溯算法之马踏棋盘问题,骑士周游世界
 * 
 * @author Bad
 *
 */
public class HorseBoardChess {
	public static int X;
	public static int Y;
	private static boolean[] visited;//标记期盼是否被访问过
	private static boolean finished;//如果为true表示成功；
	public static void main(String[] args) {
		System.out.println("骑士周游世界算法开始计算：");
		X = 10;
		Y = 10;
		int row = 1;
		int column = 1;
		int[][] chessBoard = new int[X][Y];
		visited = new boolean[X * Y];
		long start = System.currentTimeMillis();
		travelSalChessBoard(chessBoard, row + 3, column + 2, 1);
		long end = System.currentTimeMillis();
		System.out.println("花费的时间为："+(end - start)+"毫秒");
		for(int[] rows : chessBoard) {
			for(int step : rows) {
				System.out.print(step+"\t");
			}
			System.out.println();
		}

	}
	/**
	 * 
	 * @param chessBoard 棋盘
	 * @param row 代表在当前第几行
	 * @param column 代表在当前第几列
	 * @param step 代表走了第几步，初始化为1
	 */
	public static void travelSalChessBoard(int[][] chessBoard,int row,int column,int step) {
		chessBoard[row][column] = step;
		visited[row * X + column] = true;//标记已经走过
		//获取当前位置可以走的下一个位置的集合
		ArrayList<Point> ps = next(new Point(column,row));
		//对ps的所有的Point进行非递减排序
		sort(ps);
		while(!ps.isEmpty()) {//表示可以走
			Point p = ps.remove(0);//取出下一个走的位置
			if(!visited[p.y * X + p.x]) {//说明这一点还没有访问过
				//这里体现出了深度优先
				travelSalChessBoard(chessBoard,p.y,p.x,step+1);
			}
		}
		if(step < X * Y && !finished) {
			chessBoard[row][column] = 0;
			visited[row * X + column] = false;
		}else {
			finished = true;
		}
	}

	public static ArrayList<Point> next(Point curPoint) {
		ArrayList<Point> ps = new ArrayList<Point>();
		Point p1 = new Point();
		if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
			ps.add(new Point(p1));
		}
		if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
			ps.add(new Point(p1));
		}
		if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
			ps.add(new Point(p1));
		}
		if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
			ps.add(new Point(p1));
		}
		if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
			ps.add(new Point(p1));
		}
		if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
			ps.add(new Point(p1));
		}
		if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
			ps.add(new Point(p1));
		}
		if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
			ps.add(new Point(p1));
		}
		return ps;
	}
	//根据当前位置的下一步所有可选择的位置进行非递减排序
	/**
	 * 用贪心算法进行优化；
	 * @param ps
	 */
	public static void sort(ArrayList<Point> ps) {
		ps.sort(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				int count1 = next(o1).size();
				int count2 = next(o2).size();
				if(count1 < count2) {
					return -1;
				}else if(count1 == count2) {
					return 0;
				}else {
					return 1;
				}
			}

		});
	}

}
