package com.send.back.domain.response;

/**
 * 功能描述：请求成功返回
 *
 * @author 董森
 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
 * @since 2017/5/8
 */
public class Success extends Result {

    public Success() {
        super(1, null, null);
    }

    public Success(String info) {
        super(1, info, null);
    }

    public Success(String info, Object returnMEssage) {
        super(1, info, returnMEssage);
    }
}
