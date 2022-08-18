/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
 */

package io.naraway.addressbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootApplication(scanBasePackages = "io.naraway.addressbook")
@EnableMongoRepositories("io.naraway.addressbook")
public class AddressbookTestApplication {
    //
    public static void main(String[] args) {
        //
        SpringApplication.run(AddressbookTestApplication.class, args);
    }
}
