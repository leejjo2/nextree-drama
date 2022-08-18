/* 
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package io.naraway.addressbook;

import io.naraway.accent.util.json.JsonUtil;
import io.naraway.addressbook.aggregate.AddressBookDramaRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class AddressBookSchemaResource {
    /* Autogen by nara studio */
    private final Map<String, Object> schema;

    public AddressBookSchemaResource(@Value("classpath:drama.json") Resource resourceFile) throws IOException {
        /* Autogen by nara studio */
        String json = new String(resourceFile.getInputStream().readAllBytes());
        AddressBookDramaRole.validate(json);
        this.schema = JsonUtil.fromJson(json, Map.class);
    }

    @GetMapping("/schema")
    public Map<String, Object> schema() {
        /* Autogen by nara studio */
        return this.schema;
    }
}
