import static org.junit.Assert.*;
import org.junit.Test;

public class TestFilk {

    @Test
    public void testFilk() {
        assertTrue(Flik.isSameNumber(128, 128));
    }

    /** Java常量池问题
     * @source https://zhuanlan.zhihu.com/p/52935091
     *
     * 在-128~127的Integer值并且以 Integer x = value; 的方式赋值的Integer值在进行==和equals比较时，
     * 都会返回true，因为Java里面对处在在-128~127之间的Integer值，用的是原生数据类型int，
     * 此时调用的是Integer.valueOf()方法，会在内存里供重用，
     * 也就是说这之间的Integer值进行==比较时只是进行int原生数据类型的数值比较，
     * 而超出-128~127的范围，进行==比较时是进行地址及数值比较。
     * 也就是说，因为a和b的地址是不一样的，所以a==b输入的是false。
     *
     * 可改为.equals()方法
     */
}
