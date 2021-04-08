package com.android.android_repo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImeiRequest {

    private String imei_value;
    private String imei_pass;
    private int day;
    private int hour;

}
