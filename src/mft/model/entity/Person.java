package mft.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

public class Person {
    private int id;
    private String name;
    private String family;

    private List<SimCard> simCardList;

    public void addSimCard(SimCard simCard) throws Exception {
        if (simCardList == null) {
            simCardList = new ArrayList<>();
        }
        if (simCardList.size() < 10) {
            simCard.setOwner(this);
            simCardList.add(simCard);
        }else{
            throw new Exception("Cant Get More !!!");
        }
    }
}
