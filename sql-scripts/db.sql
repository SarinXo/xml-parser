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
    isactual    BOOLEAN,
    isactive    BOOLEAN
);