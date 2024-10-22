package com.example.notice.service;

import com.example.notice.dto.NoticeDTO;
import com.example.notice.entity.Member;
import com.example.notice.entity.Notice;
import com.example.notice.dto.PageRequestDTO;
import com.example.notice.dto.PageResultDTO;

public interface NoticeService {

    Long register(NoticeDTO dto);

    // 게시목록 처리 기능
    PageResultDTO<NoticeDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    // 특정 게시글 하나를 조회하는 기능
    NoticeDTO get(Long bno);

    // 삭제 기능
    void removewithReplies(Long bno);

    // 수정 기능
    void modify(NoticeDTO noticeDTO);

    // Entity를 DTO로 변환하는 메소드
    default NoticeDTO entityToDTO(Notice notice, Member member){
        NoticeDTO boardDTO = NoticeDTO.builder()
                .bno(notice.getBno())
                .title(notice.getTitle())
                .content(notice.getContent())
                .regDate(notice.getRegDate())
                .modDate(notice.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .build();
        return boardDTO;
    }

    // DTO를 Entity로 변환하는 메소드
    default Notice dtoToEntity(NoticeDTO dto){
        Member member = Member.builder()
                .email(dto.getWriterEmail())
                .build();
        Notice notice = Notice.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return notice;
    }
}
