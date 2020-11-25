package com.ubiqube.etsi.mano.nfvo.v331.controller.vnf;

public class NotFoundException extends ApiException {
    private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
