package cm.fwi.beans;

import java.io.Serializable;
import java.util.Date;

public class Profile implements Serializable{

    private Integer id;
    private Date dte;
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

        this.dte = date;
        this.weight = weight;
        this.size = size;
        this.age = age;
        this.sex = sex;
    }
    
    /**
     * Get The identifier's profile
     * @return the identifier
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the identifier
     * @param id the identifier
     */
    public void setId( Integer id ) {
        this.id = id;
    }

    /**
     * The getter of the date's attribute
     * @return the date where the user has save its profile
     */
    public  Date getDte() {
        return dte;
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
}
