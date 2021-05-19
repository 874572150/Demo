package cn.os.test.common.config.auditor;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author oushuo
 * @date 2020/10/20
 * @description 审计，@CreateBy @LastModifiedBy实现
 */
@Component("auditorAware")
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
//        return Optional.empty();
        return Optional.of("tempUser");
    }
}
