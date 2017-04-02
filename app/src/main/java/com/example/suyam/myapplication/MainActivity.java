package com.example.suyam.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String str = "fkrg";
        str.toLowerCase();

        int l = "qwert yuiopd qwerty uiopd".length();
        char[] array = "qwert yuiopd qwerty uiopd      ".toCharArray();
        replaceSpace(array, l);
        /*Log.d("isUni(qwertyuiopd)", isPermuationPalindrome("Rervev") + "");
        Log.d("isUni(qwertyuiord)", isPermuationPalindrome("Rerev") + "");
        Log.d("isUni(qwertyuiord)", isPermuationPalindrome("Rerevb") + "");
        Log.d("isUni(qwertyuiopd)", isPermuationPalindrome("Rervvvv") + "");*/

        Log.d("isUni", isOneStepAway("plae", "ple") + "");
        Log.d("isUni", isOneStepAway("plaes", "ple") + "");
        Log.d("isUni", isOneStepAway("plaes", "plaesr") + "");
        Log.d("isUni", isOneStepAway("plae", "blae") + "");
        Log.d("isUni", isOneStepAway("plae", "bae") + "");
        Log.d("isUni", isOneStepAway("plae", "plvkdjvpdke") + "");

        Log.d("isUni", compress("aaabwwccccc"));
        Log.d("isUni", compress("acbnmjkiy"));

        Log.d("isUni", isRoation("waterbottele", "ttelewaterbo") + "");

        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        rotateMatrix(matrix);
    }

    void rotateMatrix(int[][] matrix) {

        if (matrix.length == 0) {
            return;
        }

        printMatrix(matrix);

        for (int layer = 0; layer < matrix.length / 2; layer++) {

            int last = matrix.length - 1 - layer;

            Log.d("isUni","Swaping start for layer "+ layer);
            for (int index = layer; index < last; index++) {
                Log.d("isUni","Swaping inside  "+ index+" "+layer +"to top");
                int top = matrix[index][layer];

                Log.d("isUni","Swaping "+last+" "+ index +"t0  "+ index+" "+layer);
                matrix[index][layer] = matrix[last][index];

                Log.d("isUni","Swaping "+(last - index)+" "+(last)+"to "+last+" "+ index );
                matrix[last][index] = matrix[last-index][last];

                Log.d("isUni","Swaping "+ layer+" "+(last-index) + "to "+(last - index)+" "+(last));
                matrix[last - index][last] = matrix[layer][last-index];

                Log.d("isUni","top to "+(layer)+" "+(last - index));

                matrix[layer][last-index] = top;
            }
        }
        printMatrix(matrix);
    }

    void printMatrix(int[][] matrix) {
        Log.d("isUni"," Start");
        for (int iIndex = 0; iIndex < matrix.length; iIndex++) {
            for (int jIndex = 0; jIndex < matrix[iIndex].length; jIndex++) {
                Log.d("isUni", matrix[iIndex][jIndex] + "");
            }
        }
    }

    boolean isRoation(String value1, String value2) {
        if (value1.isEmpty() || value2.isEmpty() || value1.length() != value2.length()) {
            return false;
        }

        String value = value1 + value1;

        return value.contains(value2);
    }

    String compress(String value) {
        if (value.isEmpty()) {
            throw new RuntimeException("Value cannot be Empty");
        }

        StringBuilder compressedString = new StringBuilder();
        int compressedLength = 0;

        for (int index = 0; index < value.length(); index++) {
            compressedLength++;

            if (index + 1 == value.length() || value.charAt(index) != value.charAt(index + 1)) {
                compressedString.append(value.charAt(index));
                compressedString.append(compressedLength);

                compressedLength = 0;
            }
        }

        Log.d("isUni", "Compressed " + compressedString.toString() + " Orginal " + value);
        return compressedString.length() < value.length() ? compressedString.toString() : value;
    }

    boolean isOneStepAway(String value1, String value2) {
        if (value1.isEmpty() || value2.isEmpty()) {
            return false;
        }

        if (value1.length() == value2.length()) {
            return isOneEditRemove(value1, value2);
        } else if (value1.length() - value2.length() == 1) {
            return isOneEditInsert(value1, value2);
        } else if (value1.length() - value2.length() == -1) {
            return isOneEditInsert(value2, value1);
        }

        return false;
    }

    boolean isOneEditRemove(String value1, String value2) {
        boolean foundDifference = false;
        for (int index = 0; index < value1.length(); index++) {
            if (value1.charAt(index) != value2.charAt(index)) {
                if (foundDifference) {
                    return false;
                }
                foundDifference = true;
            }
        }

        return foundDifference;
    }

    boolean isOneEditInsert(String value1, String value2) {
        int index1 = 0;
        int index2 = 0;
        boolean foundDifference = false;
        while (index1 < value1.length() && index2 < value2.length()) {
            if (value1.charAt(index1) == value2.charAt(index2)) {
                index1++;
                index2++;
            } else {
                if (foundDifference) {
                    return false;
                }
                foundDifference = true;

                index1++;
            }
        }

        return true;
    }


    boolean isPermuationPalindrome(String value) {
        if (value.isEmpty()) {
            return false;
        }
        int charOdd = 0;
        int[] charArray = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (char c : value.toCharArray()) {
            int arrayIndex = getCharValue(c);
            charArray[arrayIndex] = charArray[arrayIndex] + 1;

            if (charArray[arrayIndex] % 2 == 0) {
                charOdd--;
            } else {
                charOdd++;
            }
        }

        return charOdd <= 1;
    }

    int getCharValue(Character c) {
        int integerValue;
        if (Character.getNumericValue('a') - Character.getNumericValue(c) > 0) {
            integerValue = Character.getNumericValue(c) - Character.getNumericValue('A');
        } else {
            integerValue = Character.getNumericValue(c) - Character.getNumericValue('a');
        }

        return integerValue;
    }

    void replaceSpace(char[] value, int trueLength) {
        int spaceCount = 0;

        for (int index = 0; index < trueLength; index++) {
            if (value[index] == ' ') {
                spaceCount++;
            }
        }

        int charIndex = trueLength + spaceCount * 2;

        for (int index = trueLength - 1; index >= 0; index--) {
            if (value[index] == ' ') {
                value[charIndex - 1] = '0';
                value[charIndex - 2] = '2';
                value[charIndex - 3] = '%';

                charIndex -= 3;
            } else {
                value[--charIndex] = value[index];
            }
        }
    }

    boolean isPermutation(String value1, String value2) {
        if (value1.isEmpty() || value2.isEmpty() || value1.length() != value2.length()) {
            return false;
        }

        return sortString(value1).equals(sortString(value2));
    }

    String sortString(String value) {
        char[] charArray = value.toCharArray();

        Arrays.sort(charArray);

        String returnValue = new String(charArray);
        Log.d("isUni", "Value Sort : " + returnValue);

        return returnValue;
    }


    boolean isUnique(String value) {
        if (value.isEmpty()) {
            return false;
        }

        HashSet setCollection = new HashSet();

        for (int index = 0; index < value.length(); index++) {
            char charToInsert = value.charAt(index);
            if (setCollection.contains(charToInsert)) {
                Log.d("isUni", "returing here");
                return false;
            }
            setCollection.add(charToInsert);
        }

        return value.length() == setCollection.size();
    }
}
