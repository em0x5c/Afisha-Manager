package ru.netology.manager;

import ru.netology.domain.PurchaseItem;

import static java.lang.Math.min;

public class AfishaManager {
  // Массив всех добавленных элементов.
  private PurchaseItem[] items = new PurchaseItem[0];
  // Вместимость ленты элементов.
  private int bandCapacity;

  /**
   * Создает новый экземпляр AfishaManager.
   * @param bandCapacity - максимальное количество элементов в ленте.
   */
  public AfishaManager(int bandCapacity) {
    this.bandCapacity = bandCapacity;
  }

  /**
   * Метод добавляет новый элемент.
   * @param item - добавляемый элемент.
   */
  public void addItem(PurchaseItem item) {
    int length = items.length + 1;
    PurchaseItem[] tmp = new PurchaseItem[length];
    System.arraycopy(items, 0, tmp, 0, items.length);
    int lastIndex = tmp.length - 1;
    tmp[lastIndex] = item;
    items = tmp;
  }

  /**
   * Возвращает все элементы в обратном порядке.
   * @return все элементы в обратном порядке.
   */
  public PurchaseItem[] getAllItems() {
    PurchaseItem[] result = new PurchaseItem[items.length];
    for (int i = 0; i < result.length; i++) {
      int index = items.length - i - 1;
      result[i] = items[index];
    }
    return result;
  }

  /**
   * Метод возвращает последние добавленные фильмы.
   * Размер возвращаемого массива определяется параметром bandCapacity,
   * передаваемым в конструктор.
   * @return возвращает последние добавленные фильмы.
   */
  public PurchaseItem[] getLastAddedItems() {
    PurchaseItem[] result = new PurchaseItem[min(items.length, bandCapacity)];
    int index = items.length - 1;
    int i = 0;
    while (i < bandCapacity && index >= 0) {
      result [i] = items[index];
      i++;
      index--;
    }
    return result;
  }

  /**
   * Удаляет выбранный элемент.
   * @param id - индекс удаляемого элемента.
   */
  public void removeItemById(int id) {
    if (id < 0 || id >= items.length) {
      return;
    }
    int length = items.length - 1;
    PurchaseItem[] tmp = new PurchaseItem[length];
    int index = 0;
    for (PurchaseItem item : items) {
      if (item.getId() != id) {
        tmp[index] = item;
        index++;
      }
    }
    items = tmp;
  }
}
