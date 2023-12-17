package com.lms.isco.user.entity;

import com.lms.isco.user.entity.generic.UserCredential;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "m_student")
public class Student {
    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "mobile_phone", unique = true)
    private String mobilePhone;

    @Column(name = "address")
    private String address;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    @PrePersist
    private void onPersist() {
        if (createdAt == null) createdAt = System.currentTimeMillis();
    }

    @PreUpdate
    private void onUpdate() {
        if (updatedAt == null) updatedAt = System.currentTimeMillis();
    }
}
