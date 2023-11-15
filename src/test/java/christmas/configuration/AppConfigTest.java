package christmas.configuration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.config.AppConfig;
import christmas.config.Config;
import christmas.controller.OrderController;
import christmas.repository.MemoryRepository;
import christmas.service.BenefitsService;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.StartView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class AppConfigTest {

    private static Config config;

    @BeforeEach
    void setUp() {
        config = AppConfig.getInstance();
    }

    static class ObjectSupplierTuple {
        final Supplier<Object> supplier;
        final Class<?> expectedType;

        ObjectSupplierTuple(final Supplier<Object> supplier, final Class<?> expectedType) {
            this.supplier = supplier;
            this.expectedType = expectedType;
        }
    }

    static Stream<ObjectSupplierTuple> singletonSuppliers() {
        return Stream.of(
                new ObjectSupplierTuple(() -> AppConfig.getInstance(), Config.class),
                new ObjectSupplierTuple(() -> config.outputView(), OutputView.class),
                new ObjectSupplierTuple(() -> config.inputView(), InputView.class),
                new ObjectSupplierTuple(() -> config.startView(), StartView.class),
                new ObjectSupplierTuple(() -> config.orderController(), OrderController.class),
                new ObjectSupplierTuple(() -> config.orderService(), OrderService.class),
                new ObjectSupplierTuple(() -> config.benefitsService(), BenefitsService.class),
                new ObjectSupplierTuple(() -> config.memoryOrderRepository(), MemoryRepository.class)
        );
    }

    @ParameterizedTest(name = "{0}은 싱글톤이며, {1}의 객체이다.")
    @MethodSource("singletonSuppliers")
    void assertSingletonAndType(ObjectSupplierTuple tuple) {
        // Given
        final Supplier<Object> supplier = tuple.supplier;
        final Class<?> expectedType = tuple.expectedType;

        // When
        Object firstInstance = supplier.get();
        Object secondInstance = supplier.get();

        // Then
        assertThat(firstInstance).isNotNull();
        assertThat(firstInstance).isInstanceOf(expectedType);
        assertThat(firstInstance).isSameAs(secondInstance);
    }
}
