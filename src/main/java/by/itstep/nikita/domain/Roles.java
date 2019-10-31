package by.itstep.nikita.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    USER, ADMIN, SECTOR_1, SECTOR_2, SECTOR_3, SECTOR_4, EXPERT, PTO;

    @Override
    public String getAuthority() {
        return name();
    }
}
