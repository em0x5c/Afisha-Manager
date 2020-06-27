package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.PurchaseItem;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AfishaManagerNonEmptyWithSetupTest {
  private int bandCapacity = 5;
  private AfishaManager manager = new AfishaManager(bandCapacity);
  private PurchaseItem first = new PurchaseItem(1, "first");
  private PurchaseItem second = new PurchaseItem(2, "second");
  private PurchaseItem third = new PurchaseItem(3, "third");

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

    PurchaseItem[] actual = manager.getLastAddedItems();
    PurchaseItem[] expected = new PurchaseItem[]{third, second};

    assertArrayEquals(expected, actual);
  }

  @Test
  public void shouldNotRemoveIfIdGraterThanNumberOfItems() {
    int idToRemove = 3;
    manager.removeItemById(idToRemove);

    PurchaseItem[] actual = manager.getLastAddedItems();
    PurchaseItem[] expected = new PurchaseItem[]{third, second, first};

    assertArrayEquals(expected, actual);
  }

  @Test
  public void shouldNotRemoveIfIdLessThanZero() {
    int idToRemove = -1;
    manager.removeItemById(idToRemove);

    PurchaseItem[] actual = manager.getLastAddedItems();
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
    PurchaseItem forth = new PurchaseItem(4, "forth");
    PurchaseItem fifth = new PurchaseItem(5, "fifth");
    PurchaseItem sixth = new PurchaseItem(6, "sixth");

    manager.addItem(forth);
    manager.addItem(fifth);
    manager.addItem(sixth);

    PurchaseItem[] actual = manager.getLastAddedItems();
    PurchaseItem[] expected = new PurchaseItem[]{sixth, fifth, forth, third, second};

    assertArrayEquals(expected, actual);
  }

  @Test
  public void shouldConstructAfishaManagerWithDefaultBandCapacity() {
    AfishaManager testManager = new AfishaManager();

    assertEquals(AfishaManager.DEFAULT_BAND_CAPACITY, testManager.getBandCapacity());

    PurchaseItem forth = new PurchaseItem(4, "forth");
    PurchaseItem fifth = new PurchaseItem(5, "fifth");
    PurchaseItem sixth = new PurchaseItem(6, "sixth");
    PurchaseItem seventh = new PurchaseItem(7, "seventh");
    PurchaseItem eighth = new PurchaseItem(8, "eighth");
    PurchaseItem ninth = new PurchaseItem(9, "ninth");
    PurchaseItem tenth = new PurchaseItem(10, "tenth");
    PurchaseItem eleventh = new PurchaseItem(11, "eleventh");

    testManager.addItem(first);
    testManager.addItem(second);
    testManager.addItem(third);
    testManager.addItem(forth);
    testManager.addItem(fifth);
    testManager.addItem(sixth);
    testManager.addItem(seventh);
    testManager.addItem(eighth);
    testManager.addItem(ninth);
    testManager.addItem(tenth);
    testManager.addItem(eleventh);


    PurchaseItem[] actual = testManager.getLastAddedItems();
    PurchaseItem[] expected = new PurchaseItem[]{eleventh, tenth, ninth, eighth, seventh, sixth, fifth, forth, third, second};

    assertArrayEquals(expected, actual);
  }

  @Test
  public void shouldConstructAfishaManagerWithNegativeDefaultBandCapacity() {
    AfishaManager testManager = new AfishaManager(-10);

    assertEquals(AfishaManager.DEFAULT_BAND_CAPACITY, testManager.getBandCapacity());

    PurchaseItem forth = new PurchaseItem(4, "forth");
    PurchaseItem fifth = new PurchaseItem(5, "fifth");
    PurchaseItem sixth = new PurchaseItem(6, "sixth");
    PurchaseItem seventh = new PurchaseItem(7, "seventh");
    PurchaseItem eighth = new PurchaseItem(8, "eighth");
    PurchaseItem ninth = new PurchaseItem(9, "ninth");
    PurchaseItem tenth = new PurchaseItem(10, "tenth");
    PurchaseItem eleventh = new PurchaseItem(11, "eleventh");

    testManager.addItem(first);
    testManager.addItem(second);
    testManager.addItem(third);
    testManager.addItem(forth);
    testManager.addItem(fifth);
    testManager.addItem(sixth);
    testManager.addItem(seventh);
    testManager.addItem(eighth);
    testManager.addItem(ninth);
    testManager.addItem(tenth);
    testManager.addItem(eleventh);


    PurchaseItem[] actual = testManager.getLastAddedItems();
    PurchaseItem[] expected = new PurchaseItem[]{eleventh, tenth, ninth, eighth, seventh, sixth, fifth, forth, third, second};

    assertArrayEquals(expected, actual);
  }
}
