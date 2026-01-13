package com.at.zijun.service;

import com.at.zijun.common.BusinessException;
import com.at.zijun.dto.PageResult;
import com.at.zijun.mapper.ParcelMapper;
import com.at.zijun.pojo.Parcel;
import com.at.zijun.pojo.ParcelStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelService {
    private final ParcelMapper parcelMapper;

    public ParcelService(ParcelMapper parcelMapper) {
        this.parcelMapper = parcelMapper;
    }

    public List<Parcel> listAll() {
        return parcelMapper.findAll();
    }

    public Parcel getById(Long id) {
        return parcelMapper.findById(id).orElseThrow(() ->
                new RuntimeException("Parcel not found, id=" + id));
    }

    public Long create(Parcel parcel) {

        if (parcel.getTrackingNo() == null || parcel.getTrackingNo().isBlank()) {
            throw new BusinessException("Tracking No 不能为空");
        }

        String trackingNo = parcel.getTrackingNo().trim();

        if (parcelMapper.countByTrackingNo(trackingNo) > 0) {
            throw new BusinessException("Tracking No 已存在：" + trackingNo);
        }

        parcel.setTrackingNo(trackingNo);

        if (parcel.getStatus() == null) {
            parcel.setStatus(ParcelStatus.CREATED);
        }

        parcelMapper.insert(parcel);
        return parcel.getId();
    }

    public void update(Long id, Parcel parcel) {
        // 防止表单没带 id
        parcel.setId(id);

        // 编辑时 trackingNo 不允许改（即使前端 readonly，后端也要兜底）
        Parcel db = getById(id);
        parcel.setTrackingNo(db.getTrackingNo());

        if (parcel.getStatus() == null) {
            parcel.setStatus(db.getStatus());
        }

        int rows = parcelMapper.update(parcel);
        if (rows != 1) {
            throw new BusinessException("更新失败，id=" + id);
        }
    }

    public void delete(Long id) {
        int rows = parcelMapper.deleteById(id);
        if (rows != 1) {
            throw new BusinessException("删除失败，id=" + id);
        }
    }
    public List<Parcel> list(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return parcelMapper.findAll();
        }
        return parcelMapper.searchByTrackingNo(keyword.trim());
    }public PageResult<Parcel> page(String keyword, int page, int size) {
        int safePage = Math.max(page, 1);
        int safeSize = Math.min(Math.max(size, 5), 50); // 5~50
        int offset = (safePage - 1) * safeSize;

        boolean hasKeyword = keyword != null && !keyword.isBlank();
        if (!hasKeyword) {
            int total = parcelMapper.countAll();
            var list = parcelMapper.pageAll(offset, safeSize);
            return new PageResult<>(list, total, safePage, safeSize);
        }

        String kw = keyword.trim();
        int total = parcelMapper.countByTrackingNoLike(kw);
        var list = parcelMapper.pageByTrackingNoLike(kw, offset, safeSize);
        return new PageResult<>(list, total, safePage, safeSize);
    }
}
