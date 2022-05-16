package dev.ivanqueiroz.transferscheduler.domain.services;

import dev.ivanqueiroz.transferscheduler.domain.entities.Account;

public class AccountMockFactory {

    public AccountMockFactory() {
        throw new IllegalAccessError("Not permited");
    }

    public static Account createSourceAccount() {
        return new Account("114299997", "Alice Elza Emanuelly Rocha", "23552312226");
    }

    public static Account createDestinationAccount() {
        return new Account("119318209", "Isabelly Laís Drumond", "91430793260");
    }

}
