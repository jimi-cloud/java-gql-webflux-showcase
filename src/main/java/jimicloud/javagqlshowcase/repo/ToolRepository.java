package jimicloud.javagqlshowcase.repo;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import jimicloud.javagqlshowcase.model.Tool;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.function.BiFunction;

@Repository
@AllArgsConstructor
public class ToolRepository {
    private final BrandRepository brandRepository;
    private final ToolCodeRepository toolCodeRepository;
    private final ToolTypeRepository toolTypeRepository;
    private final DatabaseClient databaseClient;


    public Mono<Tool> findById(Long id) {
        String sql = "SELECT * FROM tool WHERE id = :id";
        return databaseClient.sql(sql)
                .bind("id", id)
                .map((row, metadata) -> new Tool(
                                row.get("id", Long.class),
                                row.get("tool_code_id", Long.class),
                                row.get("brand_id", Long.class),
                                row.get("tool_type_id", Long.class)
                        )
                ).one();
    }

    public Flux<Tool> findAllByBrandId(Long brandId) {
        String sql = "SELECT * FROM tool WHERE brand_id = :brandId";
        return databaseClient.sql(sql)
                .bind("brandId", brandId)
                .map(getToolBiFunction())
                .all();
    }

    public Flux<Tool> findAllByToolTypeId(Long toolTypeId) {
        String sql = "SELECT * FROM tool WHERE tool_type_id = :toolTypeId";
        return databaseClient.sql(sql)
                .bind("toolTypeId", toolTypeId)
                .map(getToolBiFunction())
                .all();
    }

    public Flux<Tool> findAll() {
        String sql = "SELECT id, tool_code_id, brand_id, tool_type_id FROM tool";
        return databaseClient.sql(sql)
                .map(getToolBiFunction())
                .all();
    }

    private @NotNull BiFunction<Row, RowMetadata, Tool> getToolBiFunction() {
        return (row, metadata) -> new Tool(
                row.get("id", Long.class),
                row.get("tool_code_id", Long.class),
                row.get("brand_id", Long.class),
                row.get("tool_type_id", Long.class)
        );
    }
}