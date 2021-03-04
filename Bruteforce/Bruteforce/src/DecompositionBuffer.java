import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DecompositionBuffer {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int num = 1;
		while(num < N) {
			int add_result = 0;
			String num_str = "" + num;
			
			for(int i = 0; i < num_str.length(); i++) {
				add_result += num_str.charAt(i) - '0';
			}
			
			add_result += num;
			
			if(add_result == N) {
				bw.write(String.valueOf(num));
				break;
			}else {
				num++;
			}
		}
		
		if(num == N) {
			bw.write(String.valueOf(0));
		}
		
		bw.flush();
		bw.close();
	}

}
