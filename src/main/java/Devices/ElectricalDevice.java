package Devices;

public class ElectricalDevice {
    private String name;
    private int year;
    private String country;
    private double power;
    private double weight;
    private boolean status;
    private double price;

    public ElectricalDevice(String name, int year, String country, double power, double weight, boolean status, double price){
        this.name = name;
        this.year = year;
        this.country = country;
        this.power = power;
        this.weight = weight;
        this.status = status;
        this.price = price;
    }

    public ElectricalDevice() {

    }


    public String getName() {return name;}

    public int getYear() {
        return year;
    }

    public String getCountry() {
        return country;
    }

    public double getPower() {
        return power;
    }

    public boolean status() {return status;}

    public void setStatus(boolean status) {this.status = status;}

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        String statusName;
        if(status)
            statusName = "turned on";
        else
            statusName = "turned off";
        return "Ім\'я електричного приладу - " + name + ":" +
                "\n\t1)рік випуску: " + year +
                "\n\t2)назва країни: " + country +
                "\n\t3)потужність: " + power +
                "\n\t4)вага: " + weight +
                "\n\t5)статус: " + statusName +
                "\n\t6)ціна: " + price;
    }

    public double getWeight() {
        return weight;
    }
}
