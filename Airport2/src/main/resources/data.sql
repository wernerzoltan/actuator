insert into userlist(username, pass, enabled) values('abc', '$2a$09$6EOBlIwrRjo836Fjhi3tdeVcgWSgerNH4e9VT9XTsddtsxxs8l1HG', TRUE); 
insert into userlist(username, pass, enabled) values('def', '$2a$09$6EOBlIwrRjo836Fjhi3tdeVcgWSgerNH4e9VT9XTsddtsxxs8l1HG', TRUE);
insert into userlist(username, pass, enabled) values('ghi', '$2a$09$6EOBlIwrRjo836Fjhi3tdeVcgWSgerNH4e9VT9XTsddtsxxs8l1HG', FALSE);

insert into authorities(username, authority) values('abc', 'ROLE_ADMIN');
insert into authorities(username, authority) values('abc', 'ROLE_USER');
insert into authorities(username, authority) values('def', 'ROLE_USER');
insert into authorities(username, authority) values('ghi', 'ROLE_USER');
insert into authorities(username, authority) values('ghi', 'ROLE_ADMIN');
