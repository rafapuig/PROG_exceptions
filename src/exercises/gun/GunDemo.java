package exercises.gun;

class EmptyGunException extends IllegalStateException {

    private static final String MESSAGE = "El arma esta vacía";

    public EmptyGunException() {
        this(MESSAGE);
    }

    public EmptyGunException(String message) {
        this(message, null);
    }

    public EmptyGunException(Throwable cause) {
        this(MESSAGE, cause);
    }

    public EmptyGunException(String message, Throwable cause) {
        super(message, cause);
    }

}


class Gun {

    public static final int MAX_CHARGE = 6;

    int bullets;

    public void reload() {
        System.out.println("Recargando el arma...");
        bullets = MAX_CHARGE;
    }

    public boolean isEmpty() {
        return bullets == 0;
    }

    public void shoot() {
        System.out.println("Disparando...");
        if (isEmpty()) {
            throw new EmptyGunException();
        }
        System.out.println("Disparo!!!!!");
        bullets--;
        System.out.println("Balas restantes: " + bullets);
    }

}

public class GunDemo {

    public static void main(String[] args) {
        Gun gun = new Gun();
        try {
            gun.shoot();
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        if (!gun.isEmpty()) {
            gun.shoot();
        }

        try {
            gun.reload();
            for (int i = 0; i < Gun.MAX_CHARGE; i++) {
                gun.shoot();
            }
            gun.reload();
            for (int i = 0; i < Gun.MAX_CHARGE; i++) {
                gun.shoot();
            }
            gun.shoot();

        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
