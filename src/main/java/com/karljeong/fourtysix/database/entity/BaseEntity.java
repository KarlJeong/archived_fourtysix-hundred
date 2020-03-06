package com.karljeong.fourtysix.database.entity;

import lombok.Data;

@Data
public class BaseEntity {

	private String createDatetime;
	private Long createUserId;
	private String updateDatetime;
	private Long updateUserId;

}
