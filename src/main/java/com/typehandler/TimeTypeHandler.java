package com.typehandler;

import com.enity.MyDate;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;


import java.sql.*;

public class TimeTypeHandler extends BaseTypeHandler<MyDate> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, MyDate date, JdbcType jdbcType) throws SQLException {
        long time = date.getTime();
        Timestamp timestamp = new Timestamp(time);
        preparedStatement.setTimestamp(i,timestamp);
    }

    @Override
    public MyDate getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(s);
        long time = timestamp.getTime();
        MyDate date = new MyDate(time);
        return date;
    }

    @Override
    public MyDate getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(i);
        long time = timestamp.getTime();
        MyDate date = new MyDate(time);
        return date;
    }

    @Override
    public MyDate getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Timestamp timestamp = callableStatement.getTimestamp(i);
        long time = timestamp.getTime();
        MyDate date = new MyDate(time);
        return date;
    }
}
