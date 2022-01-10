package io.to.app.exposition;

import io.to.app.application.member.CreateMember;
import io.to.app.application.member.RetrieveMemberById;
import io.to.app.application.member.RetrieveMembers;
import io.to.app.domain.Member;
import io.to.app.domain.MemberId;
import io.to.kernel.CommandBus;
import io.to.kernel.QueryBus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public MemberController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MembersResponse> getAll() {
        final List<Member> members = queryBus.send(new RetrieveMembers());
        MembersResponse membersResponseResult = new MembersResponse(members.stream().map(
                member -> new MemberResponse( String.valueOf(member.getId().getValue()), member.getLastname(), member.getFirstname(), member.getEmail(), member.isSubscriber())
        ).collect(Collectors.toList()));
        return ResponseEntity.ok(membersResponseResult);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberResponse> getOne(@PathVariable(value = "id") int memberId) {
        final Member member = queryBus.send(new RetrieveMemberById(new MemberId(memberId)));
        MemberResponse memberResponse = new MemberResponse(String.valueOf(member.getId().getValue()), member.getLastname(), member.getFirstname(), member.getEmail(), member.isSubscriber());
        return ResponseEntity.ok(memberResponse);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid MemberRequest request) {
        CreateMember createMember = new CreateMember(request.lastname, request.firstname, request.email, request.password, request.isSubscriber);
        MemberId memberId = commandBus.send(createMember);
        return ResponseEntity.created(URI.create("/members/" + memberId.getValue())).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
