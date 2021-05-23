var modalParams = modalParams || {};
var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        console.log(modalParams.id);
        var id = modalParams.id;
        if (!id) return false;
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/chk/' + id,
            callback: function (res) {
                console.log(res.memoList);
                caller.formView01.setData(res);
                caller.gridView01.setData(res.memoList);
            },
            options: {
                // axboot.ajax 함수에 2번째 인자는 필수가 아닙니다. ajax의 옵션을 전달하고자 할때 사용합니다.
                onError: function (err) {
                    console.log(err);
                },
            },
        });
    },
    PAGE_SAVE: function (caller, act, data) {
        if (caller.formView01.validate()) {
            var item = caller.formView01.getData();
            var memos = [].concat(caller.gridView01.getData());
            memos = memos.concat(caller.gridView01.getData('deleted'));
            item.memoList = memos;
            console.log(memos);
            axboot.ajax({
                type: 'POST',
                url: '/api/v1/chk/' + item.id,
                data: JSON.stringify(item),
                callback: function (res) {
                    axDialog.alert('저장 되었습니다', function () {
                        if (parent && parent.axboot && parent.axboot.modal) {
                            parent.axboot.modal.callback({ dirty: true });
                        }
                    });
                },
            });
        }
    },

    PAGE_CLOSE: function (caller, act, data) {
        if (parent) {
            parent.axboot.modal.close(data);
        }
    },
    MODAL_OPEN: function (caller, act, data) {
        caller.guestModalView.open(data);
    },
    ITEM_CLICK: function (caller, act, data) {},
    ITEM_ADD: function (caller, act, data) {
        caller.gridView01.addRow();
    },
    ITEM_DEL: function (caller, act, data) {
        caller.gridView01.delRow('selected');
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

fnObj.guestModalView = axboot.viewExtend({
    open: function (data) {
        var _this = this;
        if (!data) data = {};
        this.modal.open({
            width: 760,
            height: 600,
            header: false,
            iframe: {
                param: { guestNm: data.guestNm, guestTel: data.guestTel, email: data.email, modalView: 'guestModalView' },
                url: '/jsp/reservation/reservation-registration-content.jsp',
            },
        });
    },
    close: function () {
        this.modal.close();
    },
    callback: function (data) {
        // console.log('@@@@@@@@@@@' + data);
        fnObj.formView01.setGuest(data);
        this.modal.close();
    },
    initView: function () {
        this.modal = new ax5.ui.modal();
    },
});
// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    var _this = this;

    this.pageButtonView.initView();
    this.formView01.initView();
    this.gridView01.initView();
    this.guestModalView.initView();

    if (modalParams.id) {
        ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
    }
};

fnObj.pageResize = function () {};

fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, 'data-page-btn', {
            save: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            },
            close: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_CLOSE);
            },
        });
    },
});

//== view 시작
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
                { key: 'memoDtti', label: '작성일', width: '30%', align: 'center', editor: { type: 'text' } },
                { key: 'memoCn', label: '메모', width: '70%', align: 'center', editor: { type: 'text' } },
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
                },
            },
        });
        axboot.buttonClick(this, 'data-grid-view-01-btn', {
            add: function () {
                ACTIONS.dispatch(ACTIONS.ITEM_ADD);
            },
            delete: function () {
                ACTIONS.dispatch(ACTIONS.ITEM_DEL);
            },
        });
    },

    getData: function (_type) {
        var list = [];
        var _list = this.target.getList(_type);

        if (_type == 'modified' || _type == 'deleted') {
            list = ax5.util.filter(_list, function () {
                return this.id;
            });
        } else {
            list = _list;
        }
        return list;
    },
    addRow: function () {
        this.target.addRow({ memoDtti: moment().format('yyyy-MM-DD*HH:mm:ss'), __created__: true }, 'last');
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
        var _this = this;
        var data = this.modelFormatter.getClearData(this.model.get()); // 모델의 값을 포멧팅 전 값으로 치환.
        if ($('input:checkbox[name=advnYn]').is(':checked') == true) {
            data.advnYn = 'Y';
        } else data.advnYn = 'N';

        data.gender = $(':input:radio[name=gender]:checked').val();
        return $.extend({}, data);
    },
    setData: function (data) {
        data = $.extend({}, data);
        if (data.rsvNum) {
            $('.js-rsvNum').text('예약번호: ' + data.rsvNum);
        }
        if (data.advnYn == 'Y') {
            $('input:checkbox[name=advnYn]').prop('checked', true);
        }
        console.log('********' + data.sttusCd);

        this.model.setModel(data);
        this.modelFormatter.formatting(); // 입력된 값을 포메팅 된 값으로 변경
    },
    setGuest: function (data) {
        this.model.set('guestId', data.id);
        this.model.set('guestNm', data.guestNm);
        this.model.set('guestNmEng', data.guestNmEng);
        this.model.set('guestTel', data.guestTel);
        this.model.set('email', data.email);
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
    calcDepDt: function (night) {
        console.log('call calcDepDt...');
        if (!night) return;
        var arrDt = $('.js-arrDt').val();
        if (!arrDt) return;
        if (night < 0 || night == 0) {
            axDialog.alert('1 이상의 숙박수를 입력하세요.', function () {
                $('[data-ax-path="nightCnt"]').val('').focus();
                $('[data-ax-path="depDt"]').val('');
            });
            return;
        }
        var depDt = moment(arrDt).add(night, 'day').format('yyyy-MM-DD');
        console.log('depDt', depDt);
        this.model.set('depDt', depDt);
    },
    calcNigth: function (Dt) {
        console.log('call calcNigth...');
        if (!Dt) return;
        var arrDt = $('.js-arrDt').val();
        var depDt = $('.js-depDt').val();
        if (!arrDt && !depDt) return;
        var night = moment(depDt).diff(moment(arrDt), 'days');
        if (night < 0) {
            axDialog.alert('도착일 이후의 날짜를 선택하세요.', function () {
                $('[data-ax-path="nightCnt"]').val('');
                $('[data-ax-path="depDt"]').val('').focus();
            });
            return;
        }
        this.model.set('nightCnt', night);
    },
    initView: function () {
        var _this = this; // fnObj.formView01

        _this.target = $('.js-form');

        this.model = new ax5.ui.binder();
        this.model.setModel({}, this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); // 모델 포메터 시작

        $('.js-nightCnt').on('change', function () {
            _this.calcDepDt($(this).val());
        });

        $('.js-depDt').on('change', function () {
            _this.calcNigth($(this).val());
        });

        $('.js-arrDt').on('change', function () {
            _this.calcNigth($(this).val());
        });

        axboot.buttonClick(this, 'data-grid-view-01-btn', {
            modalsearch: function () {
                ACTIONS.dispatch(ACTIONS.MODAL_OPEN, this.modal);
            },
        });
    },
});
