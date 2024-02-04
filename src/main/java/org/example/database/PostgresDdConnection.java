package org.example.database;

import org.example.dto.XmlObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PostgresDdConnection implements AutoCloseable{
    private final static String DATABASE_URL="jdbc:postgresql://localhost:17017/postgres?schema=TEST";
    private final static String USER="user";
    private final static String PASSWORD="pswd";
    private Connection connection;

    public PostgresDdConnection() {
        try {
            this.connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        }catch (SQLException e){
            System.out.printf("Err with creating jdbc connection URL=" + DATABASE_URL
                    + " user=" + USER
                    + " password=" + PASSWORD + "%n");
        }
    }

    public boolean insertData(List<XmlObject> listEntities) {
        try {
            PreparedStatement query = connection.prepareStatement("INSERT INTO TEST.DATA " +
                    "(id, " +
                    "objectid, " +
                    "objectguid, " +
                    "changeid, " +
                    "name, " +
                    "typename, " +
                    "level, " +
                    "opertypeid, " +
                    "previd, " +
                    "nextid, " +
                    "updatedate, " +
                    "startdate, " +
                    "enddate, " +
                    "isactual, " +
                    "isactive) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

            for (XmlObject entity : listEntities) {
                query.setObject(1, entity.getId());
                query.setObject(2, entity.getObjectid());
                query.setObject(3, entity.getObjectguid());
                query.setObject(4, entity.getChangeid());
                query.setObject(5, entity.getName());
                query.setObject(6, entity.getTypename());
                query.setObject(7, entity.getLevel());
                query.setObject(8, entity.getOpertypeid());
                query.setObject(9, entity.getPrevid());
                query.setObject(10, entity.getNextid());
                query.setObject(11, entity.getUpdatedate());
                query.setObject(12, entity.getStartdate());
                query.setObject(13, entity.getEnddate());
                query.setObject(14, entity.getIsactual());
                query.setObject(15, entity.getIsactive());

                query.addBatch();
            }
            query.executeBatch();
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
