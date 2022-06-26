package kmp;

import java.util.Arrays;

/**
 * kmp算法的应用
 * 
 * @author Bad
 *
 */
public class KMPAlgorithm {
	public static void main(String[] args) {
		int[] kmpNext = kmpNext("ABCDABD");
		System.out.println(Arrays.toString(kmpNext));

	}

	/**
	 * 
	 * @param str1 源字符串
	 * @param str2 待匹配的字符串
	 * @param next 部分值匹配表
	 * @return 返回索引值，若未找到匹配项，则返回-1
	 */
	public static int kmp(String str1, String str2, int[] next) {
		for (int i = 0, j = 0; i < str1.length(); i++) {
			while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
				j = next[j - 1];
			}
			if (str1.charAt(i) == str2.charAt(j)) {
				j++;
			}
			if (j == str2.length()) {
				return i - j + 1;
			}
		}
		return -1;
	}

	// 获取到一个字符串的部分值匹配表
	public static int[] kmpNext(String dest) {
		int[] next = new int[dest.length()];
		next[0] = 0;// 只有一个字符的字符串的前缀和后缀交集总为空
		for (int i = 1, j = 0; i < next.length; i++) {
			while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
				j = next[j - 1];// 回溯,kmp算法的核心部分；
			}
			if (dest.charAt(i) == dest.charAt(j)) {// 这里由前缀和后缀进行比较的意思
				j++;
			}
			next[i] = j;
		}
		return next;
	}

}
