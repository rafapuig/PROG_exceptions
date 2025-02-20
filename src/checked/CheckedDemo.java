package checked;


import custom.MyException;

import java.util.Random;

public class CheckedDemo {

    static Random random = new Random();

    public static void main(String[] args)  {

        throwMyExceptionAndCatchesIt();
        try {
            canThrowMyException();
        } catch (MyException e) {
            System.out.println("Error");
        }
    }


    static void canThrowMyException() throws MyException {
        if (random.nextBoolean()) {
            throw new MyException("Mala suerte");
        }
    }

    static void throwMyExceptionAndCatchesIt() {
        try {
            if (random.nextBoolean()) {
                throw new MyException("Mala suerte");
            }
        } catch (MyException e) {
            // Se traga la excepción (swallows)
        }
    }


}
