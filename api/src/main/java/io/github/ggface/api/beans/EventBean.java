package io.github.ggface.api.beans;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;

import io.github.ggface.api.utils.PojoUtils;
import io.github.ggface.api.utils.StringUtils;

/**
 * Описывает событие рассписания.
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public class EventBean implements Parcelable {

    public static final Creator<EventBean> CREATOR = new ClassCreator();

    private final String mName;
    private final Date mStartTime;
    private final Date mEndTime;
    private final String mTeacher;
    private final String mPlace;
    private final String mDescription;
    private final int mWeekDay;

    public EventBean(@Nullable String name,
                     @Nullable Date startTime,
                     @Nullable Date endTime,
                     @Nullable String teacher,
                     @Nullable String place,
                     @Nullable String description,
                     int weekDay) {
        mName = name;
        mStartTime = startTime;
        mEndTime = endTime;
        mTeacher = teacher;
        mPlace = place;
        mDescription = description;
        mWeekDay = weekDay;
    }

    protected EventBean(Parcel parcel) {
        mName = parcel.readString();
        mStartTime = new Date(parcel.readLong());
        mEndTime = new Date(parcel.readLong());
        mTeacher = parcel.readString();
        mPlace = parcel.readString();
        mDescription = parcel.readString();
        mWeekDay = parcel.readInt();
    }

    //region Getter

    /**
     * @return название события
     */
    @NonNull
    public String getName() {
        return StringUtils.nonNull(mName);
    }

    /**
     * @return время начала
     */
    @NonNull
    public Date getStartTime() {
        return mStartTime;
    }

    /**
     * @return время окончания
     */
    @NonNull
    public Date getEndTime() {
        return mEndTime;
    }

    /**
     * @return имя инструктора
     */
    @NonNull
    public String getTeacher() {
        return StringUtils.nonNull(mTeacher);
    }

    /**
     * @return место проведения события
     */
    @NonNull
    public String getPlace() {
        return StringUtils.nonNull(mPlace);
    }

    /**
     * @return описание события
     */
    @NonNull
    public String getDescription() {
        return StringUtils.nonNull(mDescription);
    }

    /**
     * @return день недели (от 1 до 7)
     */
    public int getWeekDay() {
        return mWeekDay;
    }
    //endregion Getter

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EventBean other = (EventBean) o;
        return PojoUtils.equal(mName, other.mName) &&
                PojoUtils.equal(mStartTime, other.mStartTime) &&
                PojoUtils.equal(mEndTime, other.mEndTime) &&
                PojoUtils.equal(mTeacher, other.mTeacher) &&
                PojoUtils.equal(mPlace, other.mPlace) &&
                PojoUtils.equal(mDescription, other.mDescription) &&
                PojoUtils.equal(mWeekDay, other.mWeekDay);
    }

    @Override
    public int hashCode() {
        return PojoUtils.hashCode(mName, mStartTime, mEndTime, mTeacher, mPlace, mDescription,
                mWeekDay);
    }


    //region Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeLong(mStartTime.getTime());
        dest.writeLong(mEndTime.getTime());
        dest.writeString(mTeacher);
        dest.writeString(mPlace);
        dest.writeString(mDescription);
        dest.writeInt(mWeekDay);

    }
    //endregion Parcelable

    private static final class ClassCreator implements Creator<EventBean> {
        @Override
        public EventBean createFromParcel(Parcel parcel) {
            return new EventBean(parcel);
        }

        @Override
        public EventBean[] newArray(int size) {
            return new EventBean[size];
        }
    }
}