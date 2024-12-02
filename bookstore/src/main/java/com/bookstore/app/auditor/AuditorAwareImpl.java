package com.bookstore.app.auditor;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //TODO Change the implementation when adding Spring Security
        return Optional.of("<na>");
    }
}
