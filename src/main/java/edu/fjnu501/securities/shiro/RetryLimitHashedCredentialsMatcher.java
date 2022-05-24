package edu.fjnu501.securities.shiro;


import edu.fjnu501.securities.domain.User;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, Integer> cache;

    private Integer retryMax;

    public Integer getRetryMax() {
        return retryMax;
    }

    public void setRetryMax(Integer retryMax) {
        this.retryMax = retryMax;
    }

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        cache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws ExcessiveAttemptsException {
        User principal = (User) token.getPrincipal();
        String username = principal.getUsername();
        Integer retryCount = cache.get(username);
        if(retryCount == null) {
            retryCount = new Integer(0);
            cache.put(username, retryCount);
        }

        if(retryCount >= retryMax) {
            throw new ExcessiveAttemptsException("您已连续错误达" + retryMax + "次！请30秒后再试");
        }

        cache.put(username, ++retryCount);

        //调用父类的校验方法
        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            cache.remove(username);
        }else {
            throw new IncorrectCredentialsException("密码错误，已错误" + retryCount + "次，最多错误" + retryMax + "次");
        }
        return true;
    }

}
