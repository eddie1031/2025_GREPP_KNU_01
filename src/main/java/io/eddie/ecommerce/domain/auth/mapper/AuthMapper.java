package io.eddie.ecommerce.domain.auth.mapper;

import io.eddie.ecommerce.domain.auth.model.dto.MemberDetails;
import io.eddie.ecommerce.domain.auth.model.entity.Member;

public class AuthMapper {

    public static MemberDetails toDetails(Member member) {
        return new MemberDetails(
                member.getUsername(),
                member.getPassword(),
                member.getRole()
        );
    }

}
