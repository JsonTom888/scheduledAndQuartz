
create table scheduled_cron
(
    id       int auto_increment
        primary key,
    datecron varchar(128) null,
    type     varchar(128) null
);

INSERT INTO test.scheduled_cron (datecron, type) VALUES ('*/3 * * * * ?', 'scheduledCron');
INSERT INTO test.scheduled_cron (datecron, type) VALUES ('*/2 * * * * ?', 'immediately');