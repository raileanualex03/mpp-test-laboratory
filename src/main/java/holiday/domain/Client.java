package holiday.domain;

/**
 * The Client class extends BaseEntity where it stores the ID.
 * This class has the following attributes:
 *      the id of the client   -> id   : Long ( stored in BaseEntity )
 *      the name of the client -> name : String
 *      the age of the client  -> age  : Integer
 *
 * The class contains a constructor, getters and setters for all fields, a toString function and an equals function.
 **/

public class Client extends BaseEntity<Long>{
    private String name;
    private int age;

    public Client(){}

    public Client(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || this.getClass() != object.getClass())
            return false;

        Client client = (Client)object;

        if (this.name.equals(client.getName()))
            return false;
        return this.age == client.getAge();
    }

    @Override
    public String toString(){
        return "Client{" +
                "name=" + this.name + " / " +
                "age=" + this.age + " / " +
                super.toString() + "}";
    }
}
