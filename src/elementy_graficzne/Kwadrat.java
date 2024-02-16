package elementy_graficzne;

public class Kwadrat {

    private final int a = 40;
    private int x, y;

    public Kwadrat(int x, int y) {
        this.x = x - this.a / 2;
        this.y = y - this.a / 2;
    }

    public int getA() {
        return a;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
