package Avatar;
import java.io.*;
import java.util.*;

/*
숫자가 문자열 형식으로 주어진다.
두 수중 작은 수가 기준이 되어 해당 길이의 꼭짓점을 생성한다.
예를 들어 기준이 3이면 3*3 크기로 설정 후 오른쪽으로 이동
오른쪽 끝까지 왔으면 아래 맨 왼쪽으로 이동
꼭짓점이 다 다르면 2*2크기로 다시 확인 이 과정을 재귀함수를 통해 구현
 */
public class NumberSquare {

    static String[] num;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        num = new String[n];

        for(int i = 0; i < n; i++){
            num[i] = br.readLine();
        }

        int flag = Math.min(n,m);//작은 수가 최대 길이의 정사각형 길이가 된다.
        System.out.println(search(flag));
    }

    public static int search(int length){
        if(length == 1){//길이가 1인 정사각형은 꼭짓점이 다 같기 때문에 1로 반환
            return 1;
        }else{
            int n_start = 0;//좌상
            int n_end = length - 1;//좌하
            int m_start = 0;//우상
            int m_end = length - 1;//우하

            while(n_end < num.length){
                char num1 = num[n_start].charAt(m_start);//왼쪽 위 꼭짓점
                char num2 = num[n_start].charAt(m_end);//왼쪽 아래 꼭짓점
                char num3 = num[n_end].charAt(m_start);//오른쪽 위 꼭짓점
                char num4 = num[n_end].charAt(m_end);//오른쪽 아래 꼭짓점

                if(num1 == num2 && num2 == num3 && num3 == num4){//다 똑같으면 해당 길이를 곱한 값을 반환
                    return length * length;
                }

                //그렇지 않을 경우
                //오른쪽 탐색 부분을 늘린다.
                m_start++;
                m_end++;

                if(m_end == num[0].length()){//오른쪽 끝까지 다 온경우 아래로 내려간다.
                    n_start++;
                    n_end++;
                    m_start = 0;
                    m_end = length - 1;
                }
            }
        }
        return search(length - 1);//해당길이의 정사각형이 꼭짓점이 같은게 없기 때문에 뺀 후 알아본다.
    }
}
