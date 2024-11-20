package com.tools.potato_field.category;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void loadData() {
        // location 하위 항목
        List<String> locations = Arrays.asList("공릉", "성수", "이태원", "홍대", "강남", "여의도");
        // TPO 하위 항목
        List<String> tpos = Arrays.asList("데일리", "데이트", "캠퍼스", "출근", "운동");

        // location 항목 저장
        locations.forEach(location -> {
            Category locationCategory = new Category(location, "location");
            categoryRepository.save(locationCategory);
        });

        // TPO 항목 저장
        tpos.forEach(tpo -> {
            Category tpoCategory = new Category(tpo, "TPO");
            categoryRepository.save(tpoCategory);
        });
    }
}
