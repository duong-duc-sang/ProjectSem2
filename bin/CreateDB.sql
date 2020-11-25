CREATE TABLE AP_User
(
    AP_User_ID numeric(10)
        constraint ad_user_pkey primary key,
    Name       varchar(40)                     not null,
    Value      varchar(40),
    password   varchar(20)                     not null,
    FullName   varchar(250),
    Email      varchar(250),
    Tel_no     varchar(20),
    Created    timestamp   default statement_timestamp(),
    Createdby  numeric(10) default NULL::numeric,
    Updated    timestamp   default statement_timestamp(),
    UpdatedBy  numeric(10) default NULL::numeric,
    IsActive   char        default 'Y'::bpchar not null,
    IsDeleted  char        default 'N'::bpchar not null
);

CREATE TABLE HIS_PatientHistory
(
    HIS_PatientHistory_ID numeric(10)                     not null
        constraint HIS_PatientHistory_pkey primary key,
    Name                  varchar(250)                    not null,
    Value                 varchar(40)                     not null,
    PatientDocument       varchar(40)                     not null,
    Address               varchar(250),
    Gender                varchar(10),
    Tel_No                varchar(20),
    ID_No                 varchar(20),
    BirthDay              timestamp                       not null,
    BirthdayStr           varchar(20),
    Age                   numeric(3),

    Department_ID         numeric(10),


    TimeGoIn              timestamp,
    TimeGoOut             timestamp,

    Created               timestamp   default statement_timestamp(),
    Createdby             numeric(10) default NULL::numeric,
    Updated               timestamp   default statement_timestamp(),
    UpdatedBy             numeric(10) default NULL::numeric,
    IsActive              char        default 'Y'::bpchar not null,
    IsDeleted             char        default 'N'::bpchar not null
);


CREATE TABLE HIS_Invoice
(
    HIS_Invoice_ID        numeric(10)                     not null
        constraint his_invoice_pkey primary key,
    HIS_PatientHistory_ID numeric(10)                     not null
        constraint PatientHistory_ivoice
            references HIS_PatientHistory,
    TotalPrice            numeric     default 0::numeric,
    AMount                numeric     default 0::numeric,
    IsPaid                char        default 'N'         not null
        constraint his_invoice_ispaid_check
            check (ispaid = ANY (ARRAY ['Y'::bpchar, 'N'::bpchar])),

    PayTime               timestamp   default NULL::TIMESTAMP,
    Cashier_User_ID       numeric(10) default NULL::numeric,
    Status                varchar(20),
    Created               timestamp   default statement_timestamp(),
    Createdby             numeric(10) default NULL::numeric,
    Updated               timestamp   default statement_timestamp(),
    UpdatedBy             numeric(10) default NULL::numeric,
    IsActive              char        default 'Y'::bpchar not null,
    IsDeleted             char        default 'N'::bpchar not null
);

CREATE TABLE HIS_Patient_Service
(
    HIS_Patient_Service_ID numeric(10)                     not null
        constraint HIS_Patient_Service_pkey primary key,
    HIS_PatientHistory_ID  numeric(10)                     not null
        constraint Patient_Service
            references HIS_PatientHistory,
    Quantity               numeric     default 0::numeric,
    UnitPrice              numeric,
    TotalPrice             numeric     default 0::numeric,
    AMount                 numeric     default 0::numeric,
    IsPaid                 char        default 'N'         not null
        constraint his_invoice_ispaid_check
            check (ispaid = ANY (ARRAY ['Y'::bpchar, 'N'::bpchar])),

    HIS_Room_ID            numeric(10),
    HIS_Invoice_ID         numeric(10),
    HIS_Service_ID         numeric(10),
    Docdate                timestamp,
    ActDate                timestamp,
    SequenceNo             numeric,
    Created                timestamp   default statement_timestamp(),
    Createdby              numeric(10) default NULL::numeric,
    Updated                timestamp   default statement_timestamp(),
    UpdatedBy              numeric(10) default NULL::numeric,
    IsActive               char        default 'Y'::bpchar not null,
    IsDeleted              char        default 'N'::bpchar not null
);

CREATE TABLE HIS_Service
(
    HIS_Service_ID numeric(10)                     not null
        constraint HIS_Service_pkey primary key,
    name           varchar(500)                    not null,
    value          varchar(40)                     not null,
    UnitPrice      numeric,
    ServiceType    varchar(50),
    Created        timestamp   default statement_timestamp(),
    Createdby      numeric(10) default NULL::numeric,
    Updated        timestamp   default statement_timestamp(),
    UpdatedBy      numeric(10) default NULL::numeric,
    IsActive       char        default 'Y'::bpchar not null,
    IsDeleted      char        default 'N'::bpchar not null
);

CREATE TABLE HIS_Room
(
    HIS_Room_ID numeric(10)                     not null
        constraint HIS_Room_pkey primary key,
    name        varchar(500)                    not null,
    value       varchar(40)                     not null,
    Location    VARCHAR(500),

    Created     timestamp   default statement_timestamp(),
    Createdby   numeric(10) default NULL::numeric,
    Updated     timestamp   default statement_timestamp(),
    UpdatedBy   numeric(10) default NULL::numeric,
    IsActive    char        default 'Y'::bpchar not null,
    IsDeleted   char        default 'N'::bpchar not null
);


CREATE TABLE AP_Role
(
    AP_Role_ID numeric(10)                     not null
        constraint AP_Role_pkey primary key,
    name       varchar(500)                    not null,
    Created    timestamp   default statement_timestamp(),
    Createdby  numeric(10) default NULL::numeric,
    Updated    timestamp   default statement_timestamp(),
    UpdatedBy  numeric(10) default NULL::numeric,
    IsActive   char        default 'Y'::bpchar not null,
    IsDeleted  char        default 'N'::bpchar not null
);

CREATE TABLE AP_User_Roles
(
    AP_User_Roles_ID numeric(10)                     not null
        constraint AP_UserRole_pkey primary key,
    AP_User_ID       numeric(10)                     not null
        constraint User_UserRoles
            references AP_User,
    AP_Role_ID       numeric(10)                     not null
        constraint Role_UserRoles
            references AP_Role,
    Created          timestamp   default statement_timestamp(),
    Createdby        numeric(10) default NULL::numeric,
    Updated          timestamp   default statement_timestamp(),
    UpdatedBy        numeric(10) default NULL::numeric,
    IsActive         char        default 'Y'::bpchar not null,
    IsDeleted        char        default 'N'::bpchar not null
);