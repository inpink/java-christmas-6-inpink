package christmas.repository;

import christmas.domain.entity.order.IndexModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class MemoryRepository<T extends IndexModel> implements Repository {

    protected final Map<Long, T> store = new HashMap<>();
    protected Long sequence;

    public MemoryRepository() {
        resetSequence();
    }

    private void resetSequence() {
        sequence = 0L;
    }

    @Override
    public T save(final IndexModel object) {
        store.put(++sequence, (T) object);
        object.setId(sequence);
        return (T) object;
    }

    @Override
    public Optional<T> findById(final Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteAll() {
        store.clear();
        resetSequence();
    }
}
