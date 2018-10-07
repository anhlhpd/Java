/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package again.reflection;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import myAnnotation.Entity;

/**
 *
 * @author nguye
 */
public class Model<T> {

    public static void main(String[] args) throws SQLException {
        Model model = new Model();
        Customer cus = new Customer("Hung");
        Student st = new Student("Xuan Hung", "A0213");

        Model<Student> studentModel = new Model<>();

        studentModel.save(st);
        System.out.println("----");
        model.save(cus);
    }

    // Hàm save với nhiệm vụ lưu đối tượng vào database
    public void saveWithOptimize(T obj) {
        // 1. Tạo kết nối tới database.
        // 2. Thêm câu lệnh vào
        // 2.1 Tạo ra câu lệnh:
        // insert into students (name, rollNumber)
        // values ('Hùng', 'A0001')

        String fieldsValue = getFieldsValue(obj);
        String tableName = getTableName(obj);
        String fieldsName = getFieldsName(obj.getClass().getDeclaredFields());
        String sqlString = "insert into " + tableName + fieldsName + "values" + fieldsValue;

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/reflection", "", "");
            Statement stt = connection.createStatement();
            stt.execute(sqlString);
        } catch (SQLException ex) {
            System.out.println("");
        }
    }

    public ArrayList<T> getList(T obj) {
        ArrayList<T> listObject = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/reflection?useUnicode=true&characterEncoding=utf-8", "root", "");
            String tableName = getTableName(obj);
            
            String sql = "SELECT * FROM " + tableName;
            Field[] fields = obj.getClass().getDeclaredFields();
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
//            Statement stt = connection.createStatement();
//            ResultSet rs = stt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                for (int i = 0; i < fields.length; i++) {
                    Field f = fields[i];
                    if (rs.next()) {
                        f.setAccessible(true);
                        if (f.getType().getSimpleName().equals("String")) {
//                            String fields[i].getClass().getSimpleName() = rs.getString('"' + f.getClass().getSimpleName() + '"');
                        } else if (f.getType().getSimpleName().equals("int")) {
                        }
                    }
                }
                int id = rs.getInt("id");
                String rollNumber = rs.getString("rollNumber");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
            }
        } catch (SQLException ex) {
            System.out.println("Không lấy được danh sách.");
        }
        return listObject;
    }

    public String getFieldsName(Field[] fields) {
        StringBuilder fieldsNameBuilder = new StringBuilder();
        fieldsNameBuilder.append("(");
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            if (f.isAnnotationPresent(MyId.class) && f.isAnnotationPresent(AutoGenerate.class)) {
                continue;
            }
            fieldsNameBuilder.append(f.getName()).append(",");
        }
        fieldsNameBuilder.deleteCharAt(fieldsNameBuilder.length() - 1);
        fieldsNameBuilder.append(")");
        String fieldsName = fieldsNameBuilder.toString();
        return fieldsName;
    }

    public String getFieldsValue(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        StringBuilder fieldsValueBuilder = new StringBuilder();
        fieldsValueBuilder.append("(");
        try {
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                f.setAccessible(true);

                System.out.println(f.getName() + " - " + f.getType().getSimpleName());
                System.out.println(f.isAnnotationPresent(MyId.class));
                if (f.isAnnotationPresent(MyId.class) && f.isAnnotationPresent(AutoGenerate.class)) {
                    continue;
                }
                if (f.getType().getSimpleName().equals("String")) {
                    fieldsValueBuilder.append("'").append(f.get(obj)).append("'").append(",");
                } else if (f.getType().getSimpleName().equals("int")) {
                    fieldsValueBuilder.append(f.get(obj)).append(",");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        fieldsValueBuilder.deleteCharAt(fieldsValueBuilder.length() - 1);
        fieldsValueBuilder.append(")");
        return fieldsValueBuilder.toString();
    }

    public String getTableName(Object obj) {
        Class clazz = obj.getClass();
        if (obj.getClass().isAnnotationPresent(Entity.class)) {
            Entity entity = (Entity) clazz.getAnnotation(Entity.class);
            return entity.tableName();
        }
        return obj.getClass().getSimpleName().toLowerCase() + "s";
    }

    public void save(Object obj) throws SQLException {
        Field[] fields = obj.getClass().getDeclaredFields();
        StringBuilder fieldsValueBuilder = new StringBuilder();
        fieldsValueBuilder.append("(");
        try {
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                f.setAccessible(true);

                System.out.println(f.getName() + " - " + f.getType().getSimpleName());
                System.out.println(f.isAnnotationPresent(MyId.class));
                if (f.isAnnotationPresent(MyId.class) && f.isAnnotationPresent(AutoGenerate.class)) {
                    continue;
                }
                if (f.getType().getSimpleName().equals("String")) {
                    fieldsValueBuilder.append("'").append(f.get(obj)).append("'").append(",");
                } else if (f.getType().getSimpleName().equals("int")) {
                    fieldsValueBuilder.append(f.get(obj)).append(",");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        fieldsValueBuilder.deleteCharAt(fieldsValueBuilder.length() - 1);
        fieldsValueBuilder.append(")");
        System.out.println(fieldsValueBuilder.toString());

        String tableName = obj.getClass().getSimpleName().toLowerCase();
        StringBuilder fieldsNameBuilder = new StringBuilder();
        fieldsNameBuilder.append("(");
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            if (f.isAnnotationPresent(MyId.class) && f.isAnnotationPresent(AutoGenerate.class)) {
                continue;
            }
            fieldsNameBuilder.append(f.getName()).append(",");
        }
        fieldsNameBuilder.deleteCharAt(fieldsNameBuilder.length() - 1);
        fieldsNameBuilder.append(")");
        String fieldsName = fieldsNameBuilder.toString();
        String fieldsValue = fieldsValueBuilder.toString();

        Statement stt = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/reflection", "root", "")
                .createStatement();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("insert into ")
                .append(tableName)
                .append(" ")
                .append(fieldsName)
                .append(" ")
                .append("values ")
                .append(fieldsValue);
        System.out.println(sqlBuilder.toString());
        stt.execute(sqlBuilder.toString());
    }
}
