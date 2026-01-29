create table if not exists evangelism.cell(
    id uuid primary key default uuid_generate_v4(),
    name varchar(255) not null unique,
    meeting_schedule varchar(255),
    teacher_id uuid null,
    secretary_id uuid null,
    constraint cell_teacher_id unique (teacher_id),
    constraint cell_secratary_id unique (secretary_id),
    constraint fk_cell_teacher_id foreign key (teacher_id) references evangelism.user(id),
    constraint fk_cell_secretary_id foreign key (secretary_id) references evangelism.user(id)
);