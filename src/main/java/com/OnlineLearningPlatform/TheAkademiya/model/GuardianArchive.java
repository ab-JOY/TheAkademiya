package com.OnlineLearningPlatform.TheAkademiya.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(
                name = "nameArchived",
                column = @Column(name = "guardian_name_archived")
        ),
        @AttributeOverride(
                name = "emailArchived",
                column = @Column(name = "guardian_email_archived")
        ),
        @AttributeOverride(
                name = "mobile",
                column = @Column(name = "guardian_mobile_archived")
        )
})
public class GuardianArchive {

    private String nameArchived;
    private String emailArchived;
    private String mobileArchived;
}
