package guidelines.illegalargument.custom;

import guidelines.illegalargument.IllegalArgumentExceptionDemo;

import java.util.StringJoiner;

class NegativeAgeException extends IllegalArgumentException {

    public NegativeAgeException() {
        super("Edad negativa");
    }

    public NegativeAgeException(String message) {
        super(message);
    }

    public NegativeAgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeAgeException(Throwable cause) {
        super(cause);
    }
}


class Person {
    private final String name;
    private final int age;

    public static boolean isValidAge(int age) {
        return age >= 0;
    }

    public static boolean isValidName(String name) {
        return name != null && !name.isEmpty();
    }

    public Person(String name, int age) {
        System.out.println("Creando Person con edad: " + age + " y nombre: " + name + " ...");
        if (!isValidAge(age)) {
            throw new NegativeAgeException("La edad de de la persona debe ser mayor o igual que 0");
        }
        if (!isValidName(name)) {
            throw new IllegalArgumentException("Nombre no valido");
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("age=" + age)
                .toString();
    }
}

public class CustomIllegalArgumentExceptionDemo {
    public static void main(String[] args) {

            Person person = new Person("Juanito", 20);
            System.out.println(person);

            try {
                Person person2 = new Person("Juanito", -20);
                System.out.println(person2);
            } catch (NegativeAgeException e) {
                System.out.println("Capturado por el bloque catch para tipo NegativeAgeException");
                printError(e);
            } catch (IllegalArgumentException e) {
                System.out.println("Capturado por el bloque catch para tipo IllegalArgumentException");
                printError(e);
            }

        /*try {
            Person person2 = new Person("Juanito", -20);
            System.out.println(person2);
        } catch (IllegalArgumentException e) {
            System.out.println("Capturado por el bloque catch para tipo IllegalArgumentException");
            printError(e);

        } catch (NegativeAgeException e) {
            // Error de compilación: NegativeException es subclase de IllegalArgumentException
            // y hay un bloque catch que captura excepciones de tipo IlegalArgumentException más arriba
            // como una referencia NegativeException se puede asignar a un parámetro de tipo IllegalArgumentException
            // este bloque catch nunca tiene oportunidad de ser elegido, puesto que el anterior ya habrá
            // sido elegido previamente antes de comprobar este bloque catch
            System.out.println("Capturado por el bloque catch para tipo NegativeAgeException");
            printError(e);
        }*/

        try {
            Person person3 = new Person("", 20);
            System.out.println(person3);
        } catch (NegativeAgeException e) {
            System.out.println("Capturado por el bloque catch para tipo NegativeAgeException");
            printError(e);
        } catch (IllegalArgumentException e) {
            System.out.println("Capturado por el bloque catch para tipo IllegalArgumentException");
            printError(e);
        }

    }


    public static void printError(Exception e) {
        System.out.println("Error de tipo " + e.getClass().getSimpleName() + ": " + e.getMessage());
    }
}
