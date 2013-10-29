import com.nemnesic.Account

class BootStrap {

    def init = { servletContext ->
        if (!Account.list()) {
            new Account(name: 'Joe', email: 'joe@example.com').save()
            new Account(name: 'Patty', email: 'patty@example.com').save()
        }
    }
    def destroy = {
    }
}
