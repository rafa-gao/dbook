package com.rafagao.dbook.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * dbook_product
 * @author 
 */
public class DbookProduct implements Serializable {
    /**
     * 商品ID
     */
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 卖家
     */
    private Long userId;

    /**
     * 售价
     */
    private BigDecimal price;

    /**
     * 促销价
     */
    private BigDecimal proPrice;

    /**
     * 销量
     */
    private Integer sale;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 0->不包邮，1->包邮
     */
    private Boolean isShip;

    /**
     * 邮费
     */
    private BigDecimal freeShip;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 上架状态：0->下架；1->上架
     */
    private Boolean publishStatus;

    /**
     * 审核状态：0->未审核；1->审核通过
     */
    private Integer verifyStatus;

    /**
     * 类别，根据类别表
     */
    private Integer classification;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 商品描述
     */
    private String description;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getProPrice() {
        return proPrice;
    }

    public void setProPrice(BigDecimal proPrice) {
        this.proPrice = proPrice;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getIsShip() {
        return isShip;
    }

    public void setIsShip(Boolean isShip) {
        this.isShip = isShip;
    }

    public BigDecimal getFreeShip() {
        return freeShip;
    }

    public void setFreeShip(BigDecimal freeShip) {
        this.freeShip = freeShip;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Boolean publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public Integer getClassification() {
        return classification;
    }

    public void setClassification(Integer classification) {
        this.classification = classification;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DbookProduct other = (DbookProduct) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getProPrice() == null ? other.getProPrice() == null : this.getProPrice().equals(other.getProPrice()))
            && (this.getSale() == null ? other.getSale() == null : this.getSale().equals(other.getSale()))
            && (this.getStock() == null ? other.getStock() == null : this.getStock().equals(other.getStock()))
            && (this.getIsShip() == null ? other.getIsShip() == null : this.getIsShip().equals(other.getIsShip()))
            && (this.getFreeShip() == null ? other.getFreeShip() == null : this.getFreeShip().equals(other.getFreeShip()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getPublishStatus() == null ? other.getPublishStatus() == null : this.getPublishStatus().equals(other.getPublishStatus()))
            && (this.getVerifyStatus() == null ? other.getVerifyStatus() == null : this.getVerifyStatus().equals(other.getVerifyStatus()))
            && (this.getClassification() == null ? other.getClassification() == null : this.getClassification().equals(other.getClassification()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getProPrice() == null) ? 0 : getProPrice().hashCode());
        result = prime * result + ((getSale() == null) ? 0 : getSale().hashCode());
        result = prime * result + ((getStock() == null) ? 0 : getStock().hashCode());
        result = prime * result + ((getIsShip() == null) ? 0 : getIsShip().hashCode());
        result = prime * result + ((getFreeShip() == null) ? 0 : getFreeShip().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getPublishStatus() == null) ? 0 : getPublishStatus().hashCode());
        result = prime * result + ((getVerifyStatus() == null) ? 0 : getVerifyStatus().hashCode());
        result = prime * result + ((getClassification() == null) ? 0 : getClassification().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", userId=").append(userId);
        sb.append(", price=").append(price);
        sb.append(", proPrice=").append(proPrice);
        sb.append(", sale=").append(sale);
        sb.append(", stock=").append(stock);
        sb.append(", isShip=").append(isShip);
        sb.append(", freeShip=").append(freeShip);
        sb.append(", image=").append(image);
        sb.append(", publishStatus=").append(publishStatus);
        sb.append(", verifyStatus=").append(verifyStatus);
        sb.append(", classification=").append(classification);
        sb.append(", sort=").append(sort);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}