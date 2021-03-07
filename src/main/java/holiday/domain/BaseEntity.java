package holiday.domain;
        
public class BaseEntity<Id> {
    private Id Id;

    public Id getId() {
        return Id;
    }

    public void setId(Id Id) {
        this.Id = Id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "Id=" + Id +
                '}';
    }
}
