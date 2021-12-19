package com.fsoft.igos.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fsoft.igos.utils.ValidateUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO extends MetaResponseDTO implements Serializable {
    private static final long serialVersionUID = 1116091598299674086L;

    private Object data;

    private Integer total;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = ValidateUtil.isNullOrEmpty(data) ? new ArrayList<>() : data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
