package com.winwin.travel.dataapi.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransformServiceImplTest {

    private final TransformServiceImpl transformService = new TransformServiceImpl();

    @Test
    void transform_shouldReturnUpperCase() {
        final String input = "hello world";
        final String expected = "HELLO WORLD";
        final String actual = this.transformService.transform(input);
        Assertions.assertEquals(expected, actual);
    }
}
