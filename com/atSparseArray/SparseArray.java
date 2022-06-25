package com.atSparseArray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

/*
 * 稀疏数组
 * 1.读取去原始数组，得到原始数组不为零的个数sum
 * 2.创建稀疏数组 int[sum+1][3];
 * 3.读取原始数组中的数据；
 * 
 */
public class SparseArray {
	public static void main(String[] args) throws IOException {
		//创建原始二维数组
		int[][] chessArray1 = new int[11][11];
		chessArray1[1][2] = 1;
		chessArray1[2][3] = 2;
		chessArray1[4][5] = 2;
		//输出二维数组
		System.out.println("原始二维数组");
		//增强for循环
		for(int[] array : chessArray1) {
			for(int data : array) {
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}
		//遍历
		int sum = 0;
		for(int i = 0;i<chessArray1.length;i++) {
			for(int j = 0;j<chessArray1[i].length;j++) {
				if(chessArray1[i][j] != 0) {
					sum++;
				}
			}
		}
		//创建稀疏数组
		int[][] sparseArr = new int[sum+1][3];
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = sum;
		//遍历二维数组，并存放到稀疏数组中；
		int count = 0;//用于记录稀疏数组的行；
		for(int i = 0;i<chessArray1.length;i++) {
			for(int j = 0;j<chessArray1[i].length;j++) {
				if(chessArray1[i][j] != 0) {
					count++;
					sparseArr[count][0] = i;
					sparseArr[count][1] = j;
					sparseArr[count][2] = chessArray1[i][j];
				}
			}
		}
		//输出稀疏数组
		System.out.println();
		System.out.println("得到的稀疏数组为");
		for(int i = 0;i<sparseArr.length;i++) {
			System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
		}
		System.out.println();
		//将稀疏数组转换为原始数组
		int[][] chessArray2 = new int[sparseArr[0][0]][sparseArr[0][1]];
		//遍历,复制
		for(int i = 1;i < sparseArr.length;i++) {
			chessArray2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}
		//遍历原始数组；
		System.out.println("转换的数组为");
		for(int[] array : chessArray2) {
			for(int data : array) {
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}
//		File file = new File("sparseArray.txt");
//		FileWriter fw = new FileWriter(file);
//		
//		for(int i = 0;i<sparseArr.length;i++) {
//			fw.write(sparseArr[i]);
		}
		
	}
	


