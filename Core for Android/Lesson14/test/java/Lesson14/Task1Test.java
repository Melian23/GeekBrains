package Lesson14;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class Task1Test {

    @Test
    public void newArray (){

        int [] arrIn2 = new int [] {5,8,7,4,1,6,1,2,3};
        int [] arr1 =Task1.newArray(arrIn2);
        int [] arr1_1 = new int[] {1,6,1,2,3};
        Assert.assertArrayEquals(arr1, arr1_1);
    }
    @Test
    public void newArrayV2 (){

        int [] arrIn3 = new int [] {5,4,7, 8,1,6,4,2,5,3};
        int [] arr2 = Task1.newArray(arrIn3);
        int [] arr2_1 = new int[] {2,5,3};

        Assert.assertArrayEquals(arr2, arr2_1);
    }
    @Test
    public void newArrayV3 (){

        int [] arrIn4 = new int [] {5,8,7,1,6,7,2,4};
        int [] arr3 =Task1.newArray(arrIn4);
        int [] arr3_1 = new int[] {};

        Assert.assertArrayEquals(arr3, arr3_1);
    }
}
