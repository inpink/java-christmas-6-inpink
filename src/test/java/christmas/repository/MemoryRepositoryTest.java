package christmas.repository;

import christmas.domain.entity.order.IndexModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryRepositoryTest {

    private MemoryRepository<TestIndexModel> memoryRepository;

    @BeforeEach
    void setUp() {
        memoryRepository = new TestMemoryRepository();
    }

    @Test
    void 객체_저장_및_반환() {
        // Given
        TestIndexModel testObject = new TestIndexModel();

        // When
        TestIndexModel savedObject = memoryRepository.save(testObject);

        // Then
        assertNotNull(savedObject);
        assertNotNull(savedObject.getId());
        assertThat(testObject).isSameAs(savedObject);
    }

    @Test
    void 저장된_객체_ID_증가() {
        // Given
        TestIndexModel object1 = new TestIndexModel();
        TestIndexModel object2 = new TestIndexModel();

        // When
        TestIndexModel savedObject1 = memoryRepository.save(object1);
        TestIndexModel savedObject2 = memoryRepository.save(object2);

        // Then
        assertEquals(1L, savedObject1.getId());
        assertEquals(2L, savedObject2.getId());
    }

    @Test
    void ID로_객체_찾기() {
        // Given
        TestIndexModel testObject = new TestIndexModel();
        memoryRepository.save(testObject);

        // When
        Optional<TestIndexModel> foundObject = memoryRepository.findById(1L);

        // Then
        assertTrue(foundObject.isPresent());
        assertEquals(testObject, foundObject.get());
    }

    @Test
    void 모든_객체_반환() {
        // Given
        memoryRepository.save(new TestIndexModel());
        memoryRepository.save(new TestIndexModel());

        // When
        var allObjects = memoryRepository.findAll();

        // Then
        assertEquals(2, allObjects.size());
    }
}

class TestIndexModel extends IndexModel {
}

class TestMemoryRepository extends MemoryRepository<TestIndexModel> {
}
