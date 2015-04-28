package com.cjl.poetryfan.di;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * com.cjl.poetryfan.di
 *
 * @author CJL
 * @since 2015-04-28
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface FilesDirectory {
}
