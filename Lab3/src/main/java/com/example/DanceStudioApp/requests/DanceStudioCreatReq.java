package com.example.DanceStudioApp.requests;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DanceStudioCreatReq {
    private String name;
    private String address;
    private String phone;
}
