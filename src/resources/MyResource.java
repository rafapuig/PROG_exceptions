package resources;

public class MyResource implements AutoCloseable {
    private int level;
    private final boolean exceptionOnCLose;

    public MyResource(int level, boolean exceptionOnCLose) {
        this.level = level;
        this.exceptionOnCLose = exceptionOnCLose;
        System.out.println("Creando MyResource. Nivel: " + level);
    }

    public void use() {
        if(level <= 0) {
            System.out.println("Lanzando una excepción al llamar al método use con un nivel muy bajo...");
            throw new RuntimeException("Nivel bajo");
        }
        System.out.println("Usando MyResource. Nivel: " + level);
        level--;
    }

    @Override
    public void close() {
        if(exceptionOnCLose) {
            System.out.println("Lanzando una excepción al cerrar el MyResource...");
            throw new RuntimeException("Error al cerrar MyResource");
        }
        System.out.println("Cerrando MyResource...");
    }
}
