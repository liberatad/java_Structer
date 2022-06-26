package dijkstra;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法之最短路径
 * @author Bad
 *
 */
public class DijkstraAlgorithm {
	public static void main(String[] args) {
		char[] vertex = {'A','B','C','D','E','F','G'};
		int[][] matrix = new int[vertex.length][vertex.length];
		final int N = 65535;
		matrix[0] = new int[] {N,5,7,N,N,N,2};
		matrix[1] = new int[] {5,N,N,9,N,N,3};
		matrix[2] = new int[] {7,N,N,N,8,N,N};
		matrix[3] = new int[] {N,9,N,N,N,4,N};
		matrix[4] = new int[] {N,N,8,N,N,5,4};
		matrix[5] = new int[] {N,N,N,4,5,N,6};
		matrix[6] = new int[] {2,3,N,N,4,6,N};
		Graph graph = new Graph(vertex, matrix);
		graph.showGraph();
		graph.dijkstra(4);
		graph.show();
	}

}
class Graph{
	private char[] vertex;
	private int[][] matrix;
	private VisitedVertex vv;
	public Graph(char[] vertex,int[][] matrix) {
		this.vertex = new char[vertex.length];
		this.matrix = new int[vertex.length][vertex.length];
		for(int i = 0;i < vertex.length;i++) {
			this.vertex[i] = vertex[i];
		}
		for(int i = 0;i < vertex.length;i++) {
			for(int j = 0;j < vertex.length;j++) {
				this.matrix[i][j] = matrix[i][j];
			}
		}
	}
	public void showGraph() {
		for(int[] link : matrix) {
			System.out.println(Arrays.toString(link));
		}
	}
	/**
	 * 迪杰斯特拉算法的实现
	 * @param index 表示出发顶点的下标
	 */
	public void dijkstra(int index) {
		vv = new VisitedVertex(vertex.length, index);
		update(index);
		for(int j = 1;j < vertex.length;j++) {
			index = vv.updateArr();
			update(index);//2 3 9 10 4 6 0 
		}
	}
	/**
	 * 更新index顶点到周围顶点的的距离以及周围顶点的前驱顶点
	 * @param index
	 */
	public void update(int index) {
		int len = 0;
		for(int j = 0;j < matrix[index].length;j++) {
			len = vv.getDis(index) + matrix[index][j];
			if(!vv.in(j) && len < vv.getDis(j)) {//说明这时需要更新
				vv.updateDis(j, len);//更新出发顶点到j下标的距离为len
				vv.updatePre(j, index);//更新j下标的顶点前驱顶点为index效标的顶点；
			}
		}
	}
	public void show() {
		vv.show();
	}
}
//已经访问顶点的集合
class VisitedVertex{
	public int[] already_arr;//表示已经访问过的顶点下标，1代表已经访问过
	public int[] pre_visited;//代表每个顶点的前一个顶点的下标，会动态更新
	public int[] dis;//出发顶点到其他顶点的距离，注意这里是出发顶点，也会动态更新
	public VisitedVertex(int length,int index) {//index指的是出发顶点的下标
		this.already_arr = new int[length];
		this.pre_visited = new int[length];
		this.dis = new int[length];
		Arrays.fill(dis, 65535);
		this.already_arr[index] = 1;
		this.dis[index] = 0;//出发顶点到自己的距离是零
	}
	public boolean in(int index) {//判断index下标的节点是否被访问过
		return already_arr[index] == 1;
	}
	/**
	 * 更新出发顶点到index顶点的距离
	 * @param index
	 * @param len
	 */
	public void updateDis(int index,int len) {
		dis[index] = len;
	}
	/**
	 * 更新pre的前驱节点为index节点
	 * @param pre
	 * @param index
	 */
	public void updatePre(int pre,int index) {
		pre_visited[pre] = index;
	}
	/**
	 * 获取出发顶点到index节点的距离
	 * @param index
	 * @return
	 */
	public int getDis(int index) {
		return dis[index];
	}
	/**
	 * 继续访问并选择新的节点，比如G节点访问过后选择A节点进行访问
	 * @return
	 */
	public int updateArr() {
		int min = 65535;
		int index = 0;
		for(int i = 0;i < already_arr.length;i++) {
			if(already_arr[i] == 0 && dis[i] < min) {
				min = dis[i];
				index = i;
			}
		}
		already_arr[index] = 1;
		return index;
	}
	public void show() {
		for(int i : already_arr) {
			System.out.print(i + " ");
		}
		System.out.println();
		for(int i : pre_visited) {
			System.out.print(i + " ");
		}
		System.out.println();
		for(int i : dis) {
			System.out.print(i + " ");
		}
	}
}
