package ru.netology.manager;

import ru.netology.domain.PurchaseItem;
import ru.netology.repository.AfishaRepository;

import static java.lang.Math.min;

public class AfishaManager {
  // Репозиторий элементов.
  private AfishaRepository repository;

  // Вместимость ленты элементов.
  private int bandCapacity = 10;

  /**
   * Создает новый экземпляр AfishaManager.
   * @param repository - используемый репозиторий.
   * @param bandCapacity - максимальное количество элементов в ленте.
   */
  public AfishaManager(AfishaRepository repository, int bandCapacity) {
    this.repository = repository;
    this.bandCapacity = bandCapacity;
  }

  /**
   * Создает новый экземпляр AfishaManager.
   * @param bandCapacity - максимальное количество элементов в ленте.
   */
  public AfishaManager(int bandCapacity) {
    this.bandCapacity = bandCapacity;
  }

  /**
   * Создает новый экземпляр AfishaManager.
   * @param repository - используемый репозиторий.
   */
  public AfishaManager(AfishaRepository repository) {
    this.repository = repository;
  }

  /**
   * Метод добавляет новый элемент.
   * @param item - добавляемый элемент.
   */
  public void addItem(PurchaseItem item) {
    repository.save(item);
  }

  /**
   * Возвращает все элементы в обратном порядке.
   * @return все элементы в обратном порядке.
   */
  public PurchaseItem[] getAllItems() {
    PurchaseItem[] items = repository.findAll();
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
