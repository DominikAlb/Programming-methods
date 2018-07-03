//Dominik Albiniak


import java.util.Scanner;
import java.util.Locale;
public class Source {
	static int[][] root;
	static String[] S;
	static int[][] tree;
	public static void main (String args[]) {
		Scanner in = new Scanner(System.in);
		int WHILE = in.nextInt();
		in.nextLine();
		for (int i = WHILE; i > 0; i--) {
			String first = in.nextLine();
			//String second = first.replaceAll("[^A-Za-z]", "");
			String third = first.replaceAll("[^ 0-9]", "");
			int count = 0;
			String number = "";
			int n = 0;
			float[] q = new float[0];
			S = new String[0];
			float[]p = new float[0];
			third += " ";
			for(char c: third.toCharArray()) {
				if (count < 2) {
					if(c != ' ') {
						number += c;
					} else {
						
						if (number.equals("")) continue;
						if (count == 0) {
							n = Integer.parseInt(number);
							q = new float[n + 1];
							S = new String[n];
							p = new float[n + 1];
							p[0] = 0;
							count++;
							number = "";
						}
						else {
							q[0] = Integer.parseInt(number);
							count++;
						}
					}
				} else {
					break;
				}
			}
			for (int j = 0; j < n; j++) {
				S[j] = in.next(); S[j] = S[j].replaceAll("[^A-Za-z]", "");
				p[j + 1] = in.nextInt(); q[j + 1] = in.nextInt();
				in.nextLine();
			}
			float D = (float)optimalSearchTree(q,p,n + 1);
			System.out.format(Locale.ENGLISH, "%.4f%n", D);
			
		}
	}
	static void printOBST(int i, int j, int k){
		if (i == j) return;
		
		printOBST(i, root[i][j] - 1, k + 1);
		for (int I = 0; I < 2*k + 1; I++) {
			System.out.print(".");
		}
		System.out.println(S[root[i][j]-1]);
		printOBST(root[i][j], j, k + 1);
	}
	static float optimalSearchTree(float q[], float p[], int n) {
		float cost[][] = new float[n + 1][n + 1];
		float[][] w = new float[n + 1][n + 1];
		root = new int[n + 1][n + 1];
	    for (int i = 0; i < n; i++) {
	        cost[i][i] = 0;
	        w[i][i] = q[i];
	        root[i][i] = 0;
	        for (int j = i + 1; j < n; j++) {
	        	w[i][j] = w[i][j - 1] + p[j] + q[j];
	        }   
	    }
	    for (int j = 1; j < n; j++) {
        	cost[j - 1][j] = w[j-1][j];
        	root[j - 1][j] = j;
        }
	    for (int d = 2; d < n; d++) {
	        for (int j = d; j < n; j++) {
	        	int i = j - d;
	        	float c = w[i][j];
	        	float minimal = Integer.MAX_VALUE;
	            for (int r = root[i][j - 1]; r <= root[i + 1][j]; r++) {
	            	float min = cost[i][r - 1] + cost[r][j];
	                if (min + c < minimal) {
	                    cost[i][j] = c + min;
	                    minimal = min + c;
	                    root[i][j] = r;
	                }
	            }
	        }
	    }
	    
	    
	    printOBST(0, n - 1, 0);
	    return cost[0][n - 1] / w[0][n - 1];
	}
}
