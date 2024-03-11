package com.chat.app.chatapp.data.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
@Builder
public class UserEntity {

    @Id
    @Column(name = "id", length = 36, unique = true, nullable = false)
    private String id;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "created_at", updatable = false, insertable = true) // changed to true...
    @CreationTimestamp
    private LocalDateTime createdAt;

    private boolean deleted = false;

//    @Enumerated(STRING)
//    Status status;
}
