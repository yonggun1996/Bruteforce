package Avatar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No1189 {

    static int r; static int c; static int k;//입력받은 r,c,k
    static int answer = 0;//정답이 될 변수
    static String[] arr;//입력받은 도로를 표현한 배열
    
    //상하좌우를 확인하기 위한 좌표역할을 하는 배열
    static int[] x_Arr = {-1,1,0,0};
    static int[] y_Arr = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new String[r];

        for(int i = 0; i < r; i++){
            arr[i] = br.readLine();
        }

        boolean[][] visit = new boolean[r][c];
        search(r - 1, 0, 1, visit);
        System.out.println(answer);
    }

    static void search(int x, int y, int count, boolean[][] visit){
        if(x == 0 && y == c - 1 && count == k){//오른쪽 위로 도착했고 k번 만큼 갔으면 answer를 늘린다
            answer++;
        }else{
            visit[x][y] = true;//해당지역 방문
            for(int i = 0; i < 4; i++){
                int nx = x + x_Arr[i];
                int ny = y + y_Arr[i];

                if(nx >= 0 && nx < r && ny >= 0 && ny < c){//다음 구역이 배열을 벗어나지 않는다면
                    char c = arr[nx].charAt(ny);//갈 수 있는 지역인지 아닌지 파악하는 문자
                    if(c == '.' && !visit[nx][ny]){//방문 가능 구역일 경우
                        search(nx, ny, count + 1, visit);
                    }
                }
            }
            visit[x][y] = false;//해당지역을 다 살폈으면 방문하지 안았음으로 수정
        }
    }
}
