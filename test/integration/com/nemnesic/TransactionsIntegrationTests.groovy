package com.nemnesic

import org.junit.Before

class TransactionsIntegrationTests extends AbstractAdminControllerIntegrationTests {

    def payService

    @Before
    void setup() {
        super.setup()
    }

    void testTransactionsList() {
        5.times {
            _runTransaction()
        }
        nem.refresh()
        controller.params.id = nem.id
        controller.transactions()
        assert 5 == controller.modelAndView.model.transactions.size()
        assert controller.modelAndView.model.account
    }

    void testTransactionAccountNotFound() {
        controller.params.id = 99
        controller.transactions()
        assert !controller.modelAndView.model.account
        assert !controller.modelAndView.model.transactions
        assert 'account.not.found' == controller.modelAndView.model.messageSourceKey
    }

    private void _runTransaction() {
        payService.payNow nem, dan, 20
    }
}
