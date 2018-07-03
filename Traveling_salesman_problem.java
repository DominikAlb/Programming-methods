//Dominik Albiniak

import java.util.Scanner;
import java.util.Stack;

public class Source {
	static int N;//System.out.println
	static char controlPath;
	static double[][] pre;
	static double[][] adj;
	static String[] nazwy;
	static boolean swapp;
	static int[] final_path;
	static double round (double value, int precision) {
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(value * scale) / scale;
	}
	
	static void sort(String[] S){
        int n = pre.length;
        for (int i=1; i<n; ++i)
        {
            double[] key = pre[i];
            String keyS = S[i];
            int j = i-1;
            while (j>=0 && pre[j][0] + pre[j][1]/60 <= key[0] + key[1]/60)
            {
                pre[j+1] = pre[j];
                S[j+1] = S[j];
                j = j-1;
            }
            pre[j+1] = key;
            S[j+1] = keyS;
        }
    }
	static double TSP() {
		final_path = new int[N + 1];
		int[] path = new int[N];
		double[][] dt = new double[N][N];
		int[][] b = new int[N][N];
		dt[0][1] = adj[0][1];
		dt[0][0] = 0;
		double temp, min;
		for (int i = 2;i < N; i++) {
			for(int j = 0; j < i; j++) {
				min = Double.MAX_VALUE;
				if (i <= j + 1) {
					for(int k = 0; k < j; k++) {
						temp = dt[k][j] + adj[k][i];
						if (temp < min) {
							min = temp;
							b[j][i] = k;
						} 
					}
					dt[j][i] = min;
				} else {
					dt[j][i] = dt[j][i - 1] + adj[i - 1][i];
					b[j][i] = i - 1;
				}
			}
		}
		Stack<Integer> s1 = new Stack<Integer>();
		Stack<Integer> s2 = new Stack<Integer>();
		int i = N-2;
		int k = 0;
		int j = N-1;
		while(j > 0){
			if(k == 0){
				s1.push(j);
			} else{
				s2.push(j);
			}
			j = b[i][j];
			if(j < i){
				int temp2 = i;
				i = j;
				j = temp2;
				if(k==0){
					k=1;
				} else{
					k=0;
				}
			}
		}
		s1.push(0);
		while(!s2.empty()){
			s1.push(s2.pop());
		}
		while(!s1.empty()) {
			path[i++] = s1.pop();
		}
		for(i = 0, j = 0; i < N; i++) {
			if (path[i] == 0) {
				while (i < N) {
					final_path[j] = path[i];
					i++;
					j++;
				}
			}
		}
		i = 0;
		while(path[i] != 0) {
			final_path[j++] = path[i];
			i++;
		}
		final_path[N] = 0;
		if (nazwy[final_path[1]].charAt(0) == controlPath) {
			
			for (i = 1; i < N/2; i++) {
				swap(final_path[i], final_path[N - i]);
			}
			swapp = true;
			
		}
		return dt[N-2][N-1] + adj[N - 1][N - 2];
	}
	
	private static void swap(int i, int j) {
		// TODO Auto-generated method stub
		int temp = i;
		i = j;
		j = temp;
	}

	static double ortodroma(double alfa0, double alfa1, double beta0, double beta1) {
		double a = Math.toRadians(alfa0);
		double b = Math.toRadians(beta0);
		double c = Math.toRadians(alfa1 - beta1);
		return Math.toDegrees((Math.acos(Math.sin(a)*Math.sin(b) + Math.cos(a)*Math.cos(b)*Math.cos(c))))*60;
	}
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int z = in.nextInt();
		in.nextLine();;
		for (int zz = 0; zz < z; zz++) {
			swapp = false;
			String line = in.nextLine();
            String temp = line.replaceAll("[^0-9]"," ");
            line = "";
            int j = 0;
            while (temp.charAt(j++) ==' ');
		j--;
            while (j < temp.length() && temp.charAt(j) != ' ') line += temp.charAt(j++);
            N = Integer.parseInt(line);
			nazwy = new String[N];
			pre = new double [N][N];
			int temp2;
			double[] tab;
			for (int i = 0 ; i < N; i++) {
				j = 0;
                line= in.nextLine();
                temp = line.replaceAll("[^A-Za-z]"," ");
                String tmp2 = line.replaceAll("[^0-9]"," ");
                line = "";
                while (temp.charAt(j++) == ' ');
			j--;
                while(j < temp.length() && temp.charAt(j)!=' ') line += temp.charAt(j++);
                nazwy[i] =line;
                tab = new double[4];;
                j = temp2 = 0;
                line = "";
                for (; temp2 != 4;line = "") {
                    while (tmp2.charAt(j++) == ' ');
			j--;
                    while(j < tmp2.length() && tmp2.charAt(j) != ' ') line += tmp2.charAt(j++);
                    tab[temp2++] = Integer.parseInt(line);
                }
                pre[i] = tab;
            }
			adj = new double[N][N];
			sort(nazwy);
			for (int i = 0; i < 1; i++) {
				for (j = 0; j < N; j++) {
					if (i == j) {
						adj[i][j] = 0;
					} else {
						adj[i][j] = (ortodroma(pre[i][0] + pre[i][1]/60, pre[i][2] + pre[i][3]/60, pre[j][0] + pre[j][1]/60, pre[j][2] + pre[j][3]/60));

					}
					System.out.printf("%02d",(int)pre[j][0]);
					System.out.print("*");
					System.out.printf("%02d",(int)pre[j][1]);
					System.out.print("'N ");
					System.out.printf("%02d",(int)pre[j][2]);
					System.out.print("*");
					System.out.printf("%02d",(int)pre[j][3]);
					System.out.print("'W");
					String a = Double.toString(round(adj[i][j], 1));
					System.out.printf("%7s",a);
					System.out.println(" -> " + nazwy[j]);
				}
			}
			controlPath = nazwy[1].charAt(0);
			for (int i = 1; i < N; i++) {
				for (j = 0; j < N; j++) {
					if (i == j) {
						adj[i][j] = 0;
					} else {
						adj[i][j] = (ortodroma(pre[i][0] + pre[i][1]/60, pre[i][2] + pre[i][3]/60, pre[j][0] + pre[j][1]/60, pre[j][2] + pre[j][3]/60));
					}
				}
			}
			double minimum = TSP();
			minimum = round(minimum, 1);
			System.out.print("dystans = " + minimum + " mil, droga = ");
			if(!swapp) {
				for (int i = 0; i <= N; i++) System.out.print(nazwy[final_path[i]].charAt(0));
			} else {
				for (int i = N; i >=0 ; i--) System.out.print(nazwy[final_path[i]].charAt(0));
			}System.out.println();
		}
	}
	
}
