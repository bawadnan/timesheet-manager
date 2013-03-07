create table config
(
    id                          integer            primary key    auto_increment,
    config_option               varchar(100)       not null       unique,
    config_value                varchar(250)       not null
);

create table leave_type
(
    id                          integer            primary key    auto_increment,
    code                        varchar(20)        not null       unique,
    description                 varchar(1000),
    default_hours               integer            not null
);

insert into leave_type (code, description, default_hours)
values('Annual', 'Annual Leave', 7);
insert into leave_type (code, description, default_hours)
values('Concessional', 'Concessional Leave', 7);
insert into leave_type (code, description, default_hours)
values('Conference', 'Conference Leave', 7);
insert into leave_type (code, description, default_hours)
values('Holiday', 'Holiday', 7);
insert into leave_type (code, description, default_hours)
values('Long Service', 'Long Service Leave', 7);
insert into leave_type (code, description, default_hours)
values('RDO', 'Rostered Day Off', 7);
insert into leave_type (code, description, default_hours)
values('Rostered Weekend', 'Rostered Weekend', 7);
insert into leave_type (code, description, default_hours)
values('Sick', 'Sick Leave', 7);
insert into leave_type (code, description, default_hours)
values('Sick-Cert', 'Sick Leave with Medical Certificate', 7);
insert into leave_type (code, description, default_hours)
values('Training', 'Training Leave', 7);
insert into leave_type (code, description, default_hours)
values('Variable Public', 'Variable Public Leave', 7);

create table timesheet_user
(
    id                          integer            primary key     auto_increment,
    one_id                      varchar(20)        not null,
    email                       varchar(250)       not null,
    name                        varchar(100)       not null,
    department                  varchar(50)        not null,
    status                      varchar(20)        not null,
    time_accrued                integer,
    time_forfeited              integer,
    unique(one_id),
    unique(email)
);

create table supervisor
(
    id                          integer            primary key     auto_increment,
    employee_id                 integer            not null,
    supervisor_id               integer            not null,
    rank                        integer            not null,
    status                      integer            not null,
    constraint foreign key (employee_id) references timesheet_user(id) on delete cascade on update cascade,
    constraint foreign key (supervisor_id) references timesheet_user(id) on delete cascade on update cascade
);

create table timesheet
(
    id                          integer            primary key     auto_increment,
    timesheet_user_id           integer            not null,
    period_commence_date        date               not null,
    duration                    integer            not null,
    status                      varchar(20)        not null,
    constraint foreign key (timesheet_user_id) references timesheet_user(id),
    unique(timesheet_user_id, period_commence_date)
);

create table timesheet_log
(
    id                          integer            primary key     auto_increment,
    timesheet_id                integer            not null,
    log                         varchar(500)       not null,
    constraint foreign key (timesheet_id) references timesheet(id) on delete cascade on update cascade
);

create table timesheet_day
(
    id                          integer            primary key     auto_increment,
    timesheet_id                integer            not null,
    timesheet_day               datetime           not null,
    planned_start               datetime           not null,
    planned_finish              datetime           not null,
    planned_lunch               integer,
    planned_leave               integer,
    planned_leave_type_id       integer,
    actual_start                datetime,
    actual_finish               datetime,
    actual_lunch                integer,
    actual_leave                integer,
    actual_leave_type_id        integer,
    constraint foreign key (timesheet_id) references timesheet(id) on delete cascade on update cascade,
    constraint foreign key (planned_leave_type_id) references leave_type(id),
    constraint foreign key (actual_leave_type_id) references leave_type(id),
    unique(timesheet_id, timesheet_day)
);

create table template
(
    id                          integer            primary key    auto_increment,
    timesheet_user_id           integer            not null,
    duration                    integer            not null,
    constraint foreign key (timesheet_user_id) references timesheet_user(id)
);

create table template_day
(
    id                          integer            primary key    auto_increment,
    template_id                 integer            not null,
    planned_start               datetime           not null,
    planned_finish              datetime           not null,
    planned_lunch               integer,
    planned_leave               integer,
    planned_leave_type_id       integer,
    constraint foreign key (template_id) references template(id) on delete cascade on update cascade,
    constraint foreign key (planned_leave_type_id) references leave_type(id) on delete set null on update cascade
);
