package com.ms.historico.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CampoObrigatorioException extends ResponseStatusException {

    public CampoObrigatorioException(String mensagem) {
        super(HttpStatus.BAD_REQUEST, mensagem);
    }
}