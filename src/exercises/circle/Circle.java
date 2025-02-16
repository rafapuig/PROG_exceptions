package exercises.circle;

import java.util.StringJoiner;

public class Circle {

    private final float radius;
    private float area = Float.NaN;
    private float circumference = Float.NaN;

    public Circle(float radius) {
        this.radius = validateRadius(radius);
    }

    public float getRadius() {
        return radius;
    }

    public float getArea() {
        if (Float.isNaN(area)) {
            area = (float) (Math.PI * radius * radius);
        }
        return area;
    }

    public float getCircumference() {
        if (Float.isNaN(circumference)) {
            circumference = (float) (2 * Math.PI * radius);
        }
        return circumference;
    }

    private static float validateRadius(float radius) {
        if (!isRadiusValid(radius)) {
            throw new IllegalArgumentException("Radius is invalid");
        }
        return radius;
    }

    public static boolean isRadiusValid(float radius) {
        return radius >= 0;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Circle.class.getSimpleName() + "[", "]")
                .add("radius=" + radius)
                .add(String.format("area=%.2f", getArea()))
                .add(String.format("circumference=%.2f", getCircumference()))
                .toString();
    }
}

class CircleDemo {

    public static void main(String[] args) {
        testWithPreviousCheckIfValidRadius(-1.5f);
        testWithPreviousCheckIfValidRadius(1.5f);
        testWithExceptionHandling(-1.5f);
        testWithExceptionHandling(1.5f);
    }

    private static void testWithPreviousCheckIfValidRadius(float radius) {
        if (Circle.isRadiusValid(radius)) {
            Circle circle = new Circle(radius);
            System.out.println(circle);
        } else {
            System.out.println("Radius is invalid");
        }
    }

    private static void testWithExceptionHandling(float radius) {
        try {
            Circle circle = new Circle(radius);
            System.out.println(circle);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
