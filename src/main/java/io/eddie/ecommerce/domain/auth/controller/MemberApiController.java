package io.eddie.ecommerce.domain.auth.controller;

import io.eddie.ecommerce.domain.auth.model.dto.MemberSaveRequest;
import io.eddie.ecommerce.domain.auth.model.entity.Member;
import io.eddie.ecommerce.domain.auth.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Member> saveMember(
            @RequestBody MemberSaveRequest request
    ) {
        Member saved = memberService.save(request);
        return ResponseEntity.ok(saved);
    }


}
