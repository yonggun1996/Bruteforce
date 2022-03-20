import java.util.Scanner;

public class Bulk {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		
		int[] height = new int[n];
		int[] weight = new int[n];
		
		for(int i = 0; i < n; i++) {
			height[i] = in.nextInt();
			weight[i] = in.nextInt();
		}
		
		for(int i = 0; i < n; i++) {
			int rank = 1;
			
			for(int j = 0; j < n; j++) {
				if(i != j && height[i] < height[j] && weight[i] < weight[j]) {
					rank++;
				}
			}
			
			System.out.print(rank + " ");
		}
	}

}
