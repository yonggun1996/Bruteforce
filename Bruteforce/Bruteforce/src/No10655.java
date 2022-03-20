package Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No10655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n + 1][2];//입력받은 좌표를 담을 배열

        for(int i = 1; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        //현 포인트에서 다다음 포인트 까지
        //하나하나 거쳐서 가는 경우와 한 포인트를 건너 뛰고 가는 경우의 거리를 구한 후
        //두 수를 빼서 차이가 가장 큰 값에서 부터 한 칸을 건너 뛴 다음 칸으로 이동한다
        int max = 0;
        int idx = 0;
        for(int i = 1; i <= n - 2; i++){
            int dist1 = Math.abs(arr[i][0] - arr[i + 1][0]) + Math.abs(arr[i][1] - arr[i + 1][1]);//현 포인트에서 다음 포인트
            int dist2 = Math.abs(arr[i + 1][0] - arr[i + 2][0]) + Math.abs(arr[i + 1][1] - arr[i + 2][1]);//다음 포인트에서 다다음 포인트
            int dist3 = Math.abs(arr[i][0] - arr[i + 2][0]) + Math.abs(arr[i][1] - arr[i + 2][1]);//포인트를 건너 뛰는 경우
            int result = dist1 + dist2 - dist3;//두 경우의 수의 차이를 구한다
            if(max < result){//차이가 많이 나면 건너 뛸 부분을 갱신해준다
                max = result;
                idx = i;
            }
        }

        int answer = 0;
        //거리를 구하기 위한 두개의 포인터
        int start = 1;
        int end = 2;
        while(end <= n){
            if(start == idx) end++;//건너 뛰어야 할 포인트라면 end를 하나 더 늘려 하나의 포인트를 건너 뛴다
            answer += Math.abs(arr[start][0] - arr[end][0]) + Math.abs(arr[start][1] - arr[end][1]);
            start = end;//시작점은 도착점이 되고
            end++;//도착점은 현 시점에서 다음 포인터가 된다
        }

        System.out.println(answer);
    }
}
