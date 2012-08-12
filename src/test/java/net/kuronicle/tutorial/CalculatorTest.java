package net.kuronicle.tutorial;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static net.kuronicle.tutorial.matcher.IsDate.*;

import java.util.Date;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class CalculatorTest {
    
    @Test
    public void multiplyで3と4の乗算結果が取得できる() {
        Calculator calc = new Calculator();
        int expected = 12;
        int actual = calc.multiply(3, 4);
        assertThat(actual, is(expected));
    }
    
    @Test
    public void multiplyで5と7の乗算結果が取得できる() {
        Calculator calc = new Calculator();
        int expected = 35;
        int actual = calc.multiply(5, 7);
        assertThat(actual, is(expected));
    }
    
    @Test
    public void divideで3と2の除算結果が取得できる() {
        Calculator calc = new Calculator();
        float expected = 1.5f;
        float actual = calc.divide(3, 2);
        assertThat(actual, is(expected));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void divideの第2引数に0を指定した場合にはIllegalArgumentExceptionを送出する() {
        Calculator calc = new Calculator();
        calc.divide(5, 0);
    }
    
    @Test
    public void isDateマッチャーの動作確認() {
        assertThat(new Date(), is(dateOf(2012, 8, 11)));
    }
    
    @RunWith(Theories.class)
    public static class 乗算メソッドのパラメータ化テスト{
        
        @DataPoint
        public static Fixture DATA1 = new Fixture(3, 4, 12);
        
        @DataPoints
        public static Fixture[] DATAS = {
                new Fixture(4, 5, 20),
                new Fixture(3, 7, 21)
        };
        
        @DataPoints
        public static Fixture[] createFixtures() {
            Fixture[] fixtures = {new Fixture(1, 8, 8), new Fixture(7, 4, 28)};
            return fixtures;
        }
        
        @Theory
        public void multiplyで乗算結果が取得できること(Fixture fx) {
            String message = fx.x + "*"  + fx.y + "=" + fx.expected;
            System.out.println(message);
            Calculator calc = new Calculator();
            int expected = fx.expected;
            int actual = calc.multiply(fx.x, fx.y);
            assertThat(message, actual, is(expected));
        }
     
        static class Fixture{
            int x;
            int y;
            int expected;
            Fixture(int x, int y, int expected){
                this.x = x;
                this.y = y;
                this.expected = expected;
            }
        }
    }

}
