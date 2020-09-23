package com.ghsong.myboard.modules.entiry;

import com.ghsong.myboard.modules.entiry.dto.PostDto;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CommonDateEntity {

    @CreatedDate
    private LocalDateTime createedAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

}