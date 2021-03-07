package holiday.domain;

import java.util.Objects;

public class PairIds<Id1, Id2>{
    Id1 firstId;
    Id2 secondId;

    public PairIds(Id1 firstId, Id2 secondId) {
        this.firstId = firstId;
        this.secondId = secondId;
    }

    public Id1 getFirstId() {
        return firstId;
    }

    public void setFirstId(Id1 firstId) {
        this.firstId = firstId;
    }

    public Id2 getSecondId() {
        return secondId;
    }

    public void setSecondId(Id2 secondId) {
        this.secondId = secondId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairIds<?, ?> pairIds = (PairIds<?, ?>) o;
        return Objects.equals(firstId, pairIds.firstId) && Objects.equals(secondId, pairIds.secondId);
    }

    @Override
    public String toString() {
        return "PairIds{" +
                "firstId=" + firstId +
                ", secondId=" + secondId +
                '}';
    }
}
