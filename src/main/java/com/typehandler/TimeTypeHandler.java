package com.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;

public class TimeTypeHandler extends BaseTypeHandler<Date> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        long time = date.getTime();
        Timestamp timestamp = new Timestamp(time);
        preparedStatement.setTimestamp(i,timestamp);
    }

    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(s);
        long time = timestamp.getTime();
        Date date = new Date(time);
        return date;
    }

    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(i);
        long time = timestamp.getTime();
        Date date = new Date(time);
        return date;
    }

    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Timestamp timestamp = callableStatement.getTimestamp(i);
        long time = timestamp.getTime();
        Date date = new Date(time);
        return date;
    }
}
