package resources;

public class ResourceTest {

    public static void main(String[] args) {
        MyResource r1 = new MyResource(1,false);
        MyResource r2 = new MyResource(2,false);
        try(r1;r2) {
            r1.use();
            r2.use();
            r2.use();
        }
        useResource(new MyResource(3,false));
    }

    static void useResource(MyResource res) {
        try(res ; MyResource r4 = new MyResource(4,false)) {
            res.use();
            r4.use();
        }
    }
}
