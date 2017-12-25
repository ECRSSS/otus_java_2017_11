package nnglebanov.custom.testrunner;

import com.google.common.reflect.ClassPath;
import nnglebanov.custom.annotations.After;
import nnglebanov.custom.annotations.Before;
import nnglebanov.custom.annotations.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


/**
 * Created by Administrator on 23.12.2017.
 */
public class TestRunner {
    public static void main(String[] args) {
        runTestsByClassName("nnglebanov.custom.testpackage.TestClass");
        runTestsByPackageName("nnglebanov.custom.testpackage");
        runTestsByPackageName("testtest");
    }

    public static void runTestsByClassName(String className) {
        try {
            if(!isTestClass(className)){return;}
            System.out.println(className+":");
            Class c = Class.forName(className);
            ArrayList<Method> methodsBefore = new ArrayList<>();
            ArrayList<Method> methodsTest = new ArrayList<>();
            ArrayList<Method> methodsAfter = new ArrayList<>();

            ArrayList<Method> methods = new ArrayList<Method>(Arrays.asList(c.getDeclaredMethods()));

            for (Method a : methods) {
                if (a.isAnnotationPresent(Before.class)) {
                    methodsBefore.add(a);
                } else if (a.isAnnotationPresent(Test.class)) {
                    methodsTest.add(a);
                } else if (a.isAnnotationPresent(After.class)) {
                    methodsAfter.add(a);
                }
            }
            methodsTest.sort((Method m1, Method m2) -> m1.getName().compareTo(m2.getName()));
            methodsBefore.sort((Method m1, Method m2) -> m1.getName().compareTo(m2.getName()));
            methodsAfter.sort((Method m1, Method m2) -> m1.getName().compareTo(m2.getName()));
            for (Method test : methodsTest) {
                Object obj = c.newInstance();
                for (Method before : methodsBefore) {
                    before.invoke(obj, null);
                }
                test.invoke(obj, null);
                for (Method after : methodsAfter) {
                    after.invoke(obj, null);
                }

            }
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден");
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static void runTestsByPackageName(String packageName) {
        final ClassLoader loader = Thread.currentThread()
                .getContextClassLoader();
        try {
            for (final ClassPath.ClassInfo info : ClassPath.from(loader)
                    .getTopLevelClasses()) {

                if (info.getName().startsWith(packageName)) {
                    if(isTestClass(info.getName()))
                    runTestsByClassName(info.getName());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isTestClass(String className) {
        try {
            Class c = Class.forName(className);
            ArrayList<Method> methods = new ArrayList<Method>(Arrays.asList(c.getDeclaredMethods()));
            for (Method a : methods) {
                if (a.isAnnotationPresent(Test.class)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

}
