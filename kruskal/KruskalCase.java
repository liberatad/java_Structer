package kruskal;

import java.util.Arrays;

/**
 * 用克鲁斯卡尔算法解决最小生成树问题
 * 
 * @author Bad
 *
 */
public class KruskalCase {
	private int edgeNums;// 边的数目，
	private char[] vertexs;// 顶点集合；
	private int[][] matrix;// 邻接矩阵
	private static final int INF = Integer.MAX_VALUE;// 用INF表示两个顶点之间不能邻接

	public static void main(String[] args) {
		char[] vertexs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		int[][] matrix = { { 0, 12, INF, INF, INF, 16, 14 }, { 12, 0, 10, INF, INF, 7, INF },
				{ INF, 10, 0, 3, 5, 6, INF }, { INF, INF, 3, 0, 4, INF, INF }, { INF, INF, 5, 4, 0, 2, 8 },
				{ 16, 7, 6, INF, 2, 0, 9 }, { 14, INF, INF, INF, 8, 9, 0 }, };
		KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
		kruskalCase.print();
		kruskalCase.kruskal();
		// System.out.println(Arrays.toString(kruskalCase.getEdges()));
//		EData[] edges = kruskalCase.getEdges();
//		System.out.println("排序前："+Arrays.toString(edges));
//		kruskalCase.sortEdges(edges);
//		System.out.println("排序后："+Arrays.toString(edges));

	}

	public KruskalCase(char[] vertexs, int[][] matrix) {
		this.vertexs = new char[vertexs.length];
		for (int i = 0; i < vertexs.length; i++) {
			this.vertexs[i] = vertexs[i];
		}
		this.matrix = new int[matrix.length][matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				this.matrix[i][j] = matrix[i][j];
			}
		}
		// 统计边的数量
		for (int i = 0; i < vertexs.length; i++) {
			for (int j = i + 1; j < vertexs.length; j++) {
				if (this.matrix[i][j] != INF) {
					edgeNums++;
				}
			}
		}
	}

	/**
	 * 克鲁斯卡尔算法的实现
	 */
	public void kruskal() {
		int index = 0;// 表示最后结果数组的索引；
		int[] ends = new int[edgeNums];// 用于保存已有最小生成树的各个节点的终点下标
		// 创建结果数组，保存最后的边
		EData[] rets = new EData[edgeNums];
		// 获取图中所有边的集合
		EData[] edges = getEdges();
		sortEdges(edges);// 排序
		// 遍历edges数组，判断是否能加入到树中
		for (int i = 0; i < edgeNums; i++) {
			int p1 = getPosition(edges[i].start);// 每条边的起点
			int p2 = getPosition(edges[i].end);// 每条边的终点
			int m = getEnd(ends, p1);// 起点的最终点
			int n = getEnd(ends, p2);// 终点的最终点
			if (m != n) {// 说明可以加入到树中
				ends[m] = n;// 这里注意getEnd方法；
				rets[index++] = edges[i];// 将边加入到结果数组中；
			}
		}
		System.out.println("最小生成树为：" + Arrays.toString(rets));
	}

	public void print() {
		for (int i = 0; i < vertexs.length; i++) {
			for (int j = 0; j < vertexs.length; j++) {
				System.out.printf("%12d", matrix[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * 对边进行排序
	 * 
	 * @param edges
	 */
	private void sortEdges(EData[] edges) {
		for (int i = 0; i < edges.length - 1; i++) {
			for (int j = 0; j < edges.length - 1 - i; j++) {
				if (edges[j].weight > edges[j + 1].weight) {
					EData temp = edges[j];
					edges[j] = edges[j + 1];
					edges[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 返回顶点的下标
	 * 
	 * @param ch
	 * @return
	 */
	private int getPosition(char ch) {
		for (int i = 0; i < vertexs.length; i++) {
			if (vertexs[i] == ch) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 将边放入到一个数组中
	 * 
	 * @return
	 */
	private EData[] getEdges() {
		int index = 0;
		EData[] edges = new EData[edgeNums];
		for (int i = 0; i < vertexs.length; i++) {
			for (int j = i + 1; j < vertexs.length; j++) {
				if (matrix[i][j] != INF) {
					edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
				}
			}
		}
		return edges;
	}

	/**
	 * 
	 * @param ends :用于记录各个顶点的重点是哪一个，在添加边的过程中逐渐形成
	 * @param i    ：表示传入的顶点的下标
	 * @return 返回传入顶点的终点的下标
	 */
	private int getEnd(int[] ends, int i) {
		while (ends[i] != 0) {
			i = ends[i];
		}
		return i;
	}

}

//创建一个类代表1边的实例对象
class EData {
	char start;
	char end;
	int weight;

	public EData(char start, char end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "EData [start=" + start + ", end=" + end + ", weight=" + weight + "]";
	}

}
