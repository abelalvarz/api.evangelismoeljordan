
create table if not exists evangelism.user(
    id uuid primary key default uuid_generate_v4(),
    firebase_id text null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null unique,
    phone_number varchar(50),
    password varchar(255) not null,
    status varchar(20) not null default 'ACTIVE',
    last_logged_in timestamp,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,

    constraint check_user_status
        check (status in ('ACTIVE','INACTIVE'))
);

create index index_user_email on users(email);

create table if not exists evangelism.user_roles (
   user_id uuid NOT NULL,
   roles varchar(25) NULL,
   constraint user_roles_roles_check CHECK (((roles)::text = ANY ((ARRAY['ADMIN'::character varying, 'TEACHER'::character varying, 'SECRETARY'::character varying])::text[]))),
   constraint fk_user_roles_user_id FOREIGN KEY (user_id) REFERENCES evangelism.user(id)
);