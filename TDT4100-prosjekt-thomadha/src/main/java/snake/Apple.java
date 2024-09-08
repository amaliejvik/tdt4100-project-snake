package snake;

import javafx.scene.Node;

public class Apple implements SnakeInterface {
    //FELTER
    private XYvalue coordinate;
    private Node applenode;

    //KONSTRUKTØR
    public Apple(){

        //Sørger for at eplet ikke kan genereres i hjørnene av brettet
        XYvalue applecoordinate = new XYvalue();
        while((applecoordinate.getxValue() == 0 && applecoordinate.getyValue() == 0)||
            (applecoordinate.getxValue() == 0 && applecoordinate.getyValue() == 10)||
            (applecoordinate.getxValue() == 10 && applecoordinate.getyValue() == 0)||
            (applecoordinate.getxValue() == 10 && applecoordinate.getyValue() == 10)){
                applecoordinate = new XYvalue();
            }
        this.coordinate = applecoordinate;
        this.applenode = this.coordinate.getRectangle();
    }

    public Apple(int xvalue, int yvalue){
        XYvalue applecoordinate = new XYvalue(xvalue, yvalue);
        
        if((applecoordinate.getxValue() == 0 && applecoordinate.getyValue() == 0)||
        (applecoordinate.getxValue() == 0 && applecoordinate.getyValue() == 10)||
        (applecoordinate.getxValue() == 10 && applecoordinate.getyValue() == 0)||
        (applecoordinate.getxValue() == 10 && applecoordinate.getyValue() == 10)){
            throw new IllegalArgumentException("Invalid cooordinates");
        }

        this.coordinate = applecoordinate;
        this.applenode = this.coordinate.getRectangle();

    }

    
    //METODER
    public XYvalue getCoordinate() {
        return coordinate;
    }

    public Node getApplenode() {
        return applenode;
    }


    @Override
    public String toString() {
        return "Apple [coordinate=" + coordinate + "]";
    }


    @Override
    public int getxValue() {
        return coordinate.getxValue();
    }


    @Override
    public int getyValue() {
        return coordinate.getyValue();
    }

    public static void main(String[] args) {
        Apple test = new Apple();

        System.out.println(test.getApplenode());
        System.out.println(test.getCoordinate());
        System.out.println(test);
    }

    
    
    
    
}
