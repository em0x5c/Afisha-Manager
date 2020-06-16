package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.PurchaseItem;
import ru.netology.repository.AfishaRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class AfishaManagerWithMockTest {
  private int bandCapacity = 5;
  @Mock
  private AfishaRepository repository;
  @InjectMocks
  private AfishaManager manager = new AfishaManager(repository, bandCapacity);
  private PurchaseItem first = new PurchaseItem(1, "first");
  private PurchaseItem second = new PurchaseItem(2, "second");
  private PurchaseItem third = new PurchaseItem(3, "third");

  @Test
  public void shouldRemoveIfExists() {
    int idToRemove = 1;
    PurchaseItem[] returned = new PurchaseItem[]{second,third};
    doReturn(returned).when(repository).findAll();
    doNothing().when(repository).removeItemById(idToRemove);

    manager.removeItemById(idToRemove);
    PurchaseItem[] actual = manager.getLastAddedItems();
    PurchaseItem[] expected = new PurchaseItem[]{third, second};
    assertArrayEquals(expected, actual);
  }

  @Test
  public void shouldNotRemoveIfIdGraterThanNumberOfItems() {
    int idToRemove = 3;
    PurchaseItem[] returned = new PurchaseItem[]{first, second, third};
    doReturn(returned).when(repository).findAll();
    doNothing().when(repository).removeItemById(idToRemove);

    manager.removeItemById(idToRemove);

    PurchaseItem[] actual = manager.getLastAddedItems();
    PurchaseItem[] expected = new PurchaseItem[]{third, second, first};

    assertArrayEquals(expected, actual);
  }

  @Test
  public void shouldNotRemoveIfIdLessThanZero() {
    int idToRemove = -1;
    PurchaseItem[] returned = new PurchaseItem[]{first, second, third};
    doReturn(returned).when(repository).findAll();
    doNothing().when(repository).removeItemById(idToRemove);

    manager.removeItemById(idToRemove);

    PurchaseItem[] actual = manager.getLastAddedItems();
    PurchaseItem[] expected = new PurchaseItem[]{third, second, first};

    assertArrayEquals(expected, actual);
  }

  @Test
  public void shouldReturnLastAddedItems() {
    PurchaseItem[] returned = new PurchaseItem[]{first, second, third};
    doReturn(returned).when(repository).findAll();

    PurchaseItem[] actual = manager.getLastAddedItems();
    PurchaseItem[] expected = new PurchaseItem[]{third, second, first};

    assertArrayEquals(expected, actual);
  }

  @Test
  public void shouldReturnLastFiveAddedItems() {
    PurchaseItem forth = new PurchaseItem(4, "forth");
    PurchaseItem fifth = new PurchaseItem(5, "fifth");
    PurchaseItem sixth = new PurchaseItem(6, "sixth");

    PurchaseItem[] returned = new PurchaseItem[]{first, second, third, forth, fifth, sixth};
    doReturn(returned).when(repository).findAll();
    doNothing().when(repository).save(any(PurchaseItem.class));

    manager.addItem(forth);
    manager.addItem(fifth);
    manager.addItem(sixth);

    PurchaseItem[] actual = manager.getLastAddedItems();
    PurchaseItem[] expected = new PurchaseItem[]{sixth, fifth, forth, third, second};

    assertArrayEquals(expected, actual);
  }

  @Test
  public void shouldConstructAfishaManagerWithDefaultBandCapacity() {
    AfishaManager testManager = new AfishaManager(repository);

    assertEquals(AfishaManager.DEFAULT_BAND_CAPACITY, testManager.getBandCapacity());
  }

  @Test
  public void shouldConstructAfishaManagerWithNegativeDefaultBandCapacity() {
    AfishaManager testManager = new AfishaManager(repository, -10);

    assertEquals(AfishaManager.DEFAULT_BAND_CAPACITY, testManager.getBandCapacity());
  }
}
