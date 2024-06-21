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

import java.util.Objects;
import java.util.function.BiFunction;

@Repository
@AllArgsConstructor
public class ToolRepository {
    private final BrandRepository brandRepository;
    private final ToolCodeRepository toolCodeRepository;
    private final ToolTypeRepository toolTypeRepository;
    private final DatabaseClient databaseClient;


    public Mono<Tool> findById(Integer id) {
        String sql = "SELECT * FROM tool WHERE id = :id";
        return databaseClient.sql(sql)
                .bind("id", id)
                .map(getRowRowMetadataToolBiFunction())
                .one();
    }

    public Flux<Tool> findAllByBrandId(Integer brandId) {
        String sql = "SELECT * FROM tool WHERE brand_id = :brandId";
        return databaseClient.sql(sql)
                .bind("brandId", brandId)
                .map(getRowRowMetadataToolBiFunction())
                .all();
    }

    public Flux<Tool> findAllByToolTypeId(Integer toolTypeId) {
        String sql = "SELECT * FROM tool WHERE tool_type_id = :toolTypeId";
        return databaseClient.sql(sql)
                .bind("toolTypeId", toolTypeId)
                .map(getRowRowMetadataToolBiFunction())
                .all();
    }

    public Flux<Tool> findAll() {
        String sql = "SELECT * FROM tool";
        return databaseClient.sql(sql)
                .map(getRowRowMetadataToolBiFunction())
                .all();
    }

    private @NotNull BiFunction<Row, RowMetadata, Tool> getRowRowMetadataToolBiFunction() {
        return (row, metadata) -> new Tool(
                row.get("id", Integer.class),
                row.get("name", String.class),
                row.get("brand_id", Integer.class),
                row.get("tool_type_id", Integer.class),
                brandRepository.findById(Objects.requireNonNull(row.get("brand_id", Integer.class))).block(),
                toolTypeRepository.findById(Objects.requireNonNull(row.get("tool_type_id", Integer.class))).block(),
                toolCodeRepository.findById(Objects.requireNonNull(row.get("tool_code_id", Integer.class))).block()
        );
    }
}