package se.buaa.Exception;

import se.buaa.Error.ErrorDict;
import se.buaa.Error.ErrorValue;

public class ScholarException extends AbstractException{
    private final String codeSuffix = "00"; // 待定

    public ScholarException(String code) {
        ErrorValue value = ErrorDict.getInstance().getValue(codeSuffix + code);
        this.errorCode = value.getCode();
        this.errorMsg = value.getMsg();
    }
}
