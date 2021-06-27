package io.cygert.scopedproxy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@SpringBootApplication
class ScopedProxyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScopedProxyApplication.class, args);
    }
}

@RestController
@RequiredArgsConstructor
class ScopeController {
    private final ThemeManager themeManager;

    @GetMapping("/scope")
    String scopeTest() {
        return themeManager.username();
    }

}

@Component
@RequiredArgsConstructor
class ThemeManager {
    private final UserSettings userSettings;

    public String username() {
        return userSettings.username();
    }
}

interface UserSettings {
    String username();
}

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.INTERFACES)
// try with proxyMode = ScopedProxyMode.TARGET_CLASS
// try with value = ConfigurableBeanFactory.SCOPE_PROTOTYPE
class UserSettingsImpl implements UserSettings {
    private final String username = UUID.randomUUID().toString();

    @Override
    public String username() {
        return username;
    }
}

