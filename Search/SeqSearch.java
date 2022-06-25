package Search;

import java.util.ArrayList;
import java.util.List;

/*
 * 线性查找
 * 
 */
public class SeqSearch {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9,1,1,1};
		List<Integer> seqSearch = seqSearch(arr,1);
		if(seqSearch.size() == 0) {
			System.out.println("没有找到");
		}
		for(Integer a : seqSearch) {
			System.out.println("找到了，位置为"+a);
		}
		
	}
	public static List<Integer> seqSearch(int[] arr,int value){
		List<Integer> list = new ArrayList();
		for(int i = 0;i<arr.length;i++) {
			if(arr[i] == value) {
				list.add(i);
			}
		}
		return list;
	}

}
