package rethrow;

import custom.MyException;

public class RethrowTest {

    static void method1() throws MyException {
        try {
            method2();
        } catch (MyException e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    static void method2() throws MyException {
        throw new MyException("Ocurrió un error");
    }



    public static void main(String[] args) {
        try {
            method1();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

}
