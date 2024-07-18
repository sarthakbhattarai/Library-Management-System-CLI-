package com.example.Library.Model;

import com.example.Library.Enum.UserEnum.MembershipType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;
    private String name;
    private String email;
    private MembershipType membershipType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(membershipType, user.membershipType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, email, membershipType);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", membershipType='" + membershipType + '\'' +
                '}';
    }
}
