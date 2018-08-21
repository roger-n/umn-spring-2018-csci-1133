class Rectangle extends Polygon {
    public Rectangle(int a, int b){
        super(4, a, b, a, b);
    }
    public int area(){
        return side(0)*side(1);
    }
}

class Square extends Rectangle {
    public Square(int a){
        super(a, a);
    }
}
