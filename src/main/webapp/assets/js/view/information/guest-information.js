var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/guest',
            data: caller.searchView.getData(),
            callback: function (res) {
                caller.formView01.clear();
                caller.gridView01.setData(res);
            },
            options: {
                // axboot.ajax 함수에 2번째 인자는 필수가 아닙니다. ajax의 옵션을 전달하고자 할때 사용합니다.
                onError: function (err) {
                    console.log(err);
                },
            },
        });

        return false;
    },
    PAGE_SAVE: function (caller, act, data) {
        if (caller.formView01.validate()) {
            var item = caller.formView01.getData();
            if (!item.id) {
                axDialog.alert('수정 가능한 고객정보가 없습니다.');
                return false;
            }
            axboot.ajax({
                type: 'POST',
                url: '/api/v1/guest/' + item.id,
                data: JSON.stringify(item),
                callback: function (res) {
                    caller.formView01.clear();
                    caller.gridView02.clear();
                    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                    axToast.push('저장 되었습니다');
                },
            });
        }
    },
    ITEM_CLICK: function (caller, act, data) {
        var id = (data || {}).id;
        if (!id) {
            axDialog.alert('id는 필수입니다.');
            return false;
        }
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/guest/' + id,
            callback: function (res) {
                console.log(res);
                caller.formView01.setData(res);
                caller.gridView02.setData(res.chkList);
            },
        });
    },
    ITEM_ADD: function (caller, act, data) {
        caller.gridView01.addRow();
    },
    ITEM_DEL: function (caller, act, data) {
        caller.gridView01.delRow('selected');
    },
    FORM_CLEAR: function (caller, act, data) {
        axDialog.confirm({ msg: LANG('ax.script.form.clearconfirm') }, function () {
            if (this.key == 'ok') {
                caller.formView01.clear();
                $('[data-ax-path="guestNm"]').focus();
            }
        });
    },
    dispatch: function (caller, act, data) {
        var result = ACTIONS.exec(caller, act, data);
        if (result != 'error') {
            return result;
        } else {
            // 직접코딩
            return false;
        }
    },
});

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    this.pageButtonView.initView();
    this.searchView.initView();
    this.gridView01.initView();
    this.formView01.initView();
    this.gridView02.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageResize = function () {};

fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, 'data-page-btn', {
            search: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
            save: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            },
            excel: function () {},
        });
    },
});

//== view 시작
/**
 * searchView
 */
fnObj.searchView = axboot.viewExtend(axboot.searchView, {
    initView: function () {
        this.target = $(document['searchView0']);
        this.target.attr('onsubmit', 'return false;');
        this.guestNm = $('.js-guestNm');
        this.guestTel = $('.js-guestTel');
        this.email = $('.js-email');
    },
    getData: function () {
        return {
            pageNumber: this.pageNumber || 0,
            pageSize: this.pageSize || 50,
            guestNm: this.guestNm.val(),
            guestTel: this.guestTel.val(),
            email: this.email.val(),
        };
    },
});

/**
 * gridView
 */
fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            showRowSelector: true,
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                { key: 'guestNm', label: '이름', width: 100, align: 'center' },
                { key: 'guestTel', label: '연락처', width: 120, align: 'center' },
                { key: 'email', label: '이메일', width: 120, align: 'center' },
                { key: 'gender', label: '성별', width: 70, align: 'center' },
                { key: 'brth', label: '생년월일', width: 100, align: 'center' },
                {
                    key: 'langCd',
                    label: '언어',
                    width: 100,
                    align: 'center',
                    formatter: function () {
                        if (!this.value) return '';
                        return parent.COMMON_CODE['PMS_LANG'].map[this.value];
                    },
                    editor: {
                        type: 'select',
                        config: {
                            columnKeys: {
                                optionValue: 'CD',
                                optionText: 'NM',
                            },
                            options: [
                                { CD: 'KO', NM: '한국어' },
                                { CD: 'EN', NM: '영어' },
                                { CD: 'CH', NM: '중국어' },
                                { CD: 'JP', NM: '일본어' },
                            ],
                        },
                    },
                },
                // { key: 'langCd', label: '언어', width: 100, align: 'center' },
                { key: 'rmk', label: '비고', width: 100, align: 'center' },
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
                    ACTIONS.dispatch(ACTIONS.ITEM_CLICK, this.item);
                },
            },
        });
    },
    getData: function (_type) {
        var list = [];
        var _list = this.target.getList(_type);

        if (_type == 'modified' || _type == 'deleted') {
            list = ax5.util.filter(_list, function () {
                delete this.deleted;
                return this.key;
            });
        } else {
            list = _list;
        }
        return list;
    },
    addRow: function () {
        this.target.addRow({ __created__: true }, 'last');
    },
});
/**
 * gridView
 */
fnObj.gridView02 = axboot.viewExtend(axboot.gridView, {
    getDefaultData: function () {
        return {};
    },
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            showRowSelector: true,
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-02"]'),
            columns: [
                {
                    key: 'rsvDt',
                    label: '투숙일',
                    width: 100,
                    align: 'center',
                    formatter: function () {
                        return moment(this.item.arrDt).format('YY.M.D') + '-' + moment(this.item.depDt).format('YY.M.D');
                    },
                },
                { key: 'nightCnt', label: '숙박수', width: 50, align: 'center' },
                { key: 'roomNum', label: '객실번호', width: 80, align: 'center' },
                { key: 'roomTypCd', label: '객실타입', width: 120, align: 'center' },
                { key: 'rsvNum', label: '투숙번호', width: 120, align: 'center' },
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
                },
            },
        });
    },
});

/**
 * formView
 */
fnObj.formView01 = axboot.viewExtend(axboot.formView, {
    getDefaultData: function () {
        return {};
    },
    getData: function () {
        var data = this.modelFormatter.getClearData(this.model.get()); // 모델의 값을 포멧팅 전 값으로 치환.
        return $.extend({}, data);
    },
    setData: function (data) {
        data = $.extend({}, data);

        this.model.setModel(data);
        this.modelFormatter.formatting(); // 입력된 값을 포메팅 된 값으로 변경
    },
    validate: function () {
        var item = this.model.get();

        var rs = this.model.validate();
        if (rs.error) {
            axDialog.alert(LANG('ax.script.form.validate', rs.error[0].jquery.attr('title')), function () {
                rs.error[0].jquery.focus();
            });
            return false;
        }
        // required 이외 벨리데이션 정의
        var pattern;
        if (item.email) {
            pattern = /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.(?:[A-Za-z0-9]{2,}?)$/i;
            if (!pattern.test(item.email)) {
                axDialog.alert('이메일 형식을 확인하세요.', function () {
                    $('[data-ax-path="email"]').focus();
                });
                return false;
            }
        }

        if (item.guestTel && !(pattern = /^([0-9]{3})\-?([0-9]{4})\-?([0-9]{4})$/).test(item.guestTel)) {
            axDialog.alert('전화번호 형식을 확인하세요.'),
                function () {
                    $('[data-ax-path="guestTel"]').focus();
                };
            return false;
        }
        return true;
    },

    initView: function () {
        var _this = this; // fnObj.formView01

        _this.target = $('.js-form');

        _this.model = new ax5.ui.binder();
        _this.model.setModel({}, _this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); // 모델 포메터 시작
    },
});
