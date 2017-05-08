package com.send.back.domain.response;


/**
 * 功能描述：请求失败返回
 *
 * @author 董森
 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
 * @since 2017/5/8
 */
public class Failed extends Result {
    public Failed() {
        super(0, null, null);
    }

    public Failed(String info) {
        super(0, info, null);
    }

    public Failed(String info, Object returnMessage) {
        super(0, info, returnMessage);
    }
}
