package com.company;

import java.util.*;
import java.io.*;

/*
도움이 된 블로그 : https://mapocodingpark.blogspot.com/2020/02/BOJ-1339.html
주어진 단어를 각 단위별로 맵에 저장
예를 들어 "ABC"의 경우 C는 1, B는 10, A는 100으로 저장
여러개의 문자열을 다음과 같이 계산
각 문자에 대한 번호를 큰 순서대로 정렬 후 가장 큰 순서에 따라 9보터 숫자를 단다
문자를 숫자로 바꾼 후 더하기 연산 실행
 */

public class WordMath {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Character, Integer> map = new HashMap<>();//각 문자가 나온 갯수를 숫자로 변환
        HashMap<Character, Integer> index_map = new HashMap<>();//각 문자를 숫자로 저장한 해시맵
        ArrayList<String> word_list = new ArrayList<>();//주어진 문자를 담는 리스트
        int n = Integer.parseInt(br.readLine());

        //각 문자에 대한 단위와 단위에 따라 나온 수를 더한 값을 담는 과정
        for(int i = 0; i < n; i++){
            String s = br.readLine();
            word_list.add(s);
            int unit = (int) Math.pow(10, s.length() - 1);
            for(int j = 0; j < s.length(); j++){
                if(!map.containsKey(s.charAt(j))){
                    map.put(s.charAt(j), unit);
                }else{
                    map.put(s.charAt(j), map.get(s.charAt(j)) + unit);
                }
                unit /= 10;
            }
        }

        //각 문자의 등장횟수를 리스트에 저장
        ArrayList<WordData> list = new ArrayList<>();
        for(char ch : map.keySet()){
            list.add(new WordData(ch, map.get(ch)));
        }

        //빈도수가 많은 문자가 먼저
        Collections.sort(list, new Comparator<WordData>(){
            @Override
            public int compare(WordData wd1, WordData wd2){
                return wd2.count - wd1.count;
            }
        });

        //빈도수가 많은 순서대로 높은 숫자를 담는다
        int count = 9;
        for(int i = 0; i < list.size(); i++){
            WordData wd = list.get(i);
            index_map.put(wd.ch, count);
            count--;
        }

        //각 문자를 숫자로 바꿔주는 과정
        int answer = 0;
        for(int i = 0; i < word_list.size(); i++){
            String str = word_list.get(i);
            StringBuilder number = new StringBuilder();
            for(int j = 0; j < str.length(); j++){
                number.append(index_map.get(str.charAt(j)));
            }
            answer += Integer.parseInt(number.toString());
        }

        System.out.println(answer);
    }
}

class WordData{
    char ch;
    int count;

    public WordData(char ch, int count){
        this.ch = ch;
        this.count = count;
    }
}
