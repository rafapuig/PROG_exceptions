package guidelines.illegalstate;

class Book {

    String title;
    boolean opened;

    public Book(String title) {
        this.title = title;
        this.opened = false;
    }

    boolean isOpened() {
        return opened;
    }

    void open() {
        this.opened = true;
        System.out.println("El libro esta abierto");
    }

    void close() {
        this.opened = false;
        System.out.println("El libro ha sido cerrado");
    }

    void read() {
        if (!isOpened()) {
            throw new IllegalStateException("El libro no se puede leer si no esta abierto");
        }
        System.out.println("Leyendo el libro " + this.title);
    }

}


public class BookDemo {

    public static void main(String[] args) {
        tryReadWithoutChecking();
        tryReadWithChecking();
    }

    private static void tryReadWithoutChecking() {
        try {
            Book book = new Book("Java Fundamentals");
            book.read();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void tryReadWithChecking() {

        Book book = new Book("Java Fundamentals");
        if (!book.isOpened()) {
            book.open();
            book.read();
        }
    }
}
