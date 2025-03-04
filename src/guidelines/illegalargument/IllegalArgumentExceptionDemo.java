package guidelines.illegalargument;


class Person {
    String name;
    int age;

    /**
     * Método estático para permitir al código cliente de la clase que pueda verificar
     * si un valor para el parámetro age de una Person será admitido como valido
     * @param age
     * @return true si el valor de age es admitido como válido
     */
    public static boolean isValidAge(int age) {
        return age >= 0;
    }

    public Person(String name, int age) {
        System.out.println("Creando Person con edad: " + age + " y nombre: " + name + " ...");
        this.name = name;
        setAge(age);
    }

    protected void setAge(int age) {
        if (!isValidAge(age)) {
            throw new IllegalArgumentException("Edad no valida");
        }
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}


public class IllegalArgumentExceptionDemo {

    public static void main(String[] args) {
        testCauseIllegalArgumentException();
        testCheckBeforeCauseIllegalArgumentException();
    }

    private static void testCauseIllegalArgumentException() {
        Person person = null;
        try {
            //Intentamos crear un objeto Person con una edad negativa
            person = new Person("Juanito", -30);

            // Seremos "castigados" por el desarrollador de la clase Person
            // con una excepción del tipo IllegalArgumentException
        } catch (IllegalArgumentException e) {
            printError(e);
        }

        // Cuando la llamada a un constructor produce una excepción
        // el objeto en construcción NO se crea
        // Por tanto, la variable person sigue conteniendo una referencia nula
        try {
            // Invocar un método de instancia mediante una referencia nula
            // Provoca que se lance una NullPointerException
            System.out.println(person.getName());
        } catch (NullPointerException e) {
            printError(e);
        }
    }

    /**
     * Comprobar antes de realizar la accción si esta va a producir una excepción
     * En este test se comprueba primero si crear una instancia de Person llamando al constructor
     * con una edad negativa va a producir una IllegalArgumentException
     */

    private static void testCheckBeforeCauseIllegalArgumentException() {
        Person person = null;

        int edad = -30;

        // Para ser justos con el código cliente, el desarrollador de la clase
        // ha proporcionado un mecanismo para saber si la llamada al constructor
        // producirá una excepción o no.
        // En este caso, el método estático isValidAge nos permite saber de antemano
        // si se va a poder crear sin error un objeto Person con ese valor para la edad
        // De esta manera podemos anticiparnos y prevenir que se produzca la excepción
        if (Person.isValidAge(edad)) {
            person = new Person("Juanito", edad);
        }

        // El lenguaje Java nos proporciona la comparación con null para saber
        // si la llamada a un miembro de instancia mediante una referencia
        // va a provocar una NullPointerException y poder prevenir
        // que se produzca la excepción evitando la llamada
        if (person != null) {
            System.out.println(person.getName());
        }
    }


    public static void printError(Exception e) {
        System.out.println("Error de tipo " + e.getClass().getSimpleName() + ": " + e.getMessage());
    }
}
