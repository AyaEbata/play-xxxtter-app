# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tweet (
  id                        bigint not null,
  tweet                     varchar(255),
  user_id                   varchar(255),
  created_date              timestamp not null,
  constraint pk_tweet primary key (id))
;

create table user (
  user_id                   varchar(255) not null,
  password                  varchar(255),
  follow                    varchar(255),
  constraint pk_user primary key (user_id))
;

create sequence tweet_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists tweet;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists tweet_seq;

drop sequence if exists user_seq;

