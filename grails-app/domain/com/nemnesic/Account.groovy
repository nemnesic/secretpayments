package com.nemnesic

class Account {
  String name, email
  BigDecimal balance = 200
  static hasMany = [transactions: Transaction]
  static mappedBy = [transactions: 'account']

  static constraints = {
    name nullable: false, blank: false, maxSize: 50
    email email: true, nullable: false, blank: false, maxSize: 50, unique: true
  }
}
