package com.at.zijun.mapper;

import com.at.zijun.pojo.Parcel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;
@Mapper
public interface ParcelMapper {
    int insert(Parcel parcel);

    int update(Parcel parcel);

    int deleteById(@Param("id") Long id);

    Optional<Parcel> findById(@Param("id") Long id);

    List<Parcel> findAll();

    boolean existsByTrackingNo(@Param("trackingNo") String trackingNo);

    List<Parcel> searchByTrackingNo(@Param("keyword") String keyword);

    int countByTrackingNo(String trackingNo);

    int countAll();

    int countByTrackingNoLike(String keyword);

    List<Parcel> pageAll(int offset, int size);

    List<Parcel> pageByTrackingNoLike(String keyword, int offset, int size);

}
