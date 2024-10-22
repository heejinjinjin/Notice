package com.example.notice.repository.search;

import com.example.notice.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchNoticeRepository {
    Notice search1();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
