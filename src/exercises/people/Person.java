package exercises.people;

import java.util.StringJoiner;

class IllegalAgeException extends IllegalArgumentException {

    public IllegalAgeException() {
        super("Edad no valida", null);
    }

    public IllegalAgeException(String message) {
        super(message);
    }

    public IllegalAgeException(String message, Throwable cause) {
        super(message, cause);
    }

}

public class Person {

    public enum MaritalStatus {SINGLE, MARRIED, DIVORCED}

    public static final int LEGAL_AGE = 18;

    private final String name;
    private final int age;
    private Person spouse;
    private MaritalStatus maritalStatus;

    public static boolean isValidAge(int age) {
        return age >= 0; // una edad es valida si no es un valor negativo
    }

    public static int validateAge(int age) {
        if (!isValidAge(age)) {
            throw new IllegalAgeException("La edad de la persona debe ser mayor o igual a cero");
        }
        return age;
    }



    public Person(String name, int age) {
        this.name = name;
        this.age = validateAge(age);
        this.maritalStatus = MaritalStatus.SINGLE;
        this.spouse = null;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isMarried() {
        return spouse != null;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public Person getSpouse() {
        return spouse;
    }


    public boolean isLegalAged() {
        return age >= LEGAL_AGE;
    }

    public void marryWith(Person fiancee) {
        if (fiancee == null) {
            throw new IllegalArgumentException("La persona prometida no puede ser null");
        }
        if (!fiancee.isLegalAged()) {
            throw new IllegalArgumentException("La persona prometida no puede tener menos de " + LEGAL_AGE + "años");
        }
        if (fiancee.isMarried()) {
            throw new IllegalArgumentException("La persona prometida no puede ser una persona casada");
        }
        if (this.isMarried()) {
            throw new IllegalStateException("La persona no se puede casar si ya esta casada");
        }
        if (!isLegalAged()) {
            throw new IllegalStateException("La persona tiene que ser mayor de edad para poder casarse");
        }
        this.spouse = fiancee;
        fiancee.spouse = this;
        this.maritalStatus = MaritalStatus.MARRIED;
        this.spouse.maritalStatus = this.maritalStatus;
    }

    /**
     * Un objeto Person se divorcia de su cónyuge (si lo tiene)
     * A su vez el cónyuge también queda divorciado del objeto Persona
     * El estado civil de ambos pasa a ser DIVORCED
     */
    public void divorce() {
        if (!isMarried()) {
            throw new IllegalStateException("La persona no esta casada, no se puede divorciar");
        }
        this.spouse.spouse = null; // Ya no soy el esposo de mi esposo
        this.spouse.maritalStatus = MaritalStatus.DIVORCED; // Y pasa a ser divorciada
        this.spouse = null; // Ya no tengo esposo
        this.maritalStatus = MaritalStatus.DIVORCED; // Paso a estar divorciado
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", getClass().getSimpleName() + "{", "}")
                .add("name='" + name + "'")
                .add("age=" + age)
                .add("spouse=" + (spouse != null ? spouse.name : "nadie"))
                .add("maritalStatus=" + maritalStatus)
                .toString();
    }
}


class PersonDemo {

    public static void main(String[] args) {
        testMarriage();
        testMarriageWithUnderLegalAge();
        testMarriageAndDivorce();
        testDivorceWithoutMarriage();
        testDivorceWithoutMarriagePrechecked();
    }

    private static void testMarriage() {
        Person adan = new Person("Adan", 18);
        Person eva = new Person("Eva", 18);

        System.out.println(adan);
        System.out.println(eva);
        adan.marryWith(eva);
        System.out.println(adan);
        System.out.println(eva);
    }

    private static void testMarriageWithUnderLegalAge() {
        Person adan = new Person("Adan", 18);
        Person eva = new Person("Eva", 17);

        System.out.println(adan);
        System.out.println(eva);
        try {
            adan.marryWith(eva);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(adan);
        System.out.println(eva);
    }

    private static void testMarriageAndDivorce() {
        Person adan = new Person("Adan", 18);
        Person eva = new Person("Eva", 18);

        System.out.println(adan);
        System.out.println(eva);
        adan.marryWith(eva);
        System.out.println(adan);
        System.out.println(eva);
        eva.divorce();
        System.out.println(adan);
        System.out.println(eva);
    }

    private static void testDivorceWithoutMarriage() {
        Person adan = new Person("Adan", 18);

        System.out.println(adan);
        try {
            adan.divorce();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(adan);
    }

    private static void testDivorceWithoutMarriagePrechecked() {
        Person adan = new Person("Adan", 18);

        System.out.println(adan);
        if(adan.isMarried()) {
            adan.divorce();
        } else {
            System.out.println(adan.getName() + " no se puede divorciar porque no esta casado");
        }
        System.out.println(adan);
    }


}
