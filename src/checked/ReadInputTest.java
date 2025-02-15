package checked;

import java.io.IOException;

class ReadInput {

    /* Tal como está no compila
    public static char readChar() {
        char c = '\u0000';
        // El método read lee el siguiente byte del flujo de entrada y devuelve un entero entre 0 y 255
        // o un -1 si se ha alcanzado el final del flujo
        int input = System.in.read(); // El método read declara que lanza una excepción de tipo comprobada IOException
        if (input != -1) { // Si no se ha devuelto -1, es que se hay leído un carácter y no el final
            c = (char) input;
        }
        return c;
    }
    */

    public static char readCharWithTryCatch() {
        char c = '\u0000';
        try {
            // El método read lee el siguiente byte del flujo de entrada y devuelve un entero entre 0 y 255
            // o un -1 si se ha alcanzado el final del flujo
            int input = System.in.read(); // El método read declara que lanza una excepción de tipo comprobada IOException
            if (input != -1) { // Si no se ha devuelto -1, es que se hay leído un carácter y no el final
                c = (char) input;
            }
        } catch (IOException e) {
            System.out.println("IOException en readCharWithTryCatch");
        }
        return c;
    }

    public static char readCharWithDeclareThrows() throws IOException {
        char c = '\u0000';
        // El método read lee el siguiente byte del flujo de entrada y devuelve un entero entre 0 y 255
        // o un -1 si se ha alcanzado el final del flujo
        int input = System.in.read(); // El método read declara que lanza una excepción de tipo comprobada IOException
        if (input != -1) { // Si no se ha devuelto -1, es que se hay leído un carácter y no el final
            c = (char) input;
        }
        return c;
    }
}

public class ReadInputTest {

    // El método main declara la excepción IOException
    // porque no ejecuta la llamada al método que declara que lanza la excepción IOException
    // dentro de un bloque try-catch con un bloque catch que maneje esa clase de excepción
    public static void main(String[] args) throws IOException {
        testReadCharWithTryCatch();
        testReadCharWithDeclareThrowsAndCatch();
        testReadCharWithDeclareThrowsNoCatches(); // Llamar a este método puede lanzar una excepción comprobada
    }

    private static void testReadCharWithTryCatch() {
        System.out.print("Introduce un texto y pulsa Enter: ");
        char c = ReadInput.readCharWithTryCatch();
        System.out.println("El primer carácter introducido es: " + c);
    }

    private static void testReadCharWithDeclareThrowsAndCatch() {
        try {
            System.out.print("Introduce un texto y pulsa Enter: ");
            char c = ReadInput.readCharWithDeclareThrows();
            System.out.println("El primer carácter introducido es: " + c);
        } catch (IOException e) {
            System.out.println("Error de lectura del texto");
        }
    }

    private static void testReadCharWithDeclareThrowsNoCatches() throws IOException {
        System.out.print("Introduce un texto y pulsa Enter: ");
        char c = ReadInput.readCharWithDeclareThrows();
        System.out.println("El primer carácter introducido es: " + c);
    }
}
