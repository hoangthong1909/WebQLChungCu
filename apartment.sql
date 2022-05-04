create table Customer
(
    id           int auto_increment
        primary key,
    nameCustomer varchar(50) charset utf8  not null,
    phone        varchar(50) charset utf8  not null,
    email        varchar(50) charset utf8  not null,
    CMND         varchar(50)               not null,
    status       bit                       not null,
    sex          bit                       not null,
    address      varchar(200) charset utf8 not null,
    constraint Customer_CMND_uindex
        unique (CMND),
    constraint Customer_email_uindex
        unique (email),
    constraint Customer_phone_uindex
        unique (phone)
);

create table Typecontract
(
    id   int auto_increment
        primary key,
    name varchar(50) charset utf8 not null
);

create table users
(
    id           int auto_increment
        primary key,
    name         varchar(100) charset utf8 null,
    email        varchar(100) charset utf8 not null,
    passwordUser varchar(200) charset utf8 null,
    numberPhone  varchar(20) charset utf8  null,
    sex          int        default 0      null,
    birthday     date                      null,
    address      varchar(100) charset utf8 null,
    avatar       varchar(50) charset utf8  null,
    isAdmin      int        default 0      null,
    status       tinyint(1) default 0      null,
    constraint users_email_uindex
        unique (email)
);

create table Building
(
    id            int auto_increment
        primary key,
    nameBuilding  varchar(100) charset utf8 null,
    address       varchar(100) charset utf8 null,
    idUser        int                       null,
    status        int default 0             null,
    dateCreate    date                      not null,
    toilet        double                    not null,
    security      double                    not null,
    landscapeCare double                    not null,
    work          double                    not null,
    garbage       double                    not null,
    constraint FK3uuviqwsmo10x3xmpcgraubjq
        foreign key (idUser) references users (id)
);

create table Floor
(
    id         int auto_increment
        primary key,
    nameFloor  varchar(10) charset utf8 null,
    idBuilding int                      null,
    dateCreate date                     not null,
    status     bit                      not null,
    constraint Floor_Building_id_fk
        foreign key (idBuilding) references Building (id)
);

create table Room
(
    id       int auto_increment
        primary key,
    nameRoom varchar(20) charset utf8 null,
    idFloor  int                      null,
    status   bit                      not null,
    acreage  double                   not null,
    bedroom  int                      not null,
    bathroom int                      not null,
    classify int                      not null,
    constraint Room_Floor_id_fk
        foreign key (idFloor) references Floor (id)
);

create table contract
(
    id         int auto_increment
        primary key,
    idUser     int                      null,
    dateCreate date                     null,
    dateEnd    date                     null,
    idRoom     int                      null,
    price      double                   null,
    status     bit                      not null,
    name       varchar(50) charset utf8 not null,
    type_id    int                      not null,
    idCustomer int                      not null,
    vehicle    int                      not null,
    people     int                      not null,
    constraint FKk2qvkl9pip618cohxx6x4j698
        foreign key (type_id) references Typecontract (id),
    constraint FKrfi3mi5up0sery6t61q3xfsk4
        foreign key (idCustomer) references Customer (id),
    constraint contract_Room_id_fk
        foreign key (idRoom) references Room (id),
    constraint contract_users_id_fk
        foreign key (idUser) references users (id)
);

create table Receipt
(
    id          int auto_increment
        primary key,
    service     double not null,
    parking     double not null,
    electricity double null,
    water       double not null,
    contract_id int    not null,
    status      bit    not null,
    internet    double not null,
    constraint FKqamgev7clvo0rxd5rrc1g7p1h
        foreign key (contract_id) references contract (id)
);