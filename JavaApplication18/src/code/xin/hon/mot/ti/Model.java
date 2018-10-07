/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.xin.hon.mot.ti;

import myannotation.MyId;
import myannotation.AutoGenerate;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import myannotation.Entity;

/**
 *
 * @author nguye
 * @param <T>
 */
public class Model<T> {

    private Class<T> clazz;

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Model(Class<T> clazz) {
        this.clazz = clazz;
    }

    public ArrayList<T> getList() {
        ArrayList<T> list = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select * from ");
        sqlBuilder.append(getTableName());
        try {
            Statement stt = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = stt.executeQuery(sqlBuilder.toString());
            while (rs.next()) {
                T obj = this.clazz.newInstance();
                Field[] fields = this.clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.getType().getSimpleName().equalsIgnoreCase("string")) {
                        field.set(obj, rs.getString(field.getName()));
                    } else if (field.getType().getSimpleName().equalsIgnoreCase("int")) {
                        field.set(obj, rs.getInt(field.getName()));
                    }
                }
                list.add(obj);
            }
        } catch (SQLException e) {
            System.err.println("Không thể kết nối tới database. " + e.getMessage());
        } catch (InstantiationException | IllegalAccessException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Lỗi khi khởi tạo đối tượng với lớp " + this.clazz.getSimpleName());;
        }

        return list;
    }

    public void save(T obj) throws SQLException {
        Statement stt = DBConnection.getInstance().getConnection()
                .createStatement();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("insert into ")
                .append(getTableName())
                .append(" ")
                .append(getFieldsName())
                .append(" ")
                .append("values ")
                .append(getFieldsValue(obj));
        stt.execute(sqlBuilder.toString());
    }

    public String getFieldsValue(T obj) {
        StringBuilder fieldsValueBuilder = new StringBuilder();
        fieldsValueBuilder.append("(");
        try {
            for (Field f : this.clazz.getDeclaredFields()) {
                f.setAccessible(true);
                if (f.isAnnotationPresent(MyId.class)
                        && f.isAnnotationPresent(AutoGenerate.class)) {
                    continue;
                }
                if (f.getType().getSimpleName().equals("String")) {
                    fieldsValueBuilder.append("'").append(f.get(obj)).append("'").append(",");
                } else if (f.getType().getSimpleName().equals("int")) {
                    fieldsValueBuilder.append(f.get(obj)).append(",");
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException e) {
            System.err.println(e.getMessage());
        }
        fieldsValueBuilder.deleteCharAt(fieldsValueBuilder.length() - 1);
        fieldsValueBuilder.append(")");
        return fieldsValueBuilder.toString();
    }

    public String getFieldsName() {
        StringBuilder fieldsNameBuilder = new StringBuilder();
        fieldsNameBuilder.append("(");
        for (Field f : this.clazz.getDeclaredFields()) {
            if (f.isAnnotationPresent(MyId.class)
                    && f.isAnnotationPresent(AutoGenerate.class)) {
                continue;
            }
            fieldsNameBuilder.append(f.getName()).append(",");
        }
        fieldsNameBuilder.deleteCharAt(fieldsNameBuilder.length() - 1);
        fieldsNameBuilder.append(")");
        return fieldsNameBuilder.toString();
    }

    public String getTableName() {
        if (this.clazz.isAnnotationPresent(Entity.class)) {
            Entity entity = (Entity) this.clazz.getAnnotation(Entity.class);
            return entity.tableName();
        }
        return this.clazz.getSimpleName().toLowerCase();
    }
}
