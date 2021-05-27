(function () {
    if (axboot && axboot.def) {
        axboot.def['DEFAULT_TAB_LIST'] = [
            {
                menuId: '00-dashboard',
                id: 'dashboard',
                progNm: '홈',
                menuNm: '홈',
                progPh: '/jsp/dashboard.jsp',
                url: '/jsp/dashboard.jsp?progCd=dashboard',
                status: 'on',
                fixed: true,
            },
        ];

        axboot.def['API'] = {
            users: '/api/v1/users',
            commonCodes: '/api/v1/commonCodes',
            programs: '/api/v1/programs',
            menu: '/api/v2/menu',
            manual: '/api/v1/manual',
            errorLogs: '/api/v1/errorLogs',
            files: '/api/v1/files',
            samples: '/api/v1/samples',
        };

        axboot.def['MODAL'] = {
            ZIPCODE: {
                width: 500,
                height: 500,
                iframe: {
                    url: '/jsp/common/zipcode.jsp',
                },
            },
            'SAMPLE-MODAL': {
                width: 500,
                height: 500,
                iframe: {
                    url: '/jsp/_samples/modal.jsp',
                },
                header: false,
            },
            COMMON_CODE_MODAL: {
                width: 600,
                height: 400,
                iframe: {
                    url: '/jsp/system/system-config-common-code-modal.jsp',
                },
                header: false,
            },
        };
    }

    var preDefineUrls = {
        manual_downloadForm: '/api/v1/manual/excel/downloadForm',
        manual_viewer: '/jsp/system/system-help-manual-view.jsp',
    };
    axboot.getURL = function (url) {
        if (ax5.util.isArray(url)) {
            if (url[0] in preDefineUrls) {
                url[0] = preDefineUrls[url[0]];
            }
            return url.join('/');
        } else {
            return url;
        }
    };

    /**
     * axboot.selectGenerator = function(groupCd, targetId, options) {
     * selector에 select를 넘긴다.
     * ex)
     * axboot.selectGenerator('USE_YN', '.class', {});
     */
    axboot.selectGenerator = function (groupCd, targetId, options) {
        if (!groupCd) throw new Error('에러 메시지');
        if (!targetId) throw new Error('에러 메시지');
        if (!options) options = {};
        if (parent) {
            var codes = parent.COMMON_CODE[groupCd] || [];
            var sb = [];
            codes.forEach(function (code) {
                // <option value="dog">Dog</option>;
                sb.push('<option value="' + (code.value || '') + '">' + (code.name || '') + '</option>');
            });
        }

        var html = sb.join('');
        console.log(html);
        console.log($(targetId));
        return $(targetId).html(html);
    };

    axboot.commonCodeGenerator = function (selector, options) {
        if (!selector) throw new Error('Invalid call : selector required');

        var $elements = $(selector);
        if (!$elements.length) throw new Error('Not found selector : ' + selector);

        console.log(selector);
        return $elements.each(function () {
            var $this = $(this);
            var config, sb, codes, type, groupCd;

            config = $.extend({}, $this.data(), options);
            type = (config.type || 'select').toLowerCase();
            groupCd = config.groupCd;
            if (!groupCd) groupCd = config.commonCode;
            if (!groupCd) throw new Error('groupCd is requierd option');

            if (parent && parent.COMMON_CODE) {
                codes = parent.COMMON_CODE[groupCd] || [];
            } else if (COMMON_CODE) {
                codes = COMMON_CODE[groupCd] || [];
            } else codes = [];

            sb = [];
            if (type == 'select') {
                if (typeof config.empty !== 'undefined') sb.push('<option value="">' + config.empty + '</option>');
                codes.forEach(function (code) {
                    sb.push('<option value="' + (code.code || '') + '">' + (code.name || '') + '</option>');
                });
                var html = sb.join('');
                // console.log(html);
                $(this).html(html);
            } else if (type == 'checkbox') {
                // <!-- <label><input type="checkbox" name="sttus" value="RSV_01">  예약  </label>
                codes.forEach(function (code) {
                    sb.push('<label><input type="checkbox" name="' + config.name + '" value="' + code.code + '"> ' + (code.name || '') + ' </label>');
                });

                var html = sb.join('');
                console.log(html);
                $(this).replaceWith(html);
            }
        });
    };
})();
