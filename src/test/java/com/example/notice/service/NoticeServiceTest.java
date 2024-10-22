package com.example.notice.service;

import com.example.notice.dto.NoticeDTO;
import com.example.notice.dto.PageRequestDTO;
import com.example.notice.dto.PageResultDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NoticeServiceTest {

    @Autowired
    private NoticeService noticeService;

    @Test
    public void testRegister() {
        NoticeDTO dto = NoticeDTO.builder()
                .title("Test Title")
                .content("Test Content")
                .writerEmail("user7@kopo.ac.kr")
                .build();

        Long bno = noticeService.register(dto);
        System.out.println("정상적으로 글이 작성 되었습니다." + bno);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<NoticeDTO, Object[]> result = noticeService.getList(pageRequestDTO);
        for(NoticeDTO noticeDTO : result.getDtoList()){
            System.out.println(noticeDTO);
        }
    }

    @Test
    void testGet(){
        Long bno = 101L;
        NoticeDTO noticeDTO = noticeService.get(bno);
        System.out.println(noticeDTO);
    }

    @Test
    void testRemove(){
        Long bno = 6L;
        noticeService.removewithReplies(bno);
    }

    @Test
    void testModify(){
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .bno(101L)
                .title("수정한 제목 연습")
                .content("수정한 내용 연습")
                .build();
        noticeService.modify(noticeDTO);
    }
}
