import java.util.Scanner;

public class Shom {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		
		double d = 101.0 / 6.0;
		int i = (int) Math.round(d);
		System.out.println(i);
		
		int count = 0;
		int num = 666;
		while(true) {
			
			String num_str = "" + num;
			
			if(num_str.contains("666")) {
				count++;
			}
			
			if(count == N) {
				System.out.println(num_str);
				break;
			}
			
			num++;
		}
		
	}

}
