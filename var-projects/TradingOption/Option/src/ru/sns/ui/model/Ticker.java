package ru.sns.ui.model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Код контракта;
 * Дата исполнения;
 * Средневзвешенная цена;
 * Цена первой сделки;
 * Максимальная цена;
 * Минимальная цена;
 * Цена последней сделки;
 * Изменение;
 * Объем последней сделки, контр.;
 * Число сделок;
 * Объем торгов, руб.;
 * Объем торгов, контр.;
 * Объем открытых позиций, руб.;
 * Объем открытых позиций, кол-во;
 */
public class Ticker {
    private String contractCode;
    private Date exerciseDate;
    private double priceAvg;
    private double priceMax;
    private double priceMin;
    private double priceLast;
    private double change;
    private int volumeLast;
    private int deals;
    private double tradeVolume;
    private double tradeVolumeContr;
    private double openPositions;
    private int openContracts;

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Date getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public double getPriceAvg() {
        return priceAvg;
    }

    public void setPriceAvg(double priceAvg) {
        this.priceAvg = priceAvg;
    }

    public double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(double priceMax) {
        this.priceMax = priceMax;
    }

    public double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(double priceMin) {
        this.priceMin = priceMin;
    }

    public double getPriceLast() {
        return priceLast;
    }

    public void setPriceLast(double priceLast) {
        this.priceLast = priceLast;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public int getVolumeLast() {
        return volumeLast;
    }

    public void setVolumeLast(int volumeLast) {
        this.volumeLast = volumeLast;
    }

    public int getDeals() {
        return deals;
    }

    public void setDeals(int deals) {
        this.deals = deals;
    }

    public double getTradeVolume() {
        return tradeVolume;
    }

    public void setTradeVolume(double tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    public double getTradeVolumeContr() {
        return tradeVolumeContr;
    }

    public void setTradeVolumeContr(double tradeVolumeContr) {
        this.tradeVolumeContr = tradeVolumeContr;
    }

    public double getOpenPositions() {
        return openPositions;
    }

    public void setOpenPositions(double openPositions) {
        this.openPositions = openPositions;
    }

    public int getOpenContracts() {
        return openContracts;
    }

    public void setOpenContracts(int openContracts) {
        this.openContracts = openContracts;
    }


    public String toString() {
        return this.getContractCode() + "\n";
    }

    public static String[] getTitles(){
        String[] titles = new String[13];
        
        return titles;
    }

    public Object getValueByIndex(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return contractCode;
            case 1:
                return sdf.format(exerciseDate);
            case 2:
                return cf.format( priceAvg);
            case 3:
                return cf.format( priceMax);
            case 4:
                return cf.format( priceMin);
            case 5:
                return cf.format( priceLast);
            case 6:
                return change;
            case 7:
                return volumeLast;
            case 8:
                return deals;
            case 9:
                return cf.format( tradeVolume);
            case 10:
                return tradeVolumeContr;
            case 11:
                return cf.format( openPositions);
            case 12:
                return openContracts;
            default:
                return null;
        }
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy");
    NumberFormat cf = NumberFormat.getCurrencyInstance();

}
