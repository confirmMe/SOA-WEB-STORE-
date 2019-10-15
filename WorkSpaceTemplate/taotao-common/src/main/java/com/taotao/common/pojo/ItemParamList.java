package com.taotao.common.pojo;

import java.util.Date;
/**
 * 针对商品商品规格目录列表显示
 * <p>Title: ItemParamList</p>
 * <p>Description: </p>
 * @author	阎述伟
 * @date	2018年12月6日下午11:22:51
 * @version 1.0
 */
public class ItemParamList {
    private Long id;

    private Long itemCatId;

    private Date created;

    private Date updated;

    private String paramData;
    
    private  String itemCatName;

    public String getItemCatName() {
		return itemCatName;
	}

	public void setItemCatName(String itemCatName) {
		this.itemCatName = itemCatName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemCatId() {
        return itemCatId;
    }

    public void setItemCatId(Long itemCatId) {
        this.itemCatId = itemCatId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData == null ? null : paramData.trim();
    }
}