package christmas.repository;

import christmas.domain.entity.order.IndexModel;
import java.util.List;
import java.util.Optional;

public interface Repository<T extends IndexModel> {
    T save(final T object);

    Optional<T> findById(final Long id);

    List<T> findAll();

    void deleteAll();
}