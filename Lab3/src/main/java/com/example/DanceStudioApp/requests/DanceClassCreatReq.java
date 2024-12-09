package com.example.DanceStudioApp.requests;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DanceClassCreatReq {
    private String style;
    private String level;
    private String schedule;
    private Long studioId;
}
