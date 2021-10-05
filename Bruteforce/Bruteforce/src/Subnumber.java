package com.company;
import java.io.*;
import java.util.*;

/*
수가 0 ~ 9중에 주어지면 그 수를 그대로 출력
그 이상은 리스트의 값을 꺼내 문자열로 바꾼 후 0번째 문자보다 큰 값을 앞에 붙여 리스트에 담는다.
모든 경우의 수를 구한 후 정렬 해 n번째 값을 출력한다.
 */

public class Subnumber {

    static ArrayList<Long> list = new ArrayList<>();//감소하는 수를 모두 담은 리스트

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i <= 9; i++){//우선 0부터 9는 감소하는 수기 때문에 리스트에 담는다
            list.add((long)i);
        }

        if(n >= 10){//10 이상일 경우는 모든 경우의 수를 구한다
            int index = 0;//리스트의 인덱스를 구해 수를 문자열로 바꿔 0번째 문자를 토대로 그 문자보다 큰 수를 앞에 붙여 리스트에 붙인다
            while(index < list.size()){
                StringBuilder sb = new StringBuilder();
                sb.append(list.get(index));
                int num_char = sb.charAt(0) - '0';
                for(int i = num_char + 1; i <= 9; i++){
                    sb.insert(0,i);
                    list.add(Long.parseLong(sb.toString()));
                    sb.deleteCharAt(0);
                }
                index++;
            }

            Collections.sort(list);
        }

        if(n >= list.size()){//1023 이후의 값은 -1 출력
            System.out.println(-1);
        }else{//그 외는 리스트의 값을 출력한다
            System.out.println(list.get(n));
        }
    }
}
