package net.kuronicle.tutorial.matcher;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class IsDate extends BaseMatcher<Date> {
    
    private final int yyyy;
    private final int mm;
    private final int dd;
    
    Object actual;

    public IsDate(int yyyy, int mm, int dd) {
        this.yyyy = yyyy;
        this.mm = mm;
        this.dd = dd;
    }

    /**
     * assertThatで利用するstaticファクトリメソッド。
     * @param yyyy 年
     * @param mm 月
     * @param dd 日
     * @return
     */
    public static Matcher<Date> dateOf(int yyyy, int mm, int dd) {
        return new IsDate(yyyy, mm, dd);
    }
    
    @Override
    public boolean matches(Object actual) {
        this.actual = actual;
        if(!(actual instanceof Date)) return false;
        Calendar cal = Calendar.getInstance();
        cal.setTime((Date) actual);
        if(yyyy != cal.get(Calendar.YEAR)) return false;
        if(mm != cal.get(Calendar.MONTH) + 1) return false;
        if(dd != cal.get(Calendar.DATE)) return false;
        return true;
    }

    @Override
    public void describeTo(Description desc) {
        desc.appendValue(yyyy + "/" + mm + "/" + dd);
        if(actual != null) {
            desc.appendText(" but actual is ");
            desc.appendValue(new SimpleDateFormat("yyyy/MM/dd").format((Date) actual));
        }
    }

}
