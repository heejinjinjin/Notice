package com.example.notice.controller;

import com.example.notice.dto.NoticeDTO;
import com.example.notice.dto.PageRequestDTO;
import com.example.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/notice/")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/list")
    public void noticeList(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("result", noticeService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String registerPost(NoticeDTO dto, RedirectAttributes redirectAttributes) {
        Long bno = noticeService.register(dto);
        redirectAttributes.addFlashAttribute("msg", bno);
        return "redirect:/notice/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long bno, Model model){
        NoticeDTO noticeDTO = noticeService.get(bno);
        model.addAttribute("dto", noticeDTO);
    }

    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes) {
        noticeService.removewithReplies(bno);
        redirectAttributes.addFlashAttribute("msg", bno);
        return "redirect:/notice/list";
    }

    @PostMapping("/modify")
    public String modify(NoticeDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
        noticeService.modify(dto);
        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("bno", dto.getBno());
        return "redirect:/notice/read";
    }
}
