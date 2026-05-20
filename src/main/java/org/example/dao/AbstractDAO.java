package org.example.dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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
        return "SELECT " +
                " * " +
                " FROM " +
                type.getSimpleName() +
                " WHERE " + field + " =?";
    }

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> results = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + type.getSimpleName().toLowerCase();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(selectQuery);
            resultSet = statement.executeQuery();

            results = createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return results;
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

        try {
            if (type.isRecord()) {
                java.lang.reflect.RecordComponent[] components = type.getRecordComponents();
                Class<?>[] componentType = Arrays.stream(components).map(RecordComponent::getType).toArray(Class<?>[]::new);
                Constructor<T> recordCtor = type.getDeclaredConstructor(componentType);
                recordCtor.setAccessible(true);

                while (resultSet.next()) {
                    Object[] args = new Object[componentType.length];
                    for (int i = 0; i < componentType.length; i++) {
                        Object value = resultSet.getObject(components[i].getName().toLowerCase());
                        if (value instanceof BigDecimal) {
                            value = ((BigDecimal) value).doubleValue();
                        }
                        if (value instanceof Long) {
                            value = ((Long) value).intValue();
                        }
                        args[i] = value;
                    }
                    list.add(recordCtor.newInstance(args));
                }
            }
            else {
                Constructor[] ctors = type.getDeclaredConstructors();
                Constructor ctor = null;

                for (int i = 0; i < ctors.length; i++) {
                    ctor = ctors[i];
                    if (ctor.getGenericParameterTypes().length == 0)
                        break;
                }

                while (resultSet.next()) {
                    ctor.setAccessible(true);
                    T instance = (T) ctor.newInstance();
                    for (Field field : type.getDeclaredFields()) {
                        String fieldName = field.getName();

                        Object value = resultSet.getObject(fieldName.toLowerCase());

                        if (value != null) {
                            if (value instanceof BigDecimal) {
                                value = ((BigDecimal) value).doubleValue();
                            } else if (value instanceof Long) {
                                value = ((Long) value).intValue();
                            }
                        }

                        field.setAccessible(true);
                        field.set(instance, value);
                    }
                    list.add(instance);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, type.getName() + "DAO:createObjects " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }

    private String createInsertQuery() {
        String tableName = type.getSimpleName().toLowerCase();
        if (type.getSimpleName().equalsIgnoreCase("warehouseOrder")) {
            tableName = "warehouse_order";
        }

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

    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet =  null;

        String query = createInsertQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

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
                if (!type.isRecord()) {
                    Field idField = type.getDeclaredField("id");
                    idField.setAccessible(true);
                    idField.set(t, id);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert" + e.getMessage());
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return t;
    }

    public T update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;

        String tableName = type.getSimpleName().toLowerCase();
        StringBuilder columns = new StringBuilder();

        for (Field field : type.getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase("id")) {
                continue;
            }
            if (!columns.isEmpty()) {
                columns.append(", ");
            }
            columns.append(field.getName().toLowerCase()).append("=?");
        }

        String query = "UPDATE " + tableName + " SET " + columns + " WHERE id=?";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            int index = 1;
            Field idField = null;

            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);

                if (field.getName().equalsIgnoreCase("id")) {
                    idField = field;
                    continue;
                }

                statement.setObject(index++, field.get(t));
            }

            if (idField != null) {
                idField.setAccessible(true);
                statement.setObject(index, idField.get(t));
            }

            statement.execute();
        } catch (Exception e) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, type.getName() + "DAO:update" + e.getMessage());
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return t;
    }

    public T delete(T t) {
        Connection connection = null;
        PreparedStatement statement = null;

        String query = "DELETE FROM " + type.getSimpleName().toLowerCase() + " WHERE id = ?";

        try {
            Field idField = type.getDeclaredField("id");
            idField.setAccessible(true);

            Object idValue = idField.get(t);

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            statement.setObject(1, idValue);

            statement.execute();
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }
}
