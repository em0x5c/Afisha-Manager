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
        return items;
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
        for (PurchaseItem item : items) {
            if (item.getFilmId() == id)
                return item;
        }

        return null;
    }

    /**
     * Метод удаляет элемент по заданному id.
     * Метод ничего не делает, если элемента с заданным id не существует.
     * @param id id удаляемого элемента.
     */
    public void removeItemById(int id) {
        for (int i = 0; i < items.length; i++) {
            PurchaseItem item = items[i];
            if (item.getFilmId() == id) {
                PurchaseItem[] tmp = new PurchaseItem[items.length - 1];
                System.arraycopy(items, 0, tmp, 0, i);
                System.arraycopy(items, i + 1, tmp, i, items.length - (i + 1));
                items = tmp;
                return;
            }
        }
    }

    /**
     * Метод удаляет все элементы.
     */
    public void removeAll() {
        items = new PurchaseItem[0];
    }
}
