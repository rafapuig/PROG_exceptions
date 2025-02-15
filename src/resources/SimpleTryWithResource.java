package resources;

public class SimpleTryWithResource {

    static void testUseWithoutCausingAnyException() {
        // Crear y usar un recurso de tipo MyResource
        // El método close se llamará automáticamente
        try(MyResource resource = new MyResource(2, false)) {
            resource.use();
            resource.use();
        }
    }

    /** Cuando el bloque try de un try-with-resources termina sin producir ninguna excepción
     * y es únicamente el método close() el que produce una excepción el runtime reporta
     * la excepción lanzada desde el método close
     */

    static void testUseCausingExceptionOnlyOnClose() {
        // Crear y usar un recurso de tipo MyResource
        // El método close se llamará automáticamente
        try(MyResource resource = new MyResource(2, true)) {
            resource.use();
            resource.use();
        } catch (Exception e) {
            System.out.println("Mensaje: " + e.getMessage());
        }
    }

    static void testUseCausingExceptionOnlyOnUse() {
        // Crear y usar un recurso de tipo MyResource
        // El método close se llamará automáticamente
        try(MyResource resource = new MyResource(2, false)) {
            resource.use();
            resource.use();
            resource.use(); //La tercera llamada provoca una excepción
        } catch (Exception e) {
            System.out.println("Mensaje: " + e.getMessage());
        }
    }

    /** Cuando durante la ejecución del bloque try se produce una excepción y también se produce
     * una excepción en el método close() el runtime suprime la excepción lanzada por el método close
     * e informa de la excepción lanzada desde el bloque try
     *
     * Se puede acceder la las excepciones suprimidas mediante el método getSuppressed() de la clase Throwable
     * que devuelve un array de referencias a objetos Throwable
     */

    static void testUseCausingExceptionOnCloseAndUse() {
        // Crear y usar un recurso de tipo MyResource
        // El método close se llamará automáticamente
        try(MyResource resource = new MyResource(2, true)) {
            resource.use();
            resource.use();
            resource.use(); //La tercera llamada provoca una excepción
        } catch (Exception e) {
            System.out.println("Mensaje: " + e.getMessage()); // El runtime reporta la excepción lanzada desde el bloque try
            System.out.println("Mensajes de las excepciones suprimidas ...");
            for (int i = 0; i < e.getSuppressed().length; i++) {
                System.out.println(e.getSuppressed()[i].getMessage());
            }
        } // Cerrar también provoca una excepción, será suprimido si desde el try se lanza alguna excepción
    }

    public static void main(String[] args) {
            testUseWithoutCausingAnyException();
            testUseCausingExceptionOnlyOnClose();
            testUseCausingExceptionOnlyOnUse();
            testUseCausingExceptionOnCloseAndUse();
    }
}
