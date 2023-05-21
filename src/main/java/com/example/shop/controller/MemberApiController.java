package com.example.shop.controller;

import com.example.shop.domain.Member;
import com.example.shop.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("api/v1/members")
    public  CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("api/v2/members")
    public  CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest username){
        Member member = new Member();
        member.setUsername(username.username);
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }


    record CreateMemberResponse(Long id){
        private static String thisIsPossible = "thisIs";

    }

    record CreateMemberRequest(String username){

    }

    @PutMapping("api/v1/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request
    ){
        Member member = new Member();
        member.setId(id);
        member.setUsername(request.username);
        Long memberId = memberService.join(member);

        return new UpdateMemberResponse(request.username, memberId);
    }

    record UpdateMemberResponse(@NotEmpty String username, Long id){}
    record UpdateMemberRequest(String username){}

    @GetMapping("/api/v2/members")
    public Result memberV2(){
        List<Member> findMember = memberService.findMembers();
        List<MemberDto> collect = findMember.stream()
                .map(m -> new MemberDto(m.getUsername())).collect(Collectors.toList());
        return new Result(collect);
    }
    @Getter
    @Setter
    @AllArgsConstructor
    static class Result<T>{
        T data;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    static class MemberDto{
        String name;


    }


}
