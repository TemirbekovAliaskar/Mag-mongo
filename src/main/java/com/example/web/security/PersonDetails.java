package com.example.web.security;

import com.example.web.models.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class PersonDetails implements UserDetails {

    private final Person person;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // возвращает лист из одного элемента
        return Collections.singletonList(new SimpleGrantedAuthority(person.getRole()));
    }

    //  Позволяет получить пароль пользователя
    @Override
    public String getPassword() {
        return this.person.getPassword();
    }

    // Позволяет получить логин пользователя
    @Override
    public String getUsername() {
        return this.person.getLogin();
    }

    // Аккаунт действителен
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Аккаунт не заблокирован
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Пароль являеться действительным/валидным
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Аккаунт активен
    @Override
    public boolean isEnabled() {
        return true;
    }

    // Метод по получанию объект пользователя
    public Person getPerson(){
        return this.person;
    }
}
