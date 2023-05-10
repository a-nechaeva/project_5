package models;

public class Coordinates {
    private Float x; //Значение поля должно быть больше -97, Поле не может быть null
    private Double y; //Значение поля должно быть больше -480, Поле не может быть null
    public Coordinates(){
    }
    public Coordinates(Float x, Double y){
        this.x = x;
        this.y = y;
    }
    /**
     * @return Float
     */
    public Float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return Double
     */
    public Double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "(" + this.x.toString() + " " + this.y + ")";
    }
}
