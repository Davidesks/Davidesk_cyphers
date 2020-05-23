/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.davidesk_cyphers;

import java.util.Objects;

/**
 *
 * @author davidesk
 */
public class Transaction {
	private Wallet sender;
	private Wallet receiver;
	private double money;

	public Transaction(Wallet sender, Wallet receiver, double money) {
		this.sender = sender;
		this.receiver = receiver;
		this.money = money;
	}

	public Wallet getSender() {
		return sender;
	}

	public Wallet getReceiver() {
		return receiver;
	}

	public double getMoney() {
		return money;
	}

	@Override
	public String toString() {
		return "from '" + sender.getName() + "' to '" + receiver.getName() + "' " + money;
	}
}

