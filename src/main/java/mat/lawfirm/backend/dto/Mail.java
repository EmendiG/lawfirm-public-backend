package mat.lawfirm.backend.dto;

import lombok.Data;

@Data
public class Mail {

    private String fullName;
    private String phoneNumber;
    private String email;
    private String caseDescription;

}
