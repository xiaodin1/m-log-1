/**
 * 
 */
package org.mspring.mlog.web.formatter.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Gao Youbo
 * @since 2012-7-25
 * @Description
 * @TODO
 */
// @Target(value = { ElementType.FIELD, ElementType.METHOD,
// ElementType.PARAMETER })
// @Retention(RetentionPolicy.RUNTIME)
// @Documented
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface TagFormat {

}
