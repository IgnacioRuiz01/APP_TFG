package com.vedruna.tfg.DTO;

public class ClassAdDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private int likeCount;
    private Long user_id;


    public ClassAdDTO(){

    }



    public ClassAdDTO(Long id, String title, String description, Double price, Long userId, int likeCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.user_id = userId;
        this.likeCount = likeCount;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long userId) {
        this.user_id = userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
