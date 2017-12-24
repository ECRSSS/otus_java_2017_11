package nnglebanov.custom.testpackage;

import nnglebanov.custom.annotations.After;
import nnglebanov.custom.annotations.Before;
import nnglebanov.custom.annotations.Test;

/**
 * Created by Administrator on 23.12.2017.
 */
public class TestClass extends Object {


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

    @After
    public void after() {
        System.out.println("after");

    }
}
