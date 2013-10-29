package com.nemnesic

class Transaction {
  Date dateCreated
  Account account
  TransactionType type
  BigDecimal amount

  static constraints = {
    account nullable: false
    amount nullable: false, validator: { val, obj ->
      if (val <= 0) {
        ['negative.amount']
      }
    }
    type nullable: false
  }
}

enum TransactionType {
  Credit, Debit

}
