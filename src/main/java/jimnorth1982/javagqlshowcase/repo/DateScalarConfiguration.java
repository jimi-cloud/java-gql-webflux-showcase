package jimnorth1982.javagqlshowcase.repo;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Configuration
public class DateScalarConfiguration {
    @Bean
    public RuntimeWiringConfigurer dateWiring() {
        var dateScalar = GraphQLScalarType.newScalar()
                .name("Date")
                .description("Java 8 LocalDate as scalar.")
                .coercing(new Coercing<LocalDate, String>() {
                    @Override
                    public String serialize(@NonNull final Object dataFetcherResult, @NonNull GraphQLContext context, @NonNull Locale locale) {
                        if (dataFetcherResult instanceof LocalDate) {
                            return dataFetcherResult.toString();
                        } else {
                            throw new CoercingSerializeException("Expected a LocalDate object.");
                        }
                    }

                    @Override
                    public LocalDate parseValue(@NonNull final Object input, @NonNull GraphQLContext context, @NonNull Locale locale) {
                        try {
                            if (input instanceof String) {
                                return LocalDate.parse((String) input);
                            } else {
                                throw new CoercingParseValueException("Expected a String");
                            }
                        } catch (DateTimeParseException e) {
                            throw new CoercingParseValueException(String.format("Not a valid date: '%s'.", input), e
                            );
                        }
                    }

                    @Override
                    public LocalDate parseLiteral(@NonNull Value<?> input,
                                                  @NonNull CoercedVariables variables,
                                                  @NonNull GraphQLContext graphQLContext,
                                                  @NonNull Locale locale) {
                        if (input instanceof StringValue) {
                            try {
                                return LocalDate.parse(((StringValue) input).getValue());
                            } catch (DateTimeParseException e) {
                                throw new CoercingParseLiteralException(e);
                            }
                        } else {
                            throw new CoercingParseLiteralException("Expected a StringValue.");
                        }
                    }
                }).build();

        return wiringBuilder -> wiringBuilder.scalar(dateScalar);
    }
}