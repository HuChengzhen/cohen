package com.huchengzhen.cohen.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
public class UserDetail extends User {
    private List<Integer> follows;
    private List<Integer> fans;
}
