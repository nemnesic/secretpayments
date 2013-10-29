package com.nemnesic

class Transfer {

  Transaction debit
  Transaction credit

  static constraints = {
    debit nullable: false
    credit nullable: false
  }
}
