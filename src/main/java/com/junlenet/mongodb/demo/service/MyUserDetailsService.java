package com.junlenet.mongodb.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.junlenet.mongodb.demo.bo.UserBo;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		
		System.out.println("登录人名称:"+userName);
		
		//给登录人权限
		List<GrantedAuthority> resultAuths = new ArrayList<GrantedAuthority>();
		resultAuths.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return "ROLE_USER";
			}
		});
		resultAuths.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return "ROLE_BACK_USER";
			}
		});
		
		Query query = Query.query(Criteria.where("userName").is(userName));
		query.with(new Sort(Sort.Direction.DESC, "date"));
		
		UserBo userInfo = userService.findOne(query);
		
		if(userInfo == null ){
			return null;
		}
		
		return new UserDetails() {
			
			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean isAccountNonLocked() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean isAccountNonExpired() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public String getUsername() {
				// TODO Auto-generated method stub
				return userName;
			}
			
			@Override
			public String getPassword() {
				return userInfo.getPassword();
			}
			
			@Override
			public Collection<GrantedAuthority> getAuthorities() {
				return resultAuths;
			}
		};
	}

}
