package ru.netology.manager;

import ru.netology.domain.PurchaseItem;

import static java.lang.Math.min;

public class AfishaManager {
  // Используемое значение по умолчанию для параметра bandCapacity,
  // если он не задан пользоателем.
  public static final int DEFAULT_BAND_CAPACITY = 10;
  // Массив всех добавленных элементов.
  private PurchaseItem[] items = new PurchaseItem[0];
  // Вместимость ленты элементов.
  private int bandCapacity;

  /**
   * Создает новый экземпляр AfishaManager с параметром bandCapacity по умолчанию.
   */
  public AfishaManager() {
    this.bandCapacity = DEFAULT_BAND_CAPACITY;
  }

  /**
   * Создает новый экземпляр AfishaManager с заданным bandCapacity.
   * Если заданный пользователем bandCapacity отрицательный,
   * то будет использовано значение по умолчанию DEFAULT_BAND_CAPACITY.
   * @param bandCapacity - максимальное количество элементов в ленте.
   */
  public AfishaManager(int bandCapacity) {
    if (bandCapacity < 0)
      this.bandCapacity = DEFAULT_BAND_CAPACITY;
    else
      this.bandCapacity = bandCapacity;
  }

  /**
   * Метод возвращает параметр bandCapacity, используемый данным AfishaManager.
   */
  public int getBandCapacity() {
    return bandCapacity;
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
      if (item.getFilmId() != id) {
        tmp[index] = item;
        index++;
      }
    }
    items = tmp;
  }
}
