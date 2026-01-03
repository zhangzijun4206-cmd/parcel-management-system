package com.at.zijun.controller;


import com.at.zijun.common.BusinessException;
import com.at.zijun.dto.PageResult;
import com.at.zijun.pojo.Parcel;
import com.at.zijun.pojo.ParcelStatus;
import com.at.zijun.service.ParcelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/parcels")
public class ParcelPageController {
    private final ParcelService parcelService;

    public ParcelPageController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    // 列表页
    @GetMapping
    public String list(@RequestParam(required = false) String keyword,
                       @RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int size,
                       Model model) {

        PageResult<Parcel> result = parcelService.page(keyword, page, size);

        model.addAttribute("parcels", result.getList());
        model.addAttribute("total", result.getTotal());
        model.addAttribute("page", result.getPage());
        model.addAttribute("size", result.getSize());
        model.addAttribute("totalPages", result.getTotalPages());
        model.addAttribute("hasPrev", result.isHasPrev());
        model.addAttribute("hasNext", result.isHasNext());
        model.addAttribute("keyword", keyword);

        return "parcel/list";
    }

    // 新增表单页
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("parcel", new Parcel());
        model.addAttribute("statusList", ParcelStatus.values());
        return "parcel/form";
    }

    // 提交新增
    @PostMapping
    public String create(@ModelAttribute Parcel parcel, Model model) {
        try {
            parcelService.create(parcel);
            return "redirect:/parcels";
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("parcel", parcel);
            model.addAttribute("statusList", ParcelStatus.values());
            return "parcel/form";
        }
    }

    // 编辑表单页
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("parcel", parcelService.getById(id));
        model.addAttribute("statusList", ParcelStatus.values());
        return "parcel/form";
    }


    // 提交编辑
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Parcel parcel, Model model) {
        try {
            parcelService.update(id, parcel);
            return "redirect:/parcels";
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            parcel.setId(id);
            model.addAttribute("parcel", parcel);
            model.addAttribute("statusList", ParcelStatus.values());
            return "parcel/form";
        }
    }

    // 删除
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        parcelService.delete(id);
        return "redirect:/parcels";
    }
}
