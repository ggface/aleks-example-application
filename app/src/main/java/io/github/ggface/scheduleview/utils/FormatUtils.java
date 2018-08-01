package io.github.ggface.scheduleview.utils;

import android.support.annotation.NonNull;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import io.github.ggface.api.beans.EventBean;

/**
 * Класс для форматирования строк, дат и тд.
 *
 * @author Ivan Novikov on 2018-08-01.
 */
public class FormatUtils {

    private static final ThreadLocal<DateTimeFormatter> LONG_DAY = new ThreadLocal<DateTimeFormatter>() {
        @Override
        protected DateTimeFormatter initialValue() {
            return DateTimeFormat.forPattern("EEEE, d MMMM");
        }
    };

    /**
     * Форматирует диапазон события по времени
     *
     * @param eventBean событие
     * @return форматированная строка
     */
    public static String formatTimeRange(@NonNull EventBean eventBean) {
        return String.format("%s - %s", eventBean.getStartTime(), eventBean.getEndTime());
    }

    /**
     * Форматирует дату для вывода даты создания ордера
     *
     * @param dateTime время
     * @return строка в формате "Среда, 1 Августа"
     */
    public static String formatDayLong(DateTime dateTime) {
        return LONG_DAY.get().print(dateTime);
    }
}
