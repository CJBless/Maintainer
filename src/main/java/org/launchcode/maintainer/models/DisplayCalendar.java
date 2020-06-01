package org.launchcode.maintainer.models;

import java.time.LocalDateTime;
import java.util.Calendar;

public class DisplayCalendar extends Calendar{

    private Calendar cal;
    private LocalDateTime today = LocalDateTime.now();

    public DisplayCalendar(){
        this.cal.set(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
    }

    @Override
    protected void computeTime() {

    }

    @Override
    protected void computeFields() {

    }

    @Override
    public void add(int field, int amount) {

    }

    @Override
    public void roll(int field, boolean up) {
    }

    @Override
    public int getMinimum(int field) {
        return 0;
    }

    @Override
    public int getMaximum(int field) {
        return 0;
    }

    @Override
    public int getGreatestMinimum(int field) {
        return 0;
    }

    @Override
    public int getLeastMaximum(int field) {
        return 0;
    }

        public Calendar getThisMonthData(){
        LocalDateTime today = LocalDateTime.now();
        Calendar calendar = Calendar.getInstance();
        calendar.set(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        return calendar;
    }

    public Calendar getNextMonthData(int aMonth, int aYear){
        Calendar cal = Calendar.getInstance();
        if(aMonth + 1 == 11){
            cal.roll(Calendar.YEAR, true);
            cal.set(Calendar.MONTH, 0);
        } else{
            cal.roll(Calendar.MONTH, true);
            cal.set(Calendar.YEAR, aYear);
        }
    return cal;
    }

}
