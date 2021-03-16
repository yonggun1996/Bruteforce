import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class ChessBoardBuffer {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		String[] chess_board = new String[m];
		
		for(int i = 0; i < m; i++) {
			chess_board[i] = br.readLine();
		}
		
		int width_start = 0;
		int height_start = 0;
		int width_finish = 8;
		int height_finish = 8;
		
		int min = 65;
		
		while(height_finish <= m) {
			String[] div_borad = new String[8];
			
			int index = 0;
			for(int i = height_start; i < height_finish; i++) {
				String board_color = chess_board[i];
				String colors = "";
				
				for(int j = width_start; j < width_finish; j++) {
					colors += board_color.charAt(j);
				}
				
				div_borad[index] = colors;
				index++;
			}
			
			int blackcount = black_count(div_borad);
			int whitecount = white_count(div_borad);
			
			if(blackcount < whitecount) {
				if(blackcount < min) {
					min = blackcount;
				}
			}else if(blackcount > whitecount){
				if(whitecount < min) {
					min = whitecount;
				}
			}else {
				if(whitecount < min) {
					min = whitecount;
				}
			}
			
			if(width_finish == n) {
				width_start = 0;
				width_finish = 8;
				height_start++;
				height_finish++;
			}else {
				width_start++;
				width_finish++;
			}
			
		}
		
		bw.write(String.valueOf(min));
		bw.flush();
		bw.close();
	}
	
	public static int black_count(String[] div_borad) {
		String[] black_start_chessboard = {"BWBWBWBW","WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW","WBWBWBWB"};
		int count = 0;
		
		for(int i = 0; i < 8; i++) {
			String str1 = black_start_chessboard[i];
			String str2 = div_borad[i];
			
			for(int j = 0; j < 8; j++) {
				if(str1.charAt(j) != str2.charAt(j)) {
					count++;
				}
			}
			
		}
		
		return count;
		
	}
	
	public static int white_count(String[] div_borad) {
		String[] black_start_chessboard = {"WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW","WBWBWBWB","BWBWBWBW"};
		int count = 0;
		
		for(int i = 0; i < 8; i++) {
			String str1 = black_start_chessboard[i];
			String str2 = div_borad[i];
			
			for(int j = 0; j < 8; j++) {
				if(str1.charAt(j) != str2.charAt(j)) {
					count++;
				}
			}
			
		}
		
		return count;
		
	}

}
