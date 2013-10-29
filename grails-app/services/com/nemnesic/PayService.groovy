package com.nemnesic

import groovy.transform.InheritConstructors

class PayService {

    static transactional = true

    void payNow(Account to, Account from, BigDecimal amount) {
        transfer from, to, amount
        sendTransferEmail from, to, amount
    }

    void transfer(Account from, Account to, BigDecimal amount) {
        def credit = credit(to, amount)
        def debit = debit(from, amount)
        new Transfer(credit: credit, debit: debit).save()
    }


    def credit(Account account, BigDecimal amount) {
        def trans = new Transaction(type: TransactionType.Credit, amount: amount, account: account)
        trans.save(flush: true)
        account.balance += amount
        trans
    }

    def debit(Account account, BigDecimal amount) {
        if (amount > account.balance)
            throw new InsufficientFundsException()

        def trans = new Transaction(type: TransactionType.Debit, amount: amount, account: account)
        trans.save(flush: true)
        account.balance -= amount
        trans
    }

    def sendTransferEmail(Account transferFromAccount, Account transferToAccount, BigDecimal amount) {
        sendMail {
            to transferToAccount.email
            subject "Transfer completed"
            body "Hi $transferFromAccount.name, $amount has been credited to your account."
        }

        sendMail {
            to transferFromAccount.email
            subject "Transfer completed"
            body "Hi $transferFromAccount.name, $amount has been debited from your account."
        }

    }


}

@InheritConstructors
class InsufficientFundsException extends RuntimeException {}
