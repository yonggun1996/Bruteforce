import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BlackJackBuffer {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] setting_card = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			setting_card[i] = Integer.parseInt(st.nextToken());
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
		
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

}
