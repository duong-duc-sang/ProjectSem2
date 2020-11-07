CREATE TABLE AP_User
(
    AP_User_ID numeric(10)                     not null
        constraint ad_user_pkey primary key,
    name       varchar(40)                     not null,
    value      varchar(40)                     not null,
    FullName varchar(250),
    Email varchar(250),
    Tel_no varchar(20),
    created    timestamp   default statement_timestamp(),
    createdby  numeric(10) default NULL::numeric,
    updated    timestamp   default statement_timestamp(),
    updatedBy  numeric(10) default NULL::numeric,
    isactive   char        default 'Y'::bpchar not null,
    IsDeleted  char        default 'N'::bpchar not null
);

CREATE TABLE HIS_PatientHistory
(
    HIS_PatientHistory_ID numeric(10)                     not null
        constraint HIS_PatientHistory_pkey primary key,
    name                  varchar(250)                    not null,
    value                 varchar(40)                     not null,
    HIS_PatientDocument   varchar(40)                     not null,
    Address               varchar(250),
    Tel_No                varchar(20),
    ID_No                 varchar(20),
    BirthDay timestamp not null ,
    HIS_Department_ID     numeric(10),
    age numeric(3),
    HIS_Gender varchar(10),
    regDate timestamp,
    timeGoin timestamp,
    timeGoOut timestamp,

    created               timestamp   default statement_timestamp(),
    createdby             numeric(10) default NULL::numeric,
    updated               timestamp   default statement_timestamp(),
    updatedBy             numeric(10) default NULL::numeric,
    isactive              char        default 'Y'::bpchar not null,
    IsDeleted             char        default 'N'::bpchar not null
);


CREATE TABLE HIS_Invoice
(
    HIS_Invoice           numeric(10)                     not null
        constraint his_invoice_pkey primary key,
    HIS_PatientHistory_ID numeric(10)                     not null
        constraint PatientHistory_ivoice
            references HIS_PatientHistory,
    HIS_PatientDocument   varchar(40)                     not null,
    PatientValue          varchar(40)                     not null,
    ToltalPrice           numeric     default 0::numeric,
    AMount                numeric     default 0::numeric,
    IsPaid                char        default 'N'         not null
        constraint his_invoice_ispaid_check
            check (ispaid = ANY (ARRAY ['Y'::bpchar, 'N'::bpchar])),


    created               timestamp   default statement_timestamp(),
    createdby             numeric(10) default NULL::numeric,
    updated               timestamp   default statement_timestamp(),
    updatedBy             numeric(10) default NULL::numeric,
    isactive              char        default 'Y'::bpchar not null,
    IsDeleted             char        default 'N'::bpchar not null
);

CREATE TABLE HIS_Patient_Service
(
    HIS_Patient_Service_ID           numeric(10)                     not null
        constraint HIS_Patient_Service_pkey primary key,
    HIS_PatientHistory_ID numeric(10)                     not null
        constraint Patient_Service
            references HIS_PatientHistory,
    HIS_PatientDocument   varchar(40)                     not null,
    PatientValue          varchar(40)                     not null,
    Quantity numeric default 0::numeric,
    UnitPrice numeric,
    ToltalPrice           numeric     default 0::numeric,
    AMount                numeric     default 0::numeric,
    IsPaid                char        default 'N'         not null
        constraint his_invoice_ispaid_check
            check (ispaid = ANY (ARRAY ['Y'::bpchar, 'N'::bpchar])),

    HIS_Room_ID numeric(10),
    HIS_Invoice_ID numeric(10),
    HIS_Service_ID numeric(10),
    ServiceName varchar(500),
    Value varchar(40),
    Docdate timestamp,
    ActDate timestamp,
    sequenceNo numeric,
    created               timestamp   default statement_timestamp(),
    createdby             numeric(10) default NULL::numeric,
    updated               timestamp   default statement_timestamp(),
    updatedBy             numeric(10) default NULL::numeric,
    isactive              char        default 'Y'::bpchar not null,
    IsDeleted             char        default 'N'::bpchar not null
);

CREATE TABLE HIS_Service
(
    HIS_Service_ID           numeric(10)                     not null
        constraint HIS_Service_pkey primary key,
    name                  varchar(500)                    not null,
    value                 varchar(40)                     not null,
    UnitPrice numeric,

    created               timestamp   default statement_timestamp(),
    createdby             numeric(10) default NULL::numeric,
    updated               timestamp   default statement_timestamp(),
    updatedBy             numeric(10) default NULL::numeric,
    isactive              char        default 'Y'::bpchar not null,
    IsDeleted             char        default 'N'::bpchar not null
);

CREATE TABLE HIS_Room
(
    HIS_Room_ID           numeric(10)                     not null
        constraint HIS_Room_pkey primary key,
    name                  varchar(500)                    not null,
    value                 varchar(40)                     not null,
    Location VARCHAR(500),

    created               timestamp   default statement_timestamp(),
    createdby             numeric(10) default NULL::numeric,
    updated               timestamp   default statement_timestamp(),
    updatedBy             numeric(10) default NULL::numeric,
    isactive              char        default 'Y'::bpchar not null,
    IsDeleted             char        default 'N'::bpchar not null
);



drop table HIS_Patient_Service; cascade ;


