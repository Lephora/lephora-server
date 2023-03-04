package thoughtworks.lephora.server.lephoraserver.query.exception;

import thoughtworks.lephora.server.lephoraserver.core.exception.BusinessException;

import static thoughtworks.lephora.server.lephoraserver.query.constant.QueryErrorCode.COMMODITY_NOT_FOUND;

public class CommodityNotFoundException extends BusinessException {

    public CommodityNotFoundException(String message) {
        super(COMMODITY_NOT_FOUND, message);
    }
}
