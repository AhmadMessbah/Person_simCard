create table persons
(
    id     number primary key,
    name   nvarchar2(20),
    family nvarchar2(20)
);


create table sim_cards
(
    id   number primary key,
    phone_number nvarchar2(20),
    owner_id references persons
);

select SIM_CARDS.ID as SIM_CARD_ID,
       SIM_CARDS.PHONE_NUMBER,
       PERSONS.ID   as PERSON_ID,
       PERSONS.NAME,
       PERSONS.FAMILY
from SIM_CARDS
         join PERSONS
              on SIM_CARDS.OWNER_ID = PERSONS.ID;


select count(*)
from PERSON_SIM_CARD_VIEW
where PERSON_ID = 1;