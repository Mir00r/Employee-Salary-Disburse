package com.mir00r.salarydisburse.exceptions.limitExceed;


import com.mir00r.salarydisburse.exceptions.invalid.InvalidException;

public class LimitExceedException extends InvalidException {
    public LimitExceedException() {
    }

    public LimitExceedException(String message) {
        super(message);
    }

}
