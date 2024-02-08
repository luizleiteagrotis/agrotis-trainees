
package exercicios.aula19;

import java.util.Scanner;

class Exercicio41{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		int[] A = new int[10];
		
		System.out.print("Vetor A: ");
		for(int i = 0; i< A.length; i++) {
			A[i] = (int) Math.round(Math.random() * 10);
			System.out.print(A[i] + " ");
		}
		
		System.out.println("\nVetor A ordenado: ");

		sort(A, 0, A.length - 1);
		
		for(int i = 0; i< A.length; i++) {
			System.out.print(A[i] + " ");
		}
	}

	private static void sort(int[] a, int e, int d) {
		
		
		if(e < d) {
			int m = e + (d - e) / 2;
			
			sort(a, e, m);
			sort(a, m+1, d);
			
			merge(a, e, m, d);
		}
		
	}

	private static void merge(int[] a, int e, int m, int d) {
		
        int n1 = m - e + 1;
        int n2 = d - m;
 
        int L[] = new int[n1];
        int R[] = new int[n2];
 
        for (int i = 0; i < n1; ++i)
            L[i] = a[e + i];
        for (int j = 0; j < n2; ++j)
            R[j] = a[m + 1 + j];
 
        int i = 0, j = 0;
 
        int k = e;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            }
            else {
                a[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            a[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            a[k] = R[j];
            j++;
            k++;
        }
	}
	
}
