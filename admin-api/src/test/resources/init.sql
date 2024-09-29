create table coupon (
                        discount_value integer,
                        coupon_id bigint not null auto_increment,
                        created_at timestamp(6),
                        updated_at timestamp(6),
                        valid_datetime timestamp(6),
                        coupon_name varchar(255),
                        discount_type enum ('RATIO','VALUE'),
                        primary key (coupon_id)
);

create table event (
                       coupon_quantity integer not null,
                       coupon_id bigint unique,
                       end_datetime timestamp(6),
                       event_id bigint not null auto_increment,
                       start_datetime timestamp(6),
                       primary key (event_id)
);

create table issued_coupon (
                               coupon_id bigint,
                               created_at timestamp(6),
                               event_id bigint,
                               issued_coupon_id bigint not null auto_increment,
                               member_id bigint,
                               updated_at timestamp(6),
                               use_datetime timestamp(6),
                               primary key (issued_coupon_id)
);

create table member (
                        created_at timestamp(6),
                        member_id bigint not null auto_increment,
                        updated_at timestamp(6),
                        member_name varchar(255),
                        member_status enum ('ACTIVE','INACTIVE'),
                        member_type enum ('ADMIN','GENERAL'),
                        primary key (member_id)
);

alter table event
    add constraint eventfk1
        foreign key (coupon_id)
            references coupon(coupon_id);

alter table issued_coupon
    add constraint issuedcouponfk1
        foreign key (coupon_id)
            references coupon(coupon_id);

alter table issued_coupon
    add constraint issuedcouponfk2
        foreign key (event_id)
            references event(event_id);

alter table issued_coupon
    add constraint issuedcouponfk3
        foreign key (member_id)
            references member(member_id);