package Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
퇴사를 앞두고 상담을 하면서 가장 많은 비용을 받는 경우를 구하는 문제
이 문제는 날이 최대 15일로 주어지므로 완전탐색 알고리즘으로 해결 가능한 문제
하나의 날을 선택을 한 후 주어진 일수를 더해서 비용을 구한다.
비용도 n을 넘지 않으면 기존 누적됐던 비용을 더한다
단 n일을 넘으면 안된다. 그리고 딱 n일이 될 경우도 있으니 이 또한 처리를 해야 한다.
n일은 넘가나 딱 n일이 될 경우 maxpay를 최대값으로 갱신한다.
 */

public class No14501 {
    static int[][] arr;
    static int max_Pay = 0;//정답이 될 최대 급여
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n][2];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            arr[i][0] = t; arr[i][1] = p;
        }

        for(int i = 0; i < n; i++){
            if(i + arr[i][0] <= n){//하나의 상담을 하고 마지막 날을 넘기지 않으면 상담을 하는것으로 간주
                get_MaxPay(i + arr[i][0], arr[i][1]);
            }
        }

        System.out.println(max_Pay);
    }

    static void get_MaxPay(int idx, int pay){
        if(idx == arr.length){//퇴사하는 날까지 왔으면 maxpay를 갱신한다
            max_Pay = Math.max(max_Pay, pay);
            return;
        }

        for(int i = idx; i < arr.length; i++){
            if(i + arr[i][0] <= arr.length){//더 상담을 받을 수 있는 경우
                get_MaxPay(i + arr[i][0], pay + arr[i][1]);
            }else{//더 상담을 받을 수 없는 경우 maxpay를 갱신한다
                max_Pay = Math.max(max_Pay, pay);
            }
        }
    }
}
