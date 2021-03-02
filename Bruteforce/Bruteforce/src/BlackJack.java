import java.util.Scanner;

public class BlackJack {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		int[] setting_card = new int[n];
		
		for(int i = 0; i < n; i++) {
			setting_card[i] = in.nextInt();
		}
		
		int card_sum = 0;
		int answer = 300000;
		
		for(int i = 0; i < n - 2; i++) {
			for(int j = i + 1; j < n - 1; j++) {
				for(int k = j + 1; k < n; k++) {
					card_sum = setting_card[i] + setting_card[j] + setting_card[k];
					
					if(card_sum <= m) {
						if(Math.abs(answer - m) > Math.abs(card_sum - m)) {
							answer = card_sum;
						}
					}
				}
			}
		}
		
		System.out.println(answer);
	}

}
