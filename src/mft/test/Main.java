package mft.test;

import mft.model.entity.Person;
import mft.model.entity.SimCard;
import mft.repository.ConnectionProvider;
import mft.service.PersonService;
import mft.service.SimCardService;

public class Main {
    public static void main(String[] args)  throws Exception{
        ConnectionProvider.getProvider().getConnection();

//        SimCard simCard1 = SimCard.builder().id(1).simNumber("0917").build();
//        SimCard simCard2 = SimCard.builder().id(2).simNumber("0912").build();
//        SimCard simCard3 = SimCard.builder().id(3).simNumber("0913").build();

//        List<SimCard> simCardList = new ArrayList<>();
//        simCardList.add(simCard1);
//        simCardList.add(simCard2);


//        Person person = Person.builder().id(2).name("reza").family("rezaii").build();
//        person.addSimCard(simCard1);
//        person.addSimCard(simCard2);
//        person.addSimCard(simCard3);
//
//        System.out.println(simCard1.getOwner().getName());
//        System.out.println(simCard2.getOwner().getName());
//        System.out.println(simCard3.getOwner().getName());
//
//        PersonService.save(person);

//        SimCardService.save(simCard1);
//        SimCardService.save(simCard2);
//        SimCardService.save(simCard3);

        System.out.println(SimCardService.findAll());
//        Person person = PersonService.findById(2);
//
//        person.getSimCardList().forEach(System.out::println);

//        System.out.println(SimCardService.countSimCardsByPersonId(2));
    }
}
