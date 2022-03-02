create table cat_breeds(
    id serial primary key,
    breedname varchar(2000),
    coatcolor varchar(2000),
    eyescolor varchar(2000)
);

create table cats(
    id serial primary key,
    name varchar(2000),
    age int,
    cat_breed_id int not null references cat_breed(id)
);

create table owners(
    id serial primary key,
    name varchar(2000),
    phone varchar(2000)
);

create table cat_owner(
    id serial primary key,
    owner_id int not null references owner(id),
    cat_id int not null references cat(id)
);

create table ads(
    id serial primary key,
    description varchar(2000),
    created timestamp,
    active boolean,
    cat_id int not null references cat(id)
);