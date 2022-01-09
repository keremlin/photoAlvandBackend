package com.ara.photoalvand.viewModels;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ara.photoalvand.models.configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class vmConfigurations {
    public int id;
    public List<configuration> configurations;
}
