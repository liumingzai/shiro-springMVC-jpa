package com.gome.splunk.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class PassWordService extends HashedCredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
	  return super.doCredentialsMatch(token, info);
	}
	
	

}
