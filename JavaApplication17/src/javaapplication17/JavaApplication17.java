/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication17;

/**
 *
 * @author nguye
 */
public class JavaApplication17 {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Account acc = new Account(1, "1234", 5000);

        WithdrawThread ruttien1 = new WithdrawThread(acc, 6000);
        WithdrawThread ruttien2 = new WithdrawThread(acc, 60000);
        WithdrawThread ruttien3 = new WithdrawThread(acc, 600000);
        DepositThread guitien1 = new DepositThread(acc, 5000000);

        ruttien1.start();
        ruttien2.start();
        ruttien3.start();
        Thread.sleep(5* 1000);
        guitien1.start();

//        ruttien1.start();
        ruttien1.join();
        ruttien2.join();
        ruttien3.join();
        guitien1.join();

//        ruttien1.join();
        System.out.println("Last balance: " + acc.getBalance());
    }

}
