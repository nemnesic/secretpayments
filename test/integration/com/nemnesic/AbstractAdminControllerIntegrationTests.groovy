package com.nemnesic

import org.junit.After

class AbstractAdminControllerIntegrationTests extends GroovyTestCase {
    Account nem, dan
    AdminController controller
    def greenMail

    void setup() {
        nem = new Account(name: 'Nem Nesic', email: 'nem@nemnesic.com').save(flush: true, failOnError: true)
        dan = new Account(name: 'Dan Woods', email: 'dan@danwoods.com').save(flush: true, failOnError: true)
        controller = new AdminController()
    }

    @After
    void after() {
        nem.delete()
        dan.delete()
        greenMail.deleteAllMessages()
    }
}
