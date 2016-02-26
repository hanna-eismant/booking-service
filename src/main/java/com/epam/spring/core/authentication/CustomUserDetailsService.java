package com.epam.spring.core.authentication;

import com.epam.spring.core.shared.BookingFacade;
import com.epam.spring.core.shared.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private BookingFacade bookingFacade;

    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        try {
            com.epam.spring.core.users.User user = bookingFacade.getUser(username);
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>(1);

            grantedAuthorityList.addAll(user.getRoles().stream().map(
                    role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList()));

//            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));

            User userDetails = new User(user.getName(),
                    user.getPassword(), grantedAuthorityList);
            return userDetails;

        } catch (NotFoundException _e) {
            throw new UsernameNotFoundException(_e.getMessage());
        } catch (Exception _e) {
            _e.printStackTrace();
        }

        return null;
    }
}
