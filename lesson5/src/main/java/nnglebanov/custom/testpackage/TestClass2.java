package nnglebanov.custom.testpackage;

import nnglebanov.custom.annotations.Before;
import nnglebanov.custom.annotations.Test;

/**
 * Created by Administrator on 24.12.2017.
 */
public class TestClass2 {
    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");

    }

    @Before
    public void before() {
        System.out.println("before");

    }
}
