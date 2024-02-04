CREATE SCHEMA IF NOT EXISTS TEST;

CREATE TABLE IF NOT EXISTS TEST.DATA(
    id          SERIAL PRIMARY KEY,
    objectid    BIGINT,
    objectguid  UUID,
    changeid    BIGINT,
    name        VARCHAR(100),
    typename    VARCHAR(50),
    level       BIGINT,
    opertypeid  BIGINT,
    previd      BIGINT,
    nextid      BIGINT,
    updatedate  DATE,
    startdate   DATE,
    enddate     DATE,
    isactual    INT,
    isactive    INT
);

INSERT INTO TEST.DATA
    (id, objectid, objectguid, changeid, name, typename, level, opertypeid, previd, nextid, updatedate, startdate, enddate, isactual, isactive)
VALUES (1890362, 177734229, 'd9ac12b4-ced8-4190-9ba3-b20f1a5c757a'::uuid, , 'Шейн-Майданское', 'с.п.', 4, 30, 1805228, 0, '2021-02-12'::date, '2021-02-12'::date, '2079-06-06'::date, 1, 0)
