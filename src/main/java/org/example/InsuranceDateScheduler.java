package org.example;

import java.sql.Date;
import java.util.Calendar;

public class InsuranceDateScheduler {

    private static final int[] SENDING_DAYS = {1, 10, 20};

    public Date findNextSendingDate(Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        for (int i = 0; i < 2; i++) {
            for (int day : SENDING_DAYS) {
                calendar.set(Calendar.DAY_OF_MONTH, day);
                Date sendingDate = new Date(calendar.getTimeInMillis());
                if (!currentDate.before(sendingDate)) {
                    return getVacCheck(sendingDate);
                }
            }
            calendar.add(Calendar.MONTH, 1);
        }

        return null;
    }

    private Date getVacCheck(Date modDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(modDate);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
            cal.add(Calendar.DATE, -2);
        }
        return new Date(cal.getTimeInMillis());
    }

    public static void main(String[] args) {
        InsuranceDateScheduler scheduler = new InsuranceDateScheduler();
        Date currentDate = new Date(System.currentTimeMillis());
        Date nextSendingDate = scheduler.findNextSendingDate(currentDate);
        System.out.println("Следующая дата отправки: " + nextSendingDate);
    }
}