package ru.otus;

public abstract class AbstractSingleton<T> {
    // хранит единственный экземпляр класса
    private T instance;
    // абстрактный метод для создания экземпляра
    protected abstract T createInstance();
    // проверяет, что инстанса нет, если нет - создает createInstance() и сохраняет в инстанс
    public synchronized T getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }
}
