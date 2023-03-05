package thoughtworks.lephora.server.lephoraserver.domain.exception;

import thoughtworks.lephora.server.lephoraserver.core.exception.BusinessException;

import static thoughtworks.lephora.server.lephoraserver.domain.constant.ErrorCode.CUSTOMER_NOT_FOUND;

public class CustomerNotFoundException extends BusinessException {

    public CustomerNotFoundException(String message) {
        super(CUSTOMER_NOT_FOUND, message);
    }
}
