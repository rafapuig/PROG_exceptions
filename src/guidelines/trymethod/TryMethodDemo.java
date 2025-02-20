package guidelines.trymethod;


class Person {
    String name;
    int energy = 10;

    public void walk(int steps) {
        try {
            for (int i = 0; i < steps; i++) {
                System.out.println("Dando un paso ...");
                setEnergy(energy - 1); // Decrementar en una unidad la energía
                System.out.println("Nivel de energía restante: " + energy);
            }
        } catch (IllegalArgumentException e) {
            // Captura la IllegalArgumentException y relanza una nueva excepción
            // de tipo IllegalStateException
            // Al llamador le interesa saber que el problema es por el estado del objeto Persona
            // Se ha quedado sin energía
            // Y no, que se ha usado un argumento no válido al llamar a setEnergy
            throw new IllegalStateException("No se puede caminar con la energía agotada", e);
        }
    }

    /**
     * Establece el valor del atributo energy
     * Si el valor es negativo lanza la excepción IllegalArgumentException
     * @param energy
     */
    protected void setEnergy(int energy) {
        if (energy < 0) {
            throw new IllegalArgumentException("Energía agotada");
        }
        this.energy = energy;
    }


    protected void tryWalk(int steps) throws IllegalArgumentException {
        for (int i = 0; i < steps; i++) {
            System.out.println("Dando un paso ...");
            setEnergy(energy - 1); // Puede lanzar IllegalArgumentException si el argumento es < 0
            System.out.println("Nivel de energía restante: " + energy);
        }
    }

    public void walk_v2(int steps) {
        try {
            tryWalk(steps);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("No se puede caminar con la energía agotada", e);
        }
    }

}

public class TryMethodDemo {

    public static void main(String[] args) {
        makePersonWalk(new Person());
        makePersonWalk(new Person(), 11);
    }

    private static void makePersonWalk(Person person) {
        try {
            person.walk(5);
            person.walk(6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static void tryMakePersonWalk(Person person, int steps) {
        System.out.println("Haciendo que una persona de " + steps + " pasos ...");
        person.walk(steps);
    }

    private static void makePersonWalk(Person person, int steps) {
        try {
            tryMakePersonWalk(person, steps);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
