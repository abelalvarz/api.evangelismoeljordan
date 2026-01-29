create table if not exists evangelism.report(
    id uuid primary key default uuid_generate_v4(),
    host_name varchar(255) null,
    meeting_date date not null,
    cell_id uuid not null,
    created_at timestamp default current_timestamp,
    created_by uuid not null,
    updated_at timestamp default current_timestamp,
    updated_by uuid null
);
create table if not exists evangelism.report_attendance_detail(
    id uuid primary key default uuid_generate_v4(),
    report_id uuid not null,
    active_members int not null default 0,
    active_members_children int not null default 0,
    inactive_members int not null default 0,
    inactive_members_children int not null default 0,
    adults_visitors int not null default 0,
    children_visitors int not null default 0,
    total_attendance int not null default 0,
    constraint fk_rep_attend_detail_rep_id foreign key (report_id) references evangelism.report(id) on delete cascade
);
create table if not exists evangelism.report_evangelism_detail(
    id uuid primary key default uuid_generate_v4(),
    report_id uuid not null,
    vigil_attendance int not null default 0,
    visited_homes int not null default 0,
    new_christian int not null default 0,
    reconciled_people int not null default 0,
    constraint fk_rep_evang_detail_rep_id foreign key (report_id)  references evangelism.report(id) on delete cascade
);

create table if not exists evangelism.report_finance_detail(
    id uuid primary key default uuid_generate_v4(),
    report_id uuid not null,
    offering_amount numeric(38, 2) default 0,
    observations text null,
    constraint fk_rep_financ_detail_rep_id foreign key (report_id)  references evangelism.report(id) on delete cascade
);