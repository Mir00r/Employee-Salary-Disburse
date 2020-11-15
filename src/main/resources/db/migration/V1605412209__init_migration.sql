# -- users
# create table if not exists m_users
# (
#     id                      bigint auto_increment
#         primary key,
#     created                 datetime     null,
#     deleted                 bit          not null,
#     last_updated            datetime     null,
#     uuid_str                varchar(255) null,
#     account_non_expired     bit          not null,
#     account_non_locked      bit          not null,
#     credentials_non_expired bit          not null,
#     email                   varchar(255) null,
#     enabled                 bit          not null,
#     gender                  varchar(255) not null,
#     name                    varchar(255) not null,
#     password                varchar(512) not null,
#     phone                   varchar(255) null,
#     username                varchar(255) not null,
#     created_by_id           bigint       null,
#     updated_by_id           bigint       null
# )
#     engine = MyISAM;
#
# create index FKluks1h3sxcrnepqqqa4s9orjf
#     on m_users (updated_by_id);
#
# create index FKrauvhfwxl9xfjhvc2wc2foj9f
#     on m_users (created_by_id);
#
# -- Roles
#
# create table if not exists roles
# (
#     id            bigint auto_increment
#         primary key,
#     created       datetime     null,
#     deleted       bit          not null,
#     last_updated  datetime     null,
#     uuid_str      varchar(255) null,
#     name          varchar(255) not null,
#     restricted    bit          not null,
#     created_by_id bigint       null,
#     updated_by_id bigint       null
# )
#     engine = MyISAM;
#
# create index FK95jx57baw7c39ybblooejidye
#     on roles (updated_by_id);
#
# create index FKolo7v6bxrho3bgjfh10i4y4lb
#     on roles (created_by_id);
#
# -- users role
#
# create table if not exists m_users_roles
# (
#     user_id  bigint not null,
#     roles_id bigint not null
# )
#     engine = MyISAM;
#
# create index FK143dfv8wd8b2w93gbvd84ps5k
#     on m_users_roles (user_id);
#
# create index FK5hwrg9mw7hmdq8cn63mh7sx0j
#     on m_users_roles (roles_id);
#
# -- Privileges
#
# create table if not exists privileges
# (
#     id            bigint auto_increment
#         primary key,
#     created       datetime     null,
#     deleted       bit          not null,
#     last_updated  datetime     null,
#     uuid_str      varchar(255) null,
#     label         varchar(255) not null,
#     name          varchar(255) not null,
#     created_by_id bigint       null,
#     updated_by_id bigint       null
# )
#     engine = MyISAM;
#
# create index FKewlbnm1g24vnjeuxlsk681k7j
#     on privileges (created_by_id);
#
# create index FKj8t6kkmks6qe3dluky108cku
#     on privileges (updated_by_id);
#
# -- Privileges Access
# create table if not exists privileges_access_urls
# (
#     privilege_id bigint       not null,
#     access_urls  varchar(255) null
# )
#     engine = MyISAM;
#
# create index FKp123f0u3yvp9ygtxbc1kmff5d
#     on privileges_access_urls (privilege_id);
#
#
# -- Roles Privileges
# create table if not exists roles_privileges
# (
#     role_id      bigint not null,
#     privilege_id bigint not null
# )
#     engine = MyISAM;
#
# create index FK5duhoc7rwt8h06avv41o41cfy
#     on roles_privileges (privilege_id);
#
# create index FK629oqwrudgp5u7tewl07ayugj
#     on roles_privileges (role_id);
#
# -- Profiles
# create table if not exists profiles
# (
#     id                 bigint auto_increment
#         primary key,
#     created            datetime     null,
#     deleted            bit          not null,
#     last_updated       datetime     null,
#     uuid_str           varchar(255) null,
#     age                varchar(255) null,
#     birthday           datetime     not null,
#     blood_group        varchar(255) null,
#     gender             varchar(255) null,
#     name               varchar(255) null,
#     photo              varchar(255) null,
#     photo_identity_url varchar(255) null,
#     created_by_id      bigint       null,
#     updated_by_id      bigint       null,
#     user_id            bigint       null
# )
#     engine = MyISAM;
#
# create index FK8hlv96njo618dbfk4qfbwiqma
#     on profiles (updated_by_id);
#
# create index FKi5ltfjqx7f5lps1ccggx9vlqo
#     on profiles (created_by_id);
#
# create index FKonpp2s5vianwi6kh3800cqriv
#     on profiles (user_id);
#
# -- Upload Files
#
# create table if not exists uploaded_files
# (
#     id              bigint auto_increment
#         primary key,
#     created         datetime     null,
#     deleted         bit          not null,
#     last_updated    datetime     null,
#     uuid_str        varchar(255) null,
#     file_name       varchar(255) null,
#     file_type       varchar(255) null,
#     namespace       varchar(255) null,
#     root_path       varchar(255) null,
#     unique_property varchar(255) null,
#     created_by_id   bigint       null,
#     updated_by_id   bigint       null
# )
#     engine = MyISAM;
#
# create index FK3l4mn8nrmdehfwahgltoks90r
#     on uploaded_files (created_by_id);
#
# create index FKdcduscnqybev8lktog9l0lycx
#     on uploaded_files (updated_by_id);
#
# -- Firebase user token
#
# create table if not exists firebase_user_token
# (
#     id            bigint auto_increment
#         primary key,
#     created       datetime     null,
#     deleted       bit          not null,
#     last_updated  datetime     null,
#     uuid_str      varchar(255) null,
#     user_token    varchar(255) not null,
#     created_by_id bigint       null,
#     updated_by_id bigint       null,
#     user_id       bigint       null
# )
#     engine = MyISAM;
#
# create index FKa59j1qrnpqwhsaj8p6xonwsl
#     on firebase_user_token (created_by_id);
#
# create index FKhnd7h4lpfmd992kbd5nbgejq3
#     on firebase_user_token (updated_by_id);
#
# create index FKijg4ywkf6r83uvyp905o6omwc
#     on firebase_user_token (user_id);
#
# -- Ac validation token
#
# create table if not exists ac_validation_tokens
# (
#     id                bigint auto_increment
#         primary key,
#     created           datetime     null,
#     deleted           bit          not null,
#     last_updated      datetime     null,
#     uuid_str          varchar(255) null,
#     token             varchar(255) null,
#     token_valid       bit          not null,
#     token_valid_until datetime     null,
#     reason            varchar(255) null,
#     username          varchar(255) null,
#     created_by_id     bigint       null,
#     updated_by_id     bigint       null,
#     user_id           bigint       null
# )
#     engine = MyISAM;
#
# create index FK5c0f4fivjiyy19j4f0fxayyc3
#     on ac_validation_tokens (updated_by_id);
#
# create index FKi746ajdn1vcbojvapsq6jsc9u
#     on ac_validation_tokens (user_id);
#
# create index FKqamdf34eo911js0e19a36p6v2
#     on ac_validation_tokens (created_by_id);
#
# -- Activity log tag
#
# create table if not exists activity_log_tags
# (
#     activity_id bigint       not null,
#     tags        varchar(255) null
# )
#     engine = MyISAM;
#
# create index FKfb2vr91njjibja7ww0isnd4bl
#     on activity_log_tags (activity_id);
#
# -- Activity logs
#
# create table if not exists activity_logs
# (
#     id             bigint auto_increment
#         primary key,
#     created        datetime     null,
#     deleted        bit          not null,
#     last_updated   datetime     null,
#     uuid_str       varchar(255) null,
#     expires        varchar(255) null,
#     ip             varchar(255) null,
#     request_method varchar(255) null,
#     total_visitors bigint       null,
#     url            varchar(255) null,
#     user_agent     varchar(255) null,
#     created_by_id  bigint       null,
#     updated_by_id  bigint       null,
#     user_id        bigint       null
# )
#     engine = MyISAM;
#
# create index FK73c1vlvjh3dxqoy3txow3m48p
#     on activity_logs (created_by_id);
#
# create index FKi1mahmfxp15s84l940o3nxg19
#     on activity_logs (user_id);
#
# create index FKornhymyu3fpfciwwp3caqvw7o
#     on activity_logs (updated_by_id);
#
# -- Bank
#
# create table if not exists banks
# (
#     id            bigint auto_increment
#         primary key,
#     created       datetime     null,
#     deleted       bit          not null,
#     last_updated  datetime     null,
#     uuid_str      varchar(255) null,
#     address       varchar(255) null,
#     balance       double       not null,
#     branch_name   varchar(255) not null,
#     name          varchar(255) not null,
#     created_by_id bigint       null,
#     updated_by_id bigint       null
# )
#     engine = MyISAM;
#
# create index FK37nqwey0a8hwaptdw06mmoeb
#     on banks (updated_by_id);
#
# create index FK3qwta269hu0aedeh25mw609cc
#     on banks (created_by_id);
#
# -- Bank Account
#
# create table if not exists bankaccounts
# (
#     id              bigint auto_increment
#         primary key,
#     created         datetime     null,
#     deleted         bit          not null,
#     last_updated    datetime     null,
#     uuid_str        varchar(255) null,
#     account_number  varchar(255) not null,
#     account_type    tinyint      not null,
#     current_balance double       not null,
#     name            varchar(255) not null,
#     created_by_id   bigint       null,
#     updated_by_id   bigint       null,
#     bank_id         bigint       null
# )
#     engine = MyISAM;
#
# create index FKfe2pwebrvd79oso893deem5gs
#     on bankaccounts (created_by_id);
#
# create index FKr5j7lbmum1o49vjrxvy39r34k
#     on bankaccounts (updated_by_id);
#
# create index FKte6laqwr05powh6j2qnae6hrk
#     on bankaccounts (bank_id);
#
# -- Company
#
# create table if not exists companys
# (
#     id                bigint auto_increment
#         primary key,
#     created           datetime     null,
#     deleted           bit          not null,
#     last_updated      datetime     null,
#     uuid_str          varchar(255) null,
#     address           varchar(255) null,
#     name              varchar(255) not null,
#     phone             varchar(255) not null,
#     created_by_id     bigint       null,
#     updated_by_id     bigint       null,
#     bank_account_id   bigint       null,
#     total_paid_salary double       not null
# )
#     engine = MyISAM;
#
# create index FK3mlkl7sdrm7gljwnneg66cva9
#     on companys (updated_by_id);
#
# create index FKleodu5hnrbg4jfnnctv4lhs8y
#     on companys (bank_account_id);
#
# create index FKm8tw5fi07543w0v12w6r423n1
#     on companys (created_by_id);
#
# -- Employee
#
# create table if not exists employees
# (
#     id                bigint auto_increment
#         primary key,
#     created           datetime     null,
#     deleted           bit          not null,
#     last_updated      datetime     null,
#     uuid_str          varchar(255) null,
#     address           varchar(255) null,
#     grad              tinyint      not null,
#     name              varchar(255) not null,
#     phone             varchar(255) not null,
#     created_by_id     bigint       null,
#     updated_by_id     bigint       null,
#     bank_account_id   bigint       null,
#     company_id        bigint       null,
#     basic_salary      double       not null,
#     house_rent        double       not null,
#     medical_allowance double       not null
# )
#     engine = MyISAM;
#
# create index FK6k4e2a44gnyak4ceuw6e9amg4
#     on employees (updated_by_id);
#
# create index FK744474r30idiggpxqqdr3xdq4
#     on employees (created_by_id);
#
# create index FKfvchedchveahj8f4pp8phvohr
#     on employees (company_id);
#
# create index FKneueao5gyc5xphaxhyskiu9or
#     on employees (bank_account_id);
