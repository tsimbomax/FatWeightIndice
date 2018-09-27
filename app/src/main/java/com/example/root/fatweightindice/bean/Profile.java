package com.example.root.fatweightindice.bean;

import android.support.annotation.NonNull;

import com.example.root.fatweightindice.business.DateUtil;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.Date;

public class Profile implements Serializable, Comparable{

    private Date date;
    private Integer weight;
    private Integer size;
    private Integer age;
    private Integer sex;
    private Float fwi;
    private String comment;

    /**
     * Public and unique constructor taking four parameters.
     * Its existance allow us to leave the setters
     * @param weight : express in kilogram
     * @param size : express in centimeter
     * @param age : only the years
     * @param sex : 0 for a women and 1 for men
     */
    public Profile(Date date, Integer weight, Integer size, Integer age, Integer sex){

        this.date = date;
        this.weight = weight;
        this.size = size;
        this.age = age;
        this.sex = sex;
    }

    /**
     * The getter of the date's attribute
     * @return the date where the user has save its profile
     */
    public Date getDate() {
        return date;
    }

    /**
     * The getter of the weight's attribute
     * @return the weight of the calling instance
     */
    public Integer getWeight(){
        return this.weight;
    }

    /**
     * The getter of size's attribute
     * @return the size of the calling profile instance
     */
    public Integer getSize(){
        return this.size;
    }

    /**
     * The getter of the age's attribute
     * @return the age of the calling profile instance
     */
    public Integer getAge(){
        return this.age;
    }

    /**
     * The getter of the sex's attribute
     * @return the sex of the calling profile instance
     */
    public Integer getSex(){
        return this.sex;
    }

    /**'
     *
     * @return the fat weight indice of the calling instance profile
     */
    public Float getFwi() {
        return fwi;
    }

    /**
     * Sets the fat weight indice of the calling instance profile
     * @param fwi
     */
    public void setFwi(Float fwi) {
        this.fwi = fwi;
    }

    /**
     *
     * @return the associate comment to the img of the calling instance
     */
    public String getComment() {
        return comment;
    }

    /**
     * set the comment about the fat weight indice computed
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Convert an instance of Profile into JSONArray
     * @return the jsonArray data associate to the current profile.
     */
    public JSONArray convertProfileToJSONArray(){
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(DateUtil.convertDateToString(this.getDate()));
        jsonArray.put(this.getWeight());
        jsonArray.put(this.getSize());
        jsonArray.put(this.getAge());
        jsonArray.put(this.getSex());
        jsonArray.put(this.getFwi());
        jsonArray.put(this.getComment());
        return jsonArray;
    }

    /**
     * Compares this profile with the specified profile for order.  Returns a
     * negative integer, zero, or a positive integer as this profile.date is less
     * than, equal to, or greater than the specified profile.date.
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(@NonNull Object profile) {
        return this.getDate().compareTo(((Profile)profile).getDate());
    }

}
