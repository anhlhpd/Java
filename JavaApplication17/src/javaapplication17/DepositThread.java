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
public class DepositThread extends Thread {

    private Account account;
    private int amount;

    public DepositThread(Account account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        synchronized (this.account) {
            System.out.println(Thread.currentThread().getName() + " - before " + this.account.getBalance());
            this.account.deposit(this.amount);
            // this.account.notify();
            this.account.notifyAll();
            System.out.println(Thread.currentThread().getName() + " - after " + this.account.getBalance());
        }
    }

}
