/**
 * Created by Administrator on 2017/6/8.
 */
(function ($) {
jQuery.extend({
    /**
     * 使用参数格式化字符串
     * source：字符串模式 abcdef{0}-{1}，
     * params：参数数组，参数序号对应模式中的下标
     */
    format: function(source, params) {
        if ( arguments.length == 1 )
            return function() {
                var args = $.makeArray(arguments);
                args.unshift(source);
                return $.format.apply( this, args );
            };
        if ( arguments.length > 2 && params.constructor != Array  ) {
            params = $.makeArray(arguments).slice(1);
        }
        if ( params.constructor != Array ) {
            params = [ params ];
        }
        $.each(params, function(i, n) {
            source = source.replace(new RegExp('\\{' + i + '\\}', 'g'), n);
        });
        return source;
    }

});

})(window.jQuery);
