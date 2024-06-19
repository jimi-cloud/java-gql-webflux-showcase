package jimnorth1982.javagqlshowcase.controller;

import jimnorth1982.javagqlshowcase.model.Tool;
import jimnorth1982.javagqlshowcase.repo.ToolRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class ToolController {
    private final ToolRepository toolRepository;

    @QueryMapping
    public Tool findToolById(@Argument Long id) {
        return toolRepository.findById(id);
    }
}
