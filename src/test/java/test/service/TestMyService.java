package test.service;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author guyburton
 *         Date: 16/08/2013
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
//http://forum.springsource.org/showthread.php?80440-Integration-testing-with-transactions-on-multiple-threads
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestMyService {

    @Test public void
    correctResponseToCreateCommand() {
        assertThat(
            createClient().post("").getStatus(),
            equalTo(200));
    }

    @Test public void
    emptyCollectionHandled() {
        assertThat(
            createClient().get(List.class),
            empty()
        );
    }

    @SuppressWarnings("unchecked")
    @Test public void
    retrieveItem() {
        createClient().post("");
        List<Map<String, Object>> items = createClient().get(List.class);
        assertThat(items, hasSize(1));
        assertThat(items.get(0).get("age"), Matchers.equalTo((Object) 15));
        assertThat(items.get(0).get("name"), Matchers.equalTo((Object)"Test"));
    }

    public WebClient createClient() {
        List<Object> providers = new ArrayList<Object>();
        providers.add( new JacksonJaxbJsonProvider() );

        return WebClient
            .create("http://localhost:8080/", providers)
            .accept("application/json")
            .path("testService");
    }

}
