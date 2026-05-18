package org.example.dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.example.connection.ConnectionFactory;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    public List<T> findAll() {
        // TODO:
        return null;
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public T insert(T t) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        String query = getInsertQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            int index = 1;
            for (Field field : type.getDeclaredFields()) {
                if (field.getName().equalsIgnoreCase("id")) {
                    continue;
                }
                field.setAccessible(true);
                statement.setObject(index++, field.get(t));
            }

            statement.execute();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                Field idField = type.getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(t, id);
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert" + e.getMessage());
            e.printStackTrace();
        } finally {
            ConnectionFactory.close((ResultSet) null);
            ConnectionFactory.close((java.sql.Statement) null);
            ConnectionFactory.close((Connection) null);
        }

        return t;
    }

    private String getInsertQuery() {
        String tableName = type.getSimpleName().toLowerCase();

        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for (Field field : type.getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase("id")) {
                continue;
            }
            if (!columns.isEmpty()) {
                columns.append(", ");
                values.append(", ");
            }

            columns.append(field.getName());
            values.append("?");
        }

        return "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")";
    }

    public T update(T t) {


        return t;
    }
}
