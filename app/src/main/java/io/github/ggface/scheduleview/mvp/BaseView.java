package io.github.ggface.scheduleview.mvp;

/**
 * Базовый view
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public interface BaseView<T> {

    /**
     * Устанавливает видимость индикатора загрузки баланса
     *
     * @param active значение видимости
     */
    void setBalanceLoadingIndicator(boolean active);

    /**
     * Сообщает об ошибке
     *
     * @param code код ошибки
     */
    void showError(int code);
}