public class Customer {

    private String city;
    private String name;
    private String streetName;
    private int streetNumber;

    public static int numberOfCreatedCustomers = 0;

    //Constructors

    public Customer(){
        numberOfCreatedCustomers = numberOfCreatedCustomers+1;
    }

    public Customer(String city, String name, String streetName, int streetNumber) {

        this.city = city;
        this.name = name;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        numberOfCreatedCustomers = numberOfCreatedCustomers + 1;
    }

    //Accessor /Mutator

    public int getNumberOfCreatedCustomers(){
        return numberOfCreatedCustomers;
    }

    public String getCity() {

        return city;
    }
    public String getName() {

        return name;
    }
    public String getStreetName() {

        return streetName;
    }
    public int getStreetNumber() {

        return streetNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    //toString

    public String toString() {
        return (" Name: " + name + "| City: "+ city +  "| Street Number: " + streetNumber + "| Street Name: " + streetName);
    }

    //Equality of two customers

    public boolean equals(Customer a, Customer b) {
        if (a.city.equals(b.city) && a.streetName.equals(b.streetName) ){
            return true;
        }
        return false;
    }


}