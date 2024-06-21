package jimicloud.javagqlshowcase.datafetcher;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import graphql.ExecutionResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {DgsAutoConfiguration.class, RentalAgreementDataFetcher.class})
public class RentalAgreementDatafetcherTest {
    @Autowired
    private DgsQueryExecutor dgsQueryExecutor;

    @Test
    public void testFindAllRentalAgreements() {
        String query = "{ findAllRentalAgreements { id } }";
        ExecutionResult result = dgsQueryExecutor.execute(query);
    }
}
