package com.example.notice.repository;

import com.example.notice.entity.Notice;
import com.example.notice.repository.search.SearchNoticeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticeRepository extends JpaRepository<Notice, Long>, SearchNoticeRepository {

    @Query("select n, w from Notice n left join n.writer w where n.bno=:bno")
    Object getNoticeWithWriter(@Param("bno") Long bno);

    @Query("select n, w "
            + "from Notice n left join n.writer w "
            + "where n.bno=:bno group by n, w"
    )
    Object getNoticeByBno(@Param("bno")Long bno);

    @Query("select n, w "
            + "from Notice n left join n.writer w")
    Page<Object[]> getNotice(Pageable pageable);

}
