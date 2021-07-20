package budaev.shape_main;

import budaev.shape.*;

public class ShapeMain {
    public static void Main(String[] args) {
        Shape[] shapesArray = new Shape[5];
        shapesArray[0] = new Square(2);
        shapesArray[1] = new Rectangle(5, 10);
        shapesArray[2] = new Triangle(1, 2, 3, 7, 5, 10);
        shapesArray[3] = new Circle(8);
        shapesArray[4] = new Square(5);
        
    }

   /*  public void getMaxArea (Shape[] shapesArray){
        int maxArea = 0;

        for(int i = 0; i < shapesArray.length; i++) {
            if (shapesArray[i].getArea() > maxArea){

            }
        }
    } */
}


