package withfinally;

public class FinallyTest {
    public static void main(String[] args) {
        int x = 10, y = 0, z;
        try {
            System.out.println("Antes de dividir por y");
            z = x / y;
            System.out.println("Después de dividir por y");
        } catch (ArithmeticException e) {
            System.out.println("Dentro del bloque catch - 1");
        } finally {
            System.out.println("Dentro del bloque finally - 1");
        }
        System.out.println("------------------------------");

        try {
            System.out.println("Antes de establecer < a 2449");
            z = 2449;
            System.out.println("Después de establecer < a 2449");
        } catch (Exception e) {
            System.out.println("Dentro del bloque catch - 2");
        } finally {
            System.out.println("Dentro del bloque finally - 2");
        }
        System.out.println("------------------------------");

        try {
            System.out.println("Dentro del bloque try - 3");
        } finally {
            System.out.println("Dentro del bloque finally - 3");
        }
        System.out.println("------------------------------");

        try {
            System.out.println("Antes de ejecutar System.exit()");
            System.exit(0);
            System.out.println("Después de ejecutar System.exit()");
        } finally {
            // Este bloque finally no se ejecutará porque la aplicación sale en el bloque try
            System.out.println("Dentro del bloque finally - 4");
        }

    }
}
