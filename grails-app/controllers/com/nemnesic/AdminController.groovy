package com.nemnesic

class AdminController {
    def payService

    static defaultAction = "pay"

    def pay = {}

    def payNow = { PayNowCommand payNowCommand ->
        if (payNowCommand.hasErrors()) {
            [payNowCommand: payNowCommand]
        } else {
            def resp = [:]
            try {
                payService.payNow payNowCommand.transferTo, payNowCommand.transferFrom, payNowCommand.amount
                resp.message = 'transfer.success'
            } catch (InsufficientFundsException e) {
                resp.message = 'insufficient.funds'
            } catch (Throwable t) {
                resp.message = "internal.server.error"
            }
            [messageSourceKey: resp.message]
        }
    }

    def transactions = {
        if (params.long('id')) {
            def account = Account.get(params.long('id'))
            if (account) {
                render(view: '/admin/transactions_table',
                        model: [
                                account: account,
                                transactions: account.transactions
                        ])
            } else {
                render(view: '/admin/transactions_table', model: [messageSourceKey: 'account.not.found'])
            }
        }
    }
}

class PayNowCommand {
    Account transferFrom
    Account transferTo
    BigDecimal amount

    static constraints = {
        amount nullable: false, validator: { val, obj ->
            if (val <= 0) {
                return ['negative.transfer']
            }
        }
        transferFrom validator: { val, obj ->
            if (!val.id) {
                return ['no.from.account']
            }
        }
        transferTo validator: { val, obj ->
            if (val == obj.transferFrom) {
                return ['same.account.transfer']
            }
            if (!val.id) {
                return ['no.to.account']
            }
        }
    }
}
