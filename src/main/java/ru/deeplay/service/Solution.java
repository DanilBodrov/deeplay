package ru.deeplay.service;

import java.util.*;

public class Solution {

    public static int getResult(int[][] field) {
        int[][] matrix = new int[field.length * field.length][field.length * field.length];
        for (int[] row : matrix) {
            Arrays.fill(row, 1000);
        }

        int start = 0;
        int endGor = 1;
        int endVert = field.length;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (j + 1 <= field.length - 1) {
                    matrix[start][endGor] = field[i][j+1];
                    System.out.println("откуда " + start + " куда " + endGor + " число " + field[i][j+1]);
                }
                endGor++;
                if (i + 1 <= field.length - 1) {
                    matrix[start][endVert] = field[i+1][j];
                    System.out.println("откуда " + start + " куда " + endVert + " число " + field[i +1][j]);
                }
                endVert++;
                start++;
            }
        }

        int[][] arrayResult = new int[field.length * field.length][field.length * field.length];
        for (int[] row : arrayResult) {
            Arrays.fill(row, 1000);
        }
        Map<Integer, Integer> result = new HashMap<>();
        arrayResult[0][0] = 0;
        int now = 0;
        int index = 0;
        int a = 0;
        int minInArr = 1000;
        int minIndexInArr = 0;
        result.put(0, 0);

        while (!result.containsKey((field.length * field.length - 1))) {
            for (int i = 0; i < matrix[index].length; i++) {
                if (matrix[index][i] != 1000) {
                    int res = now + matrix[index][i];
                    if (res <= arrayResult[a][i] && !result.containsKey(i)) {
                        arrayResult[a+1][i] = res;
                    } else if (res > arrayResult[a][i]) {
                        arrayResult[a + 1][i] = arrayResult[a][i];
                        if (arrayResult[a][i] < minInArr) {
                            minInArr = arrayResult[a][i];
                            minIndexInArr = i;
                        }
                    }
                    if (res < minInArr && !result.containsKey(i)) {
                        minInArr = res;
                        minIndexInArr = i;
                    }
                } else {
                    if (index < (field.length * field.length - 1)) {
                        if (!result.containsKey(i)) {
                            arrayResult[a + 1][i] = arrayResult[a][i];
                            if (arrayResult[a][i] < minInArr) {
                                minInArr = arrayResult[a][i];
                                minIndexInArr = i;
                            }
                        }
                    }
                }
            }
            if (index != 0) {
                for (int i = 0; i < arrayResult[a + 1].length; i++) {
                    if (minInArr >= arrayResult[a + 1][i]) {
                        minInArr = arrayResult[a + 1][i];
                        index = i;
                        break;
                    }
                }
            } else {
                index = minIndexInArr;
            }

            result.put(index, minInArr);
            a++;
            now = minInArr;
            minInArr = 1000;
        }

        System.out.println("====Минимальные затраты на перемещение====");
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            System.out.println("От 0 до " + entry.getKey() + " = " + entry.getValue());
        }
        return result.get(15);
    }

    public static int[][] conversionToField(String field, String person) {
        int s = 1;
        int w = 1;
        int t = 1;
        int p = 1;

        switch (person.toLowerCase()) {
            case "human" -> {
                s = 5;
                w = 2;
                t = 3;
                p = 1;
            }
            case "swamper" -> {
                s = w = p = 2;
                t = 5;
            }
            case "woodman" -> {
                s = w = 3;
                t = p = 2;
            }
            default -> System.out.println("Раса персонажа не найдена, всё поле будет одинаковое, со стоимостью равное 1");
        }
        field = field.trim();
        String[] fields = field.split("");
        int[] arr = new int[fields.length];
        for (int i = 0; i < fields.length; i++) {
            switch (fields[i]) {
                case "s" -> arr[i] = s;
                case "w" -> arr[i] = w;
                case "t" -> arr[i] = t;
                case "p" -> arr[i] = p;
                default -> {
                    System.out.println("Неизвестный тип клетки " + i + ", стоимость будет равное 1");
                    arr[i] = 1;
                }
            }
        }
        int sqrt = (int) Math.sqrt(fields.length);

        int [][] matrix = new int[sqrt][sqrt];
        int j = 0;
        for (int x = 0; x < sqrt; x++) {
            for (int y = 0; y < sqrt; y++) {
                matrix[x][y] = arr[j];
                j++;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        String str = "sstpstwwtptwwstp";
        String person = "human";
        int[][] map = conversionToField(str, person);
        getResult(map);
    }
}
