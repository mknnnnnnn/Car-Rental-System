package carRentalSystem;

public class CarsConstructor {
    int carId;
    String series;
    String model;
    String color;
    String clas;
    String rented;

    public CarsConstructor(int carId, String series, String model, String color, String clas, String rented) {
        this.carId = carId;
        this.series = series;
        this.model = model;
        this.color = color;
        this.clas = clas;
        this.rented = rented;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getRented() {
        return rented;
    }

    public void setRented(String rented) {
        this.rented = rented;
    }
}
