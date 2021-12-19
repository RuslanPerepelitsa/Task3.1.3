package com.example.task313.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@RequiredArgsConstructor
@Data
@Getter
@NoArgsConstructor
@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "role")
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }


    @Override
    public String toString() {
        return name;
    }
}

