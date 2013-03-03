create table leave_type
(
	id							integer			primary key		auto_increment,
	code						varchar(20)		not null,
	description					varchar(1000),
	default_hours				integer			not null
);

create table timesheet_user
(
	id							integer			primary key 	auto_increment,
	one_id						varchar(20)		not null,
	email						varchar(500)	not null,
	name						varchar(100)	not null,
	department					varchar(50)		not null
);

create table supervisor
(
	id							integer			primary key		auto_increment,
	employee_id					integer			not null,
	supervisor_id				integer			not null,
	rank						integer			not null,
	status						integer			not null,
	constraint foreign key (employee_id) references timesheet_user(id) on delete cascade on update cascade,
	constraint foreign key (supervisor_id) references timesheet_user(id) on delete cascade on update cascade
);

create table timesheet
(
	id							integer			primary key		auto_increment,
	timesheet_user_id			integer			not null,
	period_commence_date		date			not null,
	duration					integer			not null,
	planned_approval			datetime,
	planned_approver			integer,
	actual_approval				datetime,
	actual_approver				integer,
	constraint foreign key (timesheet_user_id) references timesheet_user(id),
	constraint foreign key (planned_approver) references timesheet_user(id),
	constraint foreign key (actual_approver) references timesheet_user(id)
);

create table timesheet_day
(
	id							integer			primary key		auto_increment,
	timesheet_id				integer			not null,
	timesheet_day				date			not null,
	planned_start				time			not null,
	planned_finish				time			not null,
	planned_lunch				time			not null,
	planned_leave				time,
	planned_leave_type_id		integer,
	actual_start				time,
	actual_finish				time,
	actual_lunch				time,
	actual_leave				time,
	actual_leave_type_id		integer,
	constraint foreign key (timesheet_id) references timesheet(id) on delete cascade on update cascade,
	constraint foreign key (planned_leave_type_id) references leave_type(id) on delete set null on update cascade,
	constraint foreign key (actual_leave_type_id) references leave_type(id) on delete set null on update cascade
);

create table template
(
	id							integer			primary key		auto_increment,
	timesheet_user_id						integer			not null,
	duration					integer			not null,
	constraint foreign key (timesheet_user_id) references timesheet_user(id)
);

create table template_day
(
	id							integer			primary key		auto_increment,
	template_id					integer			not null,
	planned_start				time			not null,
	planned_finish				time			not null,
	planned_lunch				time			not null,
	planned_leave				time,
	planned_leave_type_id		integer,
	constraint foreign key (template_id) references template(id) on delete cascade on update cascade,
	constraint foreign key (planned_leave_type_id) references leave_type(id) on delete set null on update cascade
);

