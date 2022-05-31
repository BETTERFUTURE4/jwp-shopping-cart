package woowacourse.shoppingcart.ui;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import woowacourse.auth.dto.TokenRequest;
import woowacourse.auth.support.AuthenticationPrincipal;
import woowacourse.shoppingcart.application.CustomerService;
import woowacourse.shoppingcart.dto.CustomerLoginRequest;
import woowacourse.shoppingcart.dto.CustomerLoginResponse;
import woowacourse.shoppingcart.dto.CustomerRequest;
import woowacourse.shoppingcart.dto.CustomerResponse;
import woowacourse.shoppingcart.dto.CustomerUpdateRequest;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers/signUp")
    public ResponseEntity<Void> signUp(final @RequestBody CustomerRequest request) {
        Long id = customerService.signUp(request);
        return ResponseEntity.created(URI.create("/customers/" + id)).build();
    }

    @PostMapping("/customers/login")
    public ResponseEntity<CustomerLoginResponse> login(final @RequestBody CustomerLoginRequest request) {
        CustomerLoginResponse response = customerService.login(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/auth/customers/profile")
    public ResponseEntity<CustomerResponse> getProfile(final @AuthenticationPrincipal TokenRequest request) {
        CustomerResponse response = customerService.findById(request);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/auth/customers/profile")
    public ResponseEntity<Void> updateProfile(final @AuthenticationPrincipal TokenRequest tokenRequest,
                                              final @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        customerService.update(tokenRequest, customerUpdateRequest);
        return ResponseEntity.ok().build();
    }
}
