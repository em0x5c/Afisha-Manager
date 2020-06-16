package ru.netology.manager;

import ru.netology.domain.PurchaseItem;
import ru.netology.repository.AfishaRepository;

import static java.lang.Math.min;

public class AfishaManager {
  // Используемое значение по умолчанию для параметра bandCapacity,
  // если он не задан пользоателем.
  public static final int DEFAULT_BAND_CAPACITY = 10;
  // Репозиторий элементов.
  private AfishaRepository repository;

  // Вместимость ленты элементов.
  private int bandCapacity;

  /**
   * Создает новый экземпляр AfishaManager.
   * Если заданный пользователем bandCapacity отрицательный,
   * то будет использовано значение по умолчанию DEFAULT_BAND_CAPACITY.
   * @param repository - используемый репозиторий.
   * @param bandCapacity - максимальное количество элементов в ленте.
   */
  public AfishaManager(AfishaRepository repository, int bandCapacity) {
    this.repository = repository;
    if (bandCapacity < 0)
      this.bandCapacity = DEFAULT_BAND_CAPACITY;
    else
      this.bandCapacity = bandCapacity;
  }

  /**
   * Создает новый экземпляр AfishaManager с параметром bandCapacity по умолчанию.
   * @param repository - используемый репозиторий.
   */
  public AfishaManager(AfishaRepository repository) {
    this.repository = repository;
    this.bandCapacity = DEFAULT_BAND_CAPACITY;
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
    repository.save(item);
  }

  /**
   * Метод возвращает последние добавленные фильмы.
   * Размер возвращаемого массива определяется параметром bandCapacity,
   * передаваемым в конструктор.
   * @return возвращает последние добавленные фильмы.
   */
  public PurchaseItem[] getLastAddedItems() {
    PurchaseItem[] items = repository.findAll();
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
    repository.removeItemById(id);
  }
}
