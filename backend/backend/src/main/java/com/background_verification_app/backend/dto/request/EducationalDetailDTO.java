package com.background_verification_app.backend.dto.request;

import ch.qos.logback.classic.spi.LoggingEventVO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationalDetailDTO {
    private String sslcSchoolName;
    private double sslcPercentage;

    private String hslcSchoolName;
    private double hslcPercentage;

    private String collegeName;
    private String universityRollNumber;
    private double finalCgpa;


}
