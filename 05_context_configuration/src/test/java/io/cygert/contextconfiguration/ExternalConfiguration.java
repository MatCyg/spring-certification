package io.cygert.contextconfiguration;

import io.cygert.contextconfiguration.Foo;
import org.springframework.context.annotation.Import;

@Import(Foo.class)
class ExternalConfiguration {
}
