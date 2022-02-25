create table cat_breed(
    id serial primary key
                      ...
);

create table cat(
    id serial primary key,
    cat_breed_id int not null references cat_breed(id)
);

create table owner(
    id serial primary key
                  ...
);

create table cat_owner(
    id serial primary key,
    owner_id int not null references owner(id),
    cat_id int not null references cat(id)
);