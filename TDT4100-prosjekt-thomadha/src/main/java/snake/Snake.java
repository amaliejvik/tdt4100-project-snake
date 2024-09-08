package snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private List<XYvalue> snakeBody = new ArrayList<>();
    private String direction = "right";

    // KONSTRUKTØR -> LAGER KROPPEN TIL SLANGEN
    public Snake() {
        XYvalue kroppEn = new XYvalue(1, 1);
        XYvalue kroppTo = new XYvalue(2, 1);
        XYvalue kroppTre = new XYvalue(3, 1);
        snakeBody.add(kroppEn);
        snakeBody.add(kroppTo);
        snakeBody.add(kroppTre);

    }

    // flytter slangen i den rettningen den peker
    public void move() {
        int xVerdi = snakeBody.get(snakeBody.size() - 1).getxValue();
        int yVerdi = snakeBody.get(snakeBody.size() - 1).getyValue();
        XYvalue kropp = new XYvalue(xVerdi, yVerdi);

        switch (direction) {
            case ("right"):
                xVerdi += 1;
                break;
            case ("left"):
                xVerdi -= 1;
                break;
            case ("up"):
                yVerdi -= 1;
                break;
            case ("down"):
                yVerdi += 1;
                break;
        }
        kropp.setxValue(xVerdi);
        kropp.setyValue(yVerdi);
        
        // sjekker om ny del av slangen allerede er en del av slangen.
        for (XYvalue part : snakeBody) {
            if (part.getxValue() == kropp.getxValue() && part.getyValue() == kropp.getyValue()) {
                throw new IllegalArgumentException("Du krasjet i deg selv!");
            }
        }
        // Legger til ny del og fjerner halen.
        snakeBody.add(kropp);
        snakeBody.remove(0);

    }

    //gror slangen i den rettningen den peker. 
    public void grow(Apple apple) {
        //tar utgangspunkt i eplenoden, dvs. at slangen gror med "hodet"
        int xVerdi = apple.getxValue();
        int yVerdi = apple.getyValue();
        switch (direction) {
            case ("right"):
                xVerdi += 1;
                break;
            case ("left"):
                xVerdi -= 1;
                break;
            case ("up"):
                yVerdi -= 1;
                break;
            case ("down"):
                yVerdi += 1;
                break;
        }
        XYvalue kropp = new XYvalue(xVerdi, yVerdi);
        snakeBody.add(kropp);
    }

    public void changeDirection(String direction) {
        //validering av rettning til slangen
        if (!(direction.matches("right|left|up|down"))) {
            throw new IllegalArgumentException("Må være en gyldig rettning");
        }
        this.direction = direction;
    }

    // getter
    public List<XYvalue> getSnakeBody() {
        return this.snakeBody;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "[snakeBody= {X:" + snakeBody.get(0).getxValue() + " Y:" + snakeBody.get(0).getyValue() + "} {X:"
                + snakeBody.get(1).getxValue() + " Y:" + snakeBody.get(1).getyValue() + "} {X:"
                + snakeBody.get(2).getxValue() + " Y:" + snakeBody.get(2).getyValue() + "}]";
    }

    public static void main(String[] args) {

    }

}
