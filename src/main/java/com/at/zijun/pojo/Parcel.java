package com.at.zijun.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parcel {
        private Long id;
        private String trackingNo;
        private String senderName;
        private String receiverName;
        private String receiverPhone;
        private String address;
        private ParcelStatus status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
