package com.ara.photoalvand.viewModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class vmReturnObject {
    int id;
    String message;
    String name;
    int code;

}
