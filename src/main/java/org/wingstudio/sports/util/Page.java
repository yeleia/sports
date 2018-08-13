package org.wingstudio.sports.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("分页实体类")
public class Page {
    @ApiModelProperty("当前页数")
    private Integer tempPage;//当前页数
    @ApiModelProperty("每页显示的数据量")
    private Integer pageCapacity;//每页显示的数据量

    public Integer getTempPage() {
        return tempPage;
    }

    public void setTempPage(Integer tempPage) {
        this.tempPage = tempPage;
    }

    public Integer getPageCapacity() {
        return pageCapacity;
    }

    public void setPageCapacity(Integer pageCapacity) {
        this.pageCapacity = pageCapacity;
    }
}
