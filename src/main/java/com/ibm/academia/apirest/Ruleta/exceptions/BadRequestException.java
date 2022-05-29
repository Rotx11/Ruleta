package com.ibm.academia.apirest.Ruleta.exceptions;

public class BadRequestException extends RuntimeException
{
    public BadRequestException(String message)
    {
        super(message);
    }
}
