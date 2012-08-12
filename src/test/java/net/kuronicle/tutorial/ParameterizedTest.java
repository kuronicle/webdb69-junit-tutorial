package net.kuronicle.tutorial;

import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class ParameterizedTest {

    @DataPoint
    public static int INT_PARAM_1 = 3;
    
    @DataPoint
    public static int INT_PARAM_2 = 4;
    
    public ParameterizedTest() {
        System.out.println("初期化＠コンストラクタ");
    }
    
    @Theory
    public void test(int param) throws Exception {
        System.out.println("test(" + param + ")");
    }
    
    @Theory
    public void test2(int param) throws Exception {
        System.out.println("test2(" + param + ")");
    }
    
    @Test
    public void nomalTest() throws Exception{
        System.out.println("nomalTest()");
    }
}
