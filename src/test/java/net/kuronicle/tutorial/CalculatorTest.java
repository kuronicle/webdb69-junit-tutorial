package net.kuronicle.tutorial;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static net.kuronicle.tutorial.matcher.IsDate.*;

import java.util.Date;

import net.kuronicle.tutorial.rule.AssertionMessage;

import org.junit.Assume;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class CalculatorTest {

    public static class 基本的なテストケースの作り方 {

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
//            assertThat(new Date(), is(dateOf(2012, 8, 14)));
        }
    }

    @RunWith(Theories.class)
    public static class 乗算メソッドのパラメータ化テスト {

        @DataPoint
        public static Fixture DATA1 = new Fixture(3, 4, 12);

        @DataPoints
        public static Fixture[] DATAS = { new Fixture(4, 5, 20),
                new Fixture(3, 7, 21) };

        @DataPoints
        public static Fixture[] createFixtures() {
            Fixture[] fixtures = { new Fixture(1, 8, 8), new Fixture(7, 4, 28) };
            return fixtures;
        }
        
        @Rule
        public AssertionMessage message = new AssertionMessage();
        
        @Theory
        public void multiplyで乗算結果が取得できること(Fixture fx) {
            message.append("case: %d * %d = %d", fx.x, fx.y, fx.expected);
            String message = fx.x + "*" + fx.y + "=" + fx.expected;
            System.out.println(message);
            Calculator calc = new Calculator();
            int expected = fx.expected;
            int actual = calc.multiply(fx.x, fx.y);
            assertThat(message, actual, is(expected));
        }

        static class Fixture {
            int x;
            int y;
            int expected;

            Fixture(int x, int y, int expected) {
                this.x = x;
                this.y = y;
                this.expected = expected;
            }
        }
    }

    @RunWith(Theories.class)
    public static class 除算メソッドのパラメータ化テスト {

        @DataPoints
        public static Fixture[] DATAS = { new Fixture(3, 2, 1.5f),
                new Fixture(10, 2, 5f), new Fixture(3, 0, null),
                new Fixture(10, 0, null) };

        @Rule
        public ExpectedException exception = ExpectedException.none();

        @Theory
        public void devideで除算結果が取得できること(Fixture fx) {
            Assume.assumeTrue(fx.y != 0);
            Calculator calc = new Calculator();
            float expected = fx.expected;
            float actual = calc.divide(fx.x, fx.y);
            assertThat(actual, is(expected));
        }

        @Theory
        public void devideの第2引数に0を指定した場合にはIllegalArgumentExceptionを送出する(
                Fixture fx) {
            Assume.assumeTrue(fx.y == 0);
            exception.expect(IllegalArgumentException.class);
            Calculator calc = new Calculator();
            calc.divide(fx.x, fx.y);
        }

        static class Fixture {
            int x, y;
            Float expected;

            Fixture(int x, int y, Float expected) {
                this.x = x;
                this.y = y;
                this.expected = expected;
            }
        }
    }
}
