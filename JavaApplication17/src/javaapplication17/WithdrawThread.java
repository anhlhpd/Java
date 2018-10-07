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
public class WithdrawThread extends Thread {

    private Account account;
    private int amount;

    public WithdrawThread(Account account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        synchronized (this.account) {
            System.out.println(Thread.currentThread().getName() + " - before " + this.account.getBalance());
            try {
                while (!this.account.withdraw(this.amount)) {
                    System.err.println(Thread.currentThread().getName() + " is waiting");
                    this.account.wait();
                }
            } catch (InterruptedException e) {

            }
            System.out.println(Thread.currentThread().getName() + " - after " + this.account.getBalance());
        }
    }

}
