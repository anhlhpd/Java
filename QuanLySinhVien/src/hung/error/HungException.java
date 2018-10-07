/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.error;

/**
 *
 * @author Phuong Anh
 */
public class HungException extends Exception{

    @Override
    public String getMessage() {
        return "Lỗi do người code.";
    }
    
}
