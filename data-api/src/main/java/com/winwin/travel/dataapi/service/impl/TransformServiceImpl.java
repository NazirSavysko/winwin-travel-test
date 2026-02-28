package com.winwin.travel.dataapi.service.impl;

import com.winwin.travel.dataapi.service.TransformService;
import org.springframework.stereotype.Service;

@Service
public final class TransformServiceImpl implements TransformService {
    @Override
    public String transform(final String text) {
        return text.toUpperCase();
    }
}
