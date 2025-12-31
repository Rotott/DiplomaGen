package org.example.diploma.core;

public record DiplomaData(
        String title,
        String subtitle,
        String awardText,
        String recipientLine,
        String detailsLine
) {}