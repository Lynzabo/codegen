/**
 * Copyright (c) 2016 乐视云计算有限公司（lecloud.com）. All rights reserved
 */
package com.lynzabo.codegen.except;

/**
 * CodegenException
 * @author linzhanbo .
 * @since 2016年11月16日, 16:30 .
 * @version 1.0 .
 */
public class CodegenException extends RuntimeException {
    public CodegenException() {
    }

    public CodegenException(String msg) {
        super(msg);
    }

    public CodegenException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodegenException(Throwable cause) {
        super(cause);
    }
}
