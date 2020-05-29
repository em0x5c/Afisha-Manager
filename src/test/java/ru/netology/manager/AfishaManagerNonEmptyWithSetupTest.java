package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.PurchaseItem;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AfishaManagerNonEmptyWithSetupTest {
  private int bandCapacity = 5;
  private AfishaManager manager = new AfishaManager(bandCapacity);
  private PurchaseItem first = new PurchaseItem(1, 1, "first");
  private PurchaseItem second = new PurchaseItem(2, 2, "second");
  private PurchaseItem third = new PurchaseItem(3, 3, "third");

  @BeforeEach
  public void setUp() {
    manager.addItem(first);
    manager.addItem(second);
    manager.addItem(third);
  }

  @Test
  public void shouldRemoveIfExists() {
    int idToRemove = 1;
    manager.removeItemById(idToRemove);

    PurchaseItem[] actual = manager.getAllItems();
    PurchaseItem[] expected = new PurchaseItem[]{third, second};

    assertArrayEquals(expected, actual);
  }

  @Test
  public void shouldNotRemoveIfIdGraterThanNumberOfItems() {
    int idToRemove = 3;
    manager.removeItemById(idToRemove);

    PurchaseItem[] actual = manager.getAllItems();
    PurchaseItem[] expected = new PurchaseItem[]{third, second, first};

    assertArrayEquals(expected, actual);
  }

  @Test
  public void shouldNotRemoveIfIdLessThanZero() {
    int idToRemove = -1;
    manager.removeItemById(idToRemove);

    PurchaseItem[] actual = manager.getAllItems();
    PurchaseItem[] expected = new PurchaseItem[]{third, second, first};

    assertArrayEquals(expected, actual);
  }

  @Test
  public void shouldReturnLastAddedItems() {
    PurchaseItem[] actual = manager.getLastAddedItems();
    PurchaseItem[] expected = new PurchaseItem[]{third, second, first};

    assertArrayEquals(expected, actual);
  }

  @Test
  public void shouldReturnLastFiveAddedItems() {
    PurchaseItem forth = new PurchaseItem(4, 4, "forth");
    PurchaseItem fifth = new PurchaseItem(5, 5, "fifth");
    PurchaseItem sixth = new PurchaseItem(6, 6, "sixth");

    manager.addItem(forth);
    manager.addItem(fifth);
    manager.addItem(sixth);

    PurchaseItem[] actual = manager.getLastAddedItems();
    PurchaseItem[] expected = new PurchaseItem[]{sixth, fifth, forth, third, second};

    assertArrayEquals(expected, actual);
  }
}