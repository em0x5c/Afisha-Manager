package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.PurchaseItem;
import ru.netology.repository.AfishaRepository;

import static org.junit.jupiter.api.Assertions.*;

public class AfishaRepositoryTest {
    private AfishaRepository repository = new AfishaRepository();
    private PurchaseItem first = new PurchaseItem(1, 1, "first");
    private PurchaseItem second = new PurchaseItem(2, 2, "second");
    private PurchaseItem third = new PurchaseItem(3, 3, "third");

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
    }

    @Test
    public void shouldRemoveIfExists() {
        int idToRemove = 1;
        repository.removeItemById(idToRemove);

        PurchaseItem[] actual = repository.findAll();
        PurchaseItem[] expected = new PurchaseItem[]{second, third};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveIfIdGraterThanNumberOfItems() {
        int idToRemove = 3;
        repository.removeItemById(idToRemove);

        PurchaseItem[] actual = repository.findAll();
        PurchaseItem[] expected = new PurchaseItem[]{first, second, third};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveIfIdLessThanZero() {
        int idToRemove = -1;
        repository.removeItemById(idToRemove);

        PurchaseItem[] actual = repository.findAll();
        PurchaseItem[] expected = new PurchaseItem[]{first, second, third};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnAllItems() {
        PurchaseItem[] actual = repository.findAll();
        PurchaseItem[] expected = new PurchaseItem[]{first, second, third};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindById() {
        PurchaseItem second = repository.findById(1);
        assertEquals(this.second, second);
    }

    @Test
    public void shouldReturnNullIfItemWithIdDoesNotExist() {
        PurchaseItem forth = repository.findById(4);
        assertNull(forth);

        PurchaseItem belowZero = repository.findById(-1);
        assertNull(belowZero);
    }

    @Test
    public void shouldRemoveAllItems() {
        repository.removeAll();
        PurchaseItem[] expected = new PurchaseItem[0];

        assertArrayEquals(expected, repository.findAll());
    }
}
