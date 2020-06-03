package ru.netology.repository;

import ru.netology.domain.PurchaseItem;

/**
 * Класс, ответственный за хранение PurchaseItem
 */
public class AfishaRepository {
    private PurchaseItem[] items = new PurchaseItem[0];

    /**
     * Метод возвращает все элементы.
     * @return все элементы.
     */
    public PurchaseItem[] findAll() {
        PurchaseItem[] result = new PurchaseItem[items.length];
        System.arraycopy(items, 0, result, 0, items.length);
        return result;
    }

    /**
     * Метод сохраняет новый items в репозитории.
     * @param item сохраняемый элемент.
     */
    public void save(PurchaseItem item) {
        int length = items.length + 1;
        PurchaseItem[] tmp = new PurchaseItem[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        items = tmp;
    }

    /**
     * Возвращает элемент по заданному id.
     * Метод возвращает null, если элемента по заданному id не существует.
     * @param id id элемента
     * @return элемент по заданному id.
     */
    public PurchaseItem findById(int id) {
        if (id < 0 || id >= items.length) {
            return null;
        }
        return items[id];
    }

    /**
     * Метод удаляет элемент по заданному id.
     * Метод ничего не делает, если элемента с заданным id не существует.
     * @param id id удаляемого элемента.
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

    /**
     * Метод удаляет все элементы.
     */
    public void removeAll() {
        items = new PurchaseItem[0];
    }

}
