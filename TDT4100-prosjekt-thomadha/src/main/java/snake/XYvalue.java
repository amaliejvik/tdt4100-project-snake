package snake;

import java.util.Random;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class XYvalue implements SnakeInterface {
    
    private int xValue;
    private int yValue;
    private Rectangle rektangel; 

    //KONSTRUKTØR 1 - Genererer en random xy-verdi
    public XYvalue(){
        Random random = new Random();
        int max_value = 11;
        this.xValue = random.nextInt(max_value);
        this.yValue = random.nextInt(max_value);
        this.rektangel = new Rectangle(30, 30, Color.RED);

    }

    //KONSTRUKTØR 2 - Du kan sette x-verdi og y-verdi selv
    public XYvalue(int xValue, int yValue){
        setxValue(xValue);
        setyValue(yValue);
        this.rektangel = new Rectangle(30, 30, Color.BLUE);
    }


    //STANDARDMETODER
    public void setxValue(int xValue) {
        //Validering&Unntakshåndtering
        if(xValue>10 || xValue<0){
            throw new IllegalArgumentException("Du traff veggen!");
        }
        this.xValue = xValue;
    }

    public void setyValue(int yValue) {
        //Validering&Unntakshåndtering
        if(yValue>10 || yValue<0){
            throw new IllegalArgumentException("Du traff veggen!");
        }
        this.yValue = yValue;
    }

    public int getxValue() {
        return xValue;
    }

    public int getyValue() {
        return yValue;
    } 

    public Rectangle getRectangle(){
        return rektangel; 
    }

    @Override
    public String toString() {
        return "X:" + getxValue() + "  Y: " + getyValue() ;
    }

	@Override
	public XYvalue getCoordinate() {
        return this;
	}

    

    
}
