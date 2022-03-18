package Lesson14;

import org.junit.Assert;
import org.junit.Test;

public class Task2Tast {

    @Test
    public void checkArray (){
        int [] arr1 = new int[] {1, 1, 1, 4, 4, 1, 4, 4};
        boolean checkArr1 = Task2.checkArray(arr1);
        Assert.assertTrue(checkArr1);
    }
    @Test
    public void checkArrayV2 (){
        int [] arr2 = new int[] {1, 1, 1, 1, 1, 1, 1};
        boolean checkArr = Task2.checkArray(arr2);
        Assert.assertFalse(checkArr);
    }

    @Test
    public void checkArrayV3 (){
        int [] arr3 = new int[] { 4, 4, 4, 4};
        boolean checkArr = Task2.checkArray(arr3);
        Assert.assertFalse(checkArr);
    }

    @Test
    public void checkArrayV4 (){
        int [] arr4 = new int[] {1, 1, 1, 4, 3, 1, 4, 4};
        boolean checkArr = Task2.checkArray(arr4);
        Assert.assertFalse(checkArr);
    }

}
