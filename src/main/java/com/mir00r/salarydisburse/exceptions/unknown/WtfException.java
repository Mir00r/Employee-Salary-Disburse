package com.mir00r.salarydisburse.exceptions.unknown;


import com.mir00r.salarydisburse.exceptions.forbidden.ForbiddenException;

public class WtfException extends ForbiddenException {
    public WtfException() {
    }

    public WtfException(String message) {
        super(message);
    }
}
