package io.naraway.addressbook;

import io.naraway.drama.prologue.rolekeeper.config.EnableDramaRoleBaseAccess;
import io.naraway.drama.prologue.spacekeeper.config.DramaApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@DramaApplication
@EnableDramaRoleBaseAccess
@SpringBootApplication(scanBasePackages = "io.naraway.addressbook", exclude = DataSourceAutoConfiguration.class)
@EnableMongoRepositories("io.naraway.addressbook")
public class AddressBookBootApplication {
    //
    public static void main(String[] args) {
        /* Autogen by nara studio */
        SpringApplication.run(AddressBookBootApplication.class, args);
    }
}
