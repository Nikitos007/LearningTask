'use strict';

export default class MainService {

    doAjax(options) {
        return new Promise(function (resolve, reject) {
            $.ajax(options)
                .done(resolve)
                .fail(reject);
        });
    }

    getAjaxOptions(typeReq, urlReq, dataReq) {
        return {
            type: typeReq,
            url: urlReq,
            data: JSON.stringify(dataReq),
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }
    }

}
