package com.example.notice.repository;

import com.example.notice.entity.Member;
import com.example.notice.entity.Notice;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class NoticeRepositoryTests {
    @Autowired
    NoticeRepository noticeRepository;

    @Test
    public void insertNotice() {
        IntStream.rangeClosed(1, 99).forEach(i ->{
            Member member = Member.builder()
                    .email("user" + i + "@kopo.ac.kr")
                    .build();
            Notice notice = Notice.builder()
                    .title("Title" + i)
                    .content("Content" + i)
                    .writer(member)
                    .build();
            noticeRepository.save(notice);
        });
    }

    @Transactional
    @Test
    public void testRead(){
        Optional<Notice> result = noticeRepository.findById(5L);
        Notice notice = result.get();
        System.out.println(notice);
        System.out.println(notice.getWriter());
    }
    @Test
    public void testReadWithWriter(){
        Object result = noticeRepository.getNoticeWithWriter(5L);
        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testRead2(){
        Object result = noticeRepository.getNoticeByBno(99L);
        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testSearch1(){
        noticeRepository.search1();
    }

    @Test
    public void testSearchPage(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending().and(Sort.by("title").ascending()));
        noticeRepository.searchPage("t", "5", pageable);
    }
}
