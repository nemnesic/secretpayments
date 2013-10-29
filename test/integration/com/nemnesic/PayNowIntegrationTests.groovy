package com.nemnesic

import org.junit.Before

import javax.mail.internet.MimeMessage

class PayNowIntegrationTests extends AbstractAdminControllerIntegrationTests {


    @Before
    void setup() {
        super.setup()
        controller.params."transferFrom.id" = nem.id
        controller.params."transferTo.id" = dan.id
        controller.params.amount = 20
    }

    void testSunnyDayPayNow() {
        controller.payNow()
        assert 180 == nem.balance
        assert 220 == dan.balance
    }

    void testSunnyDayPayNowMessage() {
        def resp = controller.payNow()
        assert resp.messageSourceKey == 'transfer.success'
        assert !resp.payNowCommand
    }

    void testInsufficientFundsPayNowMessage() {
        controller.params.amount = 220
        def resp = controller.payNow()
        assert resp.messageSourceKey == 'insufficient.funds'
        assert !resp.payNowCommand
    }

    void testSystemFailureMessage() {
        use(ExceptionCategory) {
            def resp = controller.payNow()
            assert resp.messageSourceKey == 'internal.server.error'
            assert !resp.payNowCommand
        }
    }

    void testRainyDayPayNow() {
        controller.params."transferFrom.id" = null
        def resp = controller.payNow()
        assert !resp.messageSourceKey
        assert resp.payNowCommand
    }

    void testTransferringToSameAccount() {
        controller.params."transferTo.id" = nem.id
        def resp = controller.payNow()
        assert !resp.messageSourceKey
        assert resp.payNowCommand
    }

    void testConfirmationEmail() {
        controller.payNow()
        List<MimeMessage> msgs = greenMail.receivedMessages
        assert 2 == msgs.size()
        assert msgs.collect { MimeMessage msg -> msg.allRecipients }?.flatten()*.address.containsAll(nem.email, dan.email)
    }
}

class ExceptionCategory {
    static credit(PayService payService, Account a, BigDecimal b) {
        throw new RuntimeException()
    }
}