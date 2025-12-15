package com.shop.ease.io;


import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class CategoryResponse {
     private String catagoryId;
     private String name;
	 private String description;
	 private String bgColor;
	 private Timestamp createdAt;
	 private Timestamp UpdatedAt;
	 private String imgUrl;
	 

}

