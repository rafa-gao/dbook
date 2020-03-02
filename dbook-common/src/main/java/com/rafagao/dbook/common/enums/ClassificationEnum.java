package com.rafagao.dbook.common.enums;

/**
 * @author rafa gao
 */

public enum  ClassificationEnum  {

    NATURAL_SCIENCES(1000,"自然科学"),
    SOCIAL_ETHICS(2000,"社会伦理"),
    ASTRONOMY_GEOGRAPHY(3000, "天文地理"),
    AEROSPACE(4000, "航空航天"),
    CURRENTAFFAIRS_MILITARY(5000,"时政军事")
    ;

    private Integer classificationCode;

    private String descrption;

    ClassificationEnum(Integer classificationCode, String descrption) {
        this.classificationCode = classificationCode;
        this.descrption = descrption;
    }

    public Integer getClassificationCode() {
        return classificationCode;
    }

    public String getDescrption() {
        return descrption;
    }
}
