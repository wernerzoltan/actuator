drop table authorities;
drop table userlist;

create table userlist(
      username varchar(50) not null primary key,
      pass varchar(200) not null,
      enabled boolean not null);

create table authorities (
      username varchar(50) not null,
      authority varchar(50) not null,
      constraint fk_authorities_users foreign key(username) references userlist(username));
