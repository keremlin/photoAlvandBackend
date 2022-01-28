package com.ara.photoalvand.viewModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class vmReturnObject implements IReturnObject {
    int id;
    int code;
    String message;
    String name;
}
