/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo180315;

import demo180310.Human;
import demo180329Action.PositiveActivities;

/**
 *
 * @author Phuong Anh
 */
public class Staff extends Human implements PositiveActivities{
    
    private int salary;

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    
    public static void main(String[] args) {
        Staff st1 = new Staff();
        st1.setName("Hùng");
        st1.setSalary(100000);
        
        System.out.println(st1.getSalary());
    }

    @Override
    public void readBooks() {
        System.out.println("Đọc sách kinh doanh...");
    }

    @Override
    public void playSports() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
