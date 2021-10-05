package com.company;
import java.io.*;
import java.util.*;

/*
현재 얻은 떡을 기준으로 반을 나눈 후 하나씩 가감하며 D번째 날에 처음으로 준 개수를 answer에 붙인다
두 숫자를 빼는데 뺀 숫자가 작은 숫자보다 작으면 더 뺄 수 없다
 */

public class Tiger {

    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int num1 = 0;
        int num2 = 0;

        if(k % 2 == 0){//짝수일 경우
            num1 = k / 2;
            num2 = k / 2;
        }else{//홀수일 경우
            num1 = k / 2;
            num2 = k / 2 + 1;
        }

        while(num1 >= 0 && num2 <= k){
            if(search(num1, num2, d, 1)){//d날 만큼 떡을 나눴는지 확인
                break;
            }else{//d번째 날까지 확인하지 못한 경우 num1은 빼고 num2는 더한다.
                num1--;
                num2++;
            }
        }

        System.out.println(answer);
    }

    public static boolean search(int num1, int num2, int d, int depth){
        if(d - 2 == depth){//해당 깊이까지 파고 들었으면 answer에 각 숫자를 붙여 true를 반환
            answer.append(num1).append("\n").append(num2);
            return true;
        }

        if(num2 - num1 > num1){//더는 뺄 값이 없을 경우 false를 반환
            return false;
        }

        return search(num2 - num1, num1, d, depth + 1);//재귀함수를 호출해 반환받은 값을 그대로 반환
    }

}
