package intro;

public class DivideByZeroWithTryCatch {

    public static void main(String[] args) {
        int x = 10, y = 0, z;
        try {
            z = x / y;
            System.out.println("z = " + z);
        } catch (ArithmeticException e) {
            String message = e.getMessage();
            System.out.println("Ha ocurrido un error. El error es: " + message);
        }

        System.out.println("Fin del programa");
    }

}
