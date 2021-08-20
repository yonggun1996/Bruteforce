package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Tetromino {

    static int max = 0;
    static int[][] board;
    static ArrayList<boolean[][]> tetromino_list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        set_addlist();

        for(int i = 0; i < tetromino_list.size(); i++){
            search(tetromino_list.get(i));
        }

        System.out.println(max);
    }

    public static void search(boolean[][] tetro){
        int x_start = 0;
        int x_end = tetro.length;
        int y_start = 0;
        int y_end = tetro[0].length;

        while(x_end <= board.length){
            int sum = 0;

            int tetro_i = 0;
            for(int i = x_start; i < x_end; i++){
                int tetro_j = 0;
                for(int j = y_start; j < y_end; j++){
                    if(tetro[tetro_i][tetro_j]){
                        sum += board[i][j];
                    }
                    tetro_j++;
                }
                tetro_i++;
            }

            max = Math.max(max, sum);

            if(y_end == board[0].length){
                y_start = 0;
                y_end = tetro[0].length;
                x_start++;
                x_end++;
            }else{
                y_start++;
                y_end++;
            }
        }
    }

    public static void set_addlist(){
        boolean[][] t1 = {{true, true, true, true}};
        tetromino_list.add(t1);
        boolean[][] t2 = {{true},{true},{true},{true}};
        tetromino_list.add(t2);
        boolean[][] t3 = {{true, true},{true,true}};
        tetromino_list.add(t3);
        boolean[][] t4 = {{true, false},{true, false},{true, true}};
        tetromino_list.add(t4);
        boolean[][] t5 = {{true, true, true},{true, false, false}};
        tetromino_list.add(t5);
        boolean[][] t6 = {{true, true},{false, true},{false, true}};
        tetromino_list.add(t6);
        boolean[][] t7 = {{false, false, true},{true, true, true}};
        tetromino_list.add(t7);
        boolean[][] t8 = {{true, true},{true, false},{true, false}};
        tetromino_list.add(t8);
        boolean[][] t9 = {{true, true, true},{false, false, true}};
        tetromino_list.add(t9);
        boolean[][] t10 = {{false, true},{false, true},{true, true}};
        tetromino_list.add(t10);
        boolean[][] t11 = {{true, false, false},{true, true, true}};
        tetromino_list.add(t11);
        boolean[][] t12 = {{true, false},{true, true},{false, true}};
        tetromino_list.add(t12);
        boolean[][] t13 = {{false, true, true},{true, true, false}};
        tetromino_list.add(t13);
        boolean[][] t14 = {{false,true},{true,true},{true,false}};
        tetromino_list.add(t14);
        boolean[][] t15 = {{true, true, false},{false,true,true}};
        tetromino_list.add(t15);
        boolean[][] t16 = {{true, true, true},{false, true, false}};
        tetromino_list.add(t16);
        boolean[][] t17 = {{false, true},{true, true},{false, true}};
        tetromino_list.add(t17);
        boolean[][] t18 = {{false, true, false},{true, true, true}};
        tetromino_list.add(t18);
        boolean[][] t19 = {{true, false},{true, true},{true, false}};
        tetromino_list.add(t19);
    }
}
