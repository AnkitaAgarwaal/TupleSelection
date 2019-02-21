package com.tuple.main;

import javax.naming.spi.DirStateFactory.Result;

public class Application {

	static int[][][] result = new int[10000][][];

	public static void main(String[] args) {
		int[][] input = { { 1, 3 }, { 2, 5 }, { 5, 8 }, { 4, 9 }, { 1, 9 } };
		int startingSlot = findStartSlot(input);
		allSelections(startingSlot, input);
		
		for (int i = 0 ; i< result.length; i++) {
			int[][] temp = result[i];
			System.out.print("[(");
			for (int j = 0; j< temp.length; j++) {
				System.out.print(temp[j][0] + "," + temp[j][1]);
				if(j == temp.length-1) {
					System.out.print(")]");
				} else {
					System.out.print("),(");
				}
			}
			System.out.println();
		}
	}

	/**
	 * I am assuming that the smallest value of starting slots is my overall
	 * starting value
	 * 
	 * @param input
	 * @return minimum value of starting slot
	 */
	private static int findStartSlot(int[][] input) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < input.length; i++) {
			if (min > input[i][0]) {
				min = input[i][0];
			}
		}
		return min;
	}

	private static void allSelections(int startingSlot, int[][] input) {
		for (int i = 0; i < input.length; i++) {
			if (input[i][0] == startingSlot) {
				int[][] temp = new int[10000][];
				temp[0] = input[i];
				recurse(input, 1, input[i][1], temp);
			}
		}

	}

	private static void recurse(int[][] input, int currIndex, int endSlot, int[][] temp) {
		if (currIndex >= input.length) {
			result[result.length] = temp;
			return;
		}
		if (input[currIndex][0] > endSlot) {
			temp[temp.length] = input[currIndex];
		}
		for (int i = currIndex + 1; i < input.length; i++) {
			recurse(input, i, endSlot, temp);
		}
	}

}
