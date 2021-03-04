import java.util.Scanner;

public class Decomposition {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int answer = 0;
		
		int num = 1;
		while(num < N) {
			int add_result = 0;
			String num_str = "" + num;
			
			for(int i = 0; i < num_str.length(); i++) {
				add_result += num_str.charAt(i) - '0';
			}
			
			add_result += num;
			
			if(add_result == N) {
				System.out.println(num);
				break;
			}else {
				num++;
			}
		}
		
		if(num == N) {
			System.out.println(0);
		}
	}

}
