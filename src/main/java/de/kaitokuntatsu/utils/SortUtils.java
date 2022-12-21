package de.kaitokuntatsu.utils;


/**
 * (<a href="https://github.com/KaitoKunTatsu/KLibrary">KLibrary</a>)
 * @version	1.3.0 | last edit: 01.12.2022
 * @author Joshua Hartjes | KaitoKunTatsu#3656
 */
public class SortUtils {

    public static void quickSort(float[] pArr) {
        quickSort(pArr, 0, pArr.length-1);
    }

    public static void quickSort(float[] pArr, int pLeft, int pRight) {
        float lPivot = pArr[(pLeft + pRight) / 2];
        int lLow = pLeft,
                lHigh = pRight;

        while (lLow <= lHigh) {
            while (pArr[lLow] < lPivot)
                ++lLow;
            while (pArr[lHigh] > lPivot)
                ++lHigh;

            if (lLow <= lHigh) {
                swap(pArr,lLow,lHigh);
                ++lLow;
                --lHigh;
            }
        }
        if (pLeft < lHigh)
            quickSort(pArr, pLeft, lHigh);
        if (lLow < pRight)
            quickSort(pArr, lLow, pRight);
    }

    public static void quickSort(double[] pArr) {
        quickSort(pArr, 0, pArr.length-1);
    }

    public static void quickSort(double[] pArr, int pLeft, int pRight) {
        double lPivot = pArr[(pLeft + pRight) / 2];
        int lLow = pLeft,
                lHigh = pRight;

        while (lLow <= lHigh) {
            while (pArr[lLow] < lPivot)
                ++lLow;
            while (pArr[lHigh] > lPivot)
                ++lHigh;

            if (lLow <= lHigh) {
                swap(pArr,lLow,lHigh);
                ++lLow;
                --lHigh;
            }
        }
        if (pLeft < lHigh)
            quickSort(pArr, pLeft, lHigh);
        if (lLow < pRight)
            quickSort(pArr, lLow, pRight);
    }

    public static void quickSort(long[] pArr) {
        quickSort(pArr, 0, pArr.length-1);
    }

    public static void quickSort(long[] pArr, int pLeft, int pRight) {
        long lPivot = pArr[(pLeft + pRight) / 2];
        int lLow = pLeft,
            lHigh = pRight;

        while (lLow <= lHigh) {
            while (pArr[lLow] < lPivot)
                ++lLow;
            while (pArr[lHigh] > lPivot)
                ++lHigh;

            if (lLow <= lHigh) {
                swap(pArr,lLow,lHigh);
                ++lLow;
                --lHigh;
            }
        }
        if (pLeft < lHigh)
            quickSort(pArr, pLeft, lHigh);
        if (lLow < pRight)
            quickSort(pArr, lLow, pRight);
    }

    public static void quickSortLengthOnly(String[] pArr) {
        quickSortLengthOnly(pArr, 0, pArr.length-1);
    }

    public static void quickSortLengthOnly(String[] pArr, int pLeft, int pRight) {
        String lPivot = pArr[(pLeft + pRight) / 2];
        int lLow = pLeft,
                lHigh = pRight;

        while (lLow <= lHigh) {
            while (pArr[lLow].length() < lPivot.length())
                ++lLow;
            while (pArr[lHigh].length() > lPivot.length())
                --lHigh;

            if (lLow <= lHigh) {
                swap(pArr,lLow,lHigh);
                ++lLow;
                --lHigh;
            }
        }

        if (pLeft < lHigh)
            quickSortLengthOnly(pArr, pLeft, lHigh);
        if (lLow < pRight)
            quickSortLengthOnly(pArr, lLow, pRight);
    }

    public static void quickSort(String[] pArr, boolean pCaseSensitivity) {
        quickSort(pArr, 0, pArr.length-1, pCaseSensitivity);
    }

    public static void quickSort(String[] pArr, int pLeft, int pRight, boolean pCaseSensitivity) {
        String lPivot = pArr[(pLeft + pRight) / 2];
        int lLow = pLeft,
            lHigh = pRight;

        if (pCaseSensitivity) {
            while (lLow <= lHigh) {
                while (pArr[lLow].compareTo(lPivot) < 0)
                    ++lLow;
                while (pArr[lHigh].compareTo(lPivot) > 0)
                    --lHigh;

                if (lLow <= lHigh) {
                    swap(pArr,lLow,lHigh);
                    ++lLow;
                    --lHigh;
                }
            }
        }
        else {
            while (lLow <= lHigh) {
                while (pArr[lLow].compareToIgnoreCase(lPivot) < 0)
                    ++lLow;
                while (pArr[lHigh].compareToIgnoreCase(lPivot) > 0)
                    --lHigh;

                if (lLow <= lHigh) {
                    swap(pArr,lLow,lHigh);
                    ++lLow;
                    --lHigh;
                }
            }
        }

        if (pLeft < lHigh)
            quickSort(pArr, pLeft, lHigh, pCaseSensitivity);
        if (lLow < pRight)
            quickSort(pArr, lLow, pRight, pCaseSensitivity);
    }

    public static void quickSort(char[] pArr) {
        quickSort(pArr, 0, pArr.length-1);
    }

    public static void quickSort(char[] pArr, int pLeft, int pRight) {
        char lPivot = pArr[(pLeft + pRight) / 2];
        int lLow = pLeft,
                lHigh = pRight;

        while (lLow <= lHigh) {
            while (pArr[lLow] < lPivot)
                ++lLow;
            while (pArr[lHigh] > lPivot)
                ++lHigh;

            if (lLow <= lHigh) {
                swap(pArr,lLow,lHigh);
                ++lLow;
                --lHigh;
            }
        }
        if (pLeft < lHigh)
            quickSort(pArr, pLeft, lHigh);
        if (lLow < pRight)
            quickSort(pArr, lLow, pRight);
    }

    public static void quickSort(int[] pArr) {
        quickSort(pArr, 0, pArr.length-1);
    }

    public static void quickSort(int[] pArr, int pLeft, int pRight) {
        int lLow = pLeft,
            lHigh = pRight,
            lPivot = pArr[(pLeft + pRight) / 2];

        while (lLow <= lHigh) {
            while (pArr[lLow] < lPivot)
                ++lLow;
            while (pArr[lHigh] > lPivot)
                ++lHigh;

            if (lLow <= lHigh) {
                swap(pArr,lLow,lHigh);
                ++lLow;
                --lHigh;
            }
        }
        if (pLeft < lHigh)
            quickSort(pArr, pLeft, lHigh);
        if (lLow < pRight)
            quickSort(pArr, lLow, pRight);
    }

    public static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    public static void swap(long[] arr, int i1, int i2) {
        long temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    public static void swap(double[] arr, int i1, int i2) {
        double temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    public static void swap(float[] arr, int i1, int i2) {
        float temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    public static void swap(char[] arr, int i1, int i2) {
        char temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    public static void swap(Object[] arr, int i1, int i2) {
        Object temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    public static void main(String[] args) {
        String[] arr = {"a","b","CA","CB","abc","C","C"};
        SortUtils.quickSortLengthOnly(arr);
        for (String i:arr) System.out.println(i);
    }
}
