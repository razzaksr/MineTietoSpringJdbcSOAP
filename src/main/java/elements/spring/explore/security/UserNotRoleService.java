package elements.spring.explore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserNotRoleService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public UserNotRole signIp(UserNotRole userNotRole){
        jdbcTemplate.update("insert into dum_emp values(?,?)",new Object[]{userNotRole.getUsername(),userNotRole.getPassword()});
        return userNotRole;
    }
    public UserNotRole lookUsername(String username){
        UserNotRole userNotRole = jdbcTemplate.queryForObject("select * from dum_emp where username=?",new Object[]{username},
                new BeanPropertyRowMapper<>(UserNotRole.class));
        return userNotRole;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserNotRole userNotRole=lookUsername(username);
        if(userNotRole==null){
            return (UserDetails) new UsernameNotFoundException("Invalid Username entered");
        }
        return userNotRole;
    }
}
