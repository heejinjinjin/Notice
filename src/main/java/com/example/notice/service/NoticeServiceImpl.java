package com.example.notice.service;

import com.example.notice.dto.NoticeDTO;
import com.example.notice.dto.PageRequestDTO;
import com.example.notice.dto.PageResultDTO;
import com.example.notice.entity.Member;
import com.example.notice.entity.Notice;
import com.example.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    @Override
    public Long register(NoticeDTO dto) {
        Notice notice = dtoToEntity(dto);
        noticeRepository.save(notice);

        return notice.getBno();
    }

    @Override
    public PageResultDTO<NoticeDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        Function<Object[], NoticeDTO> fn = (en -> entityToDTO((Notice) en[0], (Member) en[1]));
        //Page<Object[]> result = noticeRepository.getNotice(pageRequestDTO.getPageable(Sort.by("bno").descending()));
        Page<Object[]> result = noticeRepository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("bno").descending())
        );
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public NoticeDTO get(Long bno) {
        Object result = noticeRepository.getNoticeByBno(bno);
        Object[] arr = (Object[]) result;
        NoticeDTO noticeDTO = entityToDTO((Notice) arr[0], (Member) arr[1]);
        return noticeDTO;
    }

    @Transactional
    @Override
    public void removewithReplies(Long bno) {
        // 원글 삭제
        noticeRepository.deleteById(bno);
    }

    @Transactional
    @Override
    public void modify(NoticeDTO boardDTO) {
        Notice notice = noticeRepository.getReferenceById(boardDTO.getBno());
        notice.changeTitle(boardDTO.getTitle());
        notice.changeContent(boardDTO.getContent());
        noticeRepository.save(notice);
    }
}
