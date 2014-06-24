package org.bundler;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * @author John Ericksen
 */
@Target(FIELD)
@Retention(SOURCE)
public @interface Bundled {
}
